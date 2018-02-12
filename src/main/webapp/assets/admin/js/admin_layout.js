/**
 * Created by hao on 2015/12/16.
 */

//设置修改密码窗口
function openPwd() {
    $('#w').window({
        title: '修改密码',
        width: 300,
        modal: true,
        shadow: true,
        closed: true,
        height: 160,
        resizable:false
    });
}
//关闭修改密码窗口
function close() {
    $('#w').window('close');
}


//修改密码
function serverLogin() {
    var $newpass = $('#txtNewPass');
    var $rePass = $('#txtRePass');

    if ($newpass.val() == '') {
        msgShow('系统提示', '请输入密码！', 'warning');
        return false;
    }
    if ($rePass.val() == '') {
        msgShow('系统提示', '请在一次输入密码！', 'warning');
        return false;
    }

    if ($newpass.val() != $rePass.val()) {
        msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
        return false;
    }

    $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
        msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
        $newpass.val('');
        $rePass.val('');
        close();
    })

}

//初始化左侧，根据权限不同初始化不同的导航
var _menus = {"menus":[
    {"menuid":"1","icon":"'icon-user-suit'","menuname":"用户管理","show":"true",
        "menus":[
            {"menuname":"标签管理","icon":"tag-blue","url":"user_manager/label.html"},
            {"menuname":"用户信息","url":"user_manager/detailInfo.html"}
        ]
    },

    {"menuid":"2","icon":"'icon-email'","menuname":"邮件管理","show":"true",
        "menus":[
            {"menuname":"群发邮件","url":"email_manager/sendEmail.html"},
            {"menuname":"邮件设置","url":"email_manager/emailSetting.html"}
        ]
    },
    {"menuid":"3","icon":"'icon-application'","menuname":"报名管理","show":"true",
        "menus":[
            {"menuname":"创建报名表","url":"apply_manager/applyForm.html"},
            {"menuname":"报名表列表","url":"apply_manager/listApplyForm.html"},
            {"menuname":"报名表记录","url":"apply_manager/applyRecord.html"}
        ]
    },
    {"menuid":"4","icon":"'icon-money'","menuname":"赞助管理","show":"true",
        "menus":[
            {"menuname":"赞助商管理","url":"sponsor_manager/sponsor.html"}
        ]
    }

]};

function InitLeftMenu(identity) {

    $("#navTree").empty();
    var menulist = "";

    $.each(identity.menus, function(i, n) {
        if(n.show){
            menulist += '<div title="'+n.menuname+'" data-options="iconCls:'+ n.icon+'" style="overflow:auto;">';
        }
        menulist += '<ul>';
        $.each(n.menus, function(j, o) {
            menulist += '<li><div><a target="mainFrame" href="' + o.url + '"><span class="icon"></span>' + o.menuname + '</a></div></li> ';
        })
        menulist += '</ul></div>';
    })

    $('#navTree').append(menulist);

    $('#navTree li a').click(function(){
        var tabTitle = $(this).text();
        var url = $(this).attr("href");
        addTab(tabTitle,url);
        $('#navTree li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function(){
        $(this).parent().addClass("hover");
    },function(){
        $(this).parent().removeClass("hover");
    });

    $("#navTree").accordion({fit:true});
}

function addTab(subtitle,url){
    if(!$('#tabs').tabs('exists',subtitle)){
        $('#tabs').tabs('add',{
            title:subtitle,
            content:createFrame(url),
            closable:true,
            width:$('#mainPanle').width()-10,
            height:$('#mainPanle').height()-26
        });
    }else{
        $('#tabs').tabs('select',subtitle);
    }
    tabClose();
}

function createFrame(url)
{
    var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose()
{
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function(){
        var subtitle = $(this).children("span").text();
        $('#tabs').tabs('close',subtitle);
    })

    $(".tabs-inner").bind('contextmenu',function(e){
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY,
        });

        var subtitle =$(this).children("span").text();
        $('#mm').data("currtab",subtitle);

        return false;
    });
}


//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}

$(function(){
    InitLeftMenu(_menus);

    tabClose();
    openPwd();
    //
    $('#editpass').click(function() {
        $('#w').window('open');
    });

    $('#btnEp').click(function() {
        serverLogin();
    })

    $('#loginOut').click(function() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

            if (r) {
                location.href = '#';
            }
        });

    })


})