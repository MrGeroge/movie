/**
 * Created by hezhengjie on 2015/12/9.
 */
    //    请求IP
    var base='';
    var ip = {
        'base':'http://192.168.0.103:8080',
        'login':base+'/login',
        'logout':base+'/logout',
        'register':base+'/admin/register',
        'updatePassword':base+'/update/password',
        'updateProfile':base+'/account/update/profile',
        'addTag':base+'/admin/tag',
        'deleteTag':base+'/admin/tag/delete',
        'updateTag':base+'/admin/tag',
        'listTag':base+'/admin/tag/get',
        'editUserTag':base+'/admin/tag/account/',
        'listAccount':base+'/admin/account/get',
        'listAccountByTag':base+'/admin/account/tag/',
        'listAccountLike':base+'/admin/account/keyword/get',
        'deleteAccount':base+'/admin/account/delete',
        'addSponsor':base+'/admin/sponsor',
        'deleteSponsor':base+'/admin/sponsor/',
        'updateSponsor':base+'/admin/sponsor/',
        'listSponsor':base+'/admin/sponsor/get',
        'addArticle':base+'/admin/article',
        'deleteArticle':base+'/admin/article/',
        'updateArticle':base+'/admin/article/',
        'listArticle':base+'/admin/article/get',
        'getArticleDetail':base+'/admin/article/detail/get',
        'addArticleCategory':base+'/admin/article/category/add',
        'deleteArticleCategory':base+'/admin/article/category/delete',
        'listArticleCategory':base+'/admin/article/category/list',
        'updateArticleCategory':base+'/admin/article/category/update',
        'addBuoy':base+'/admin/buoy',
        'deleteBuoy':base+'/admin/buoy/',
        'updateBuoy':base+'/admin/buoy/',
        'listBuoy':base+'/admin/buoy/get',
        'addCourse':base+'/admin/course',
        'deleteCourse':base+'/admin/course/',
        'updateCourse':base+'/admin/course/',
        'listCourse':base+'/admin/course/get',
        'addEnter':base+'/admin/enter',
        'deleteEnter':base+'/admin/enter/',
        'updateEnter':base+'/admin/enter/',
        'listEnter':base+'/admin/enter/get',
        'addForm':base+'/admin/form',
        'updateForm':base+'/admin/form/',
        'listForm':base+'/admin/form/get',
        'listMyForm':base+'/admin/my/form/get',
        'listApplyForm':base+'/admin/form/apply/get',
        'addInvestor':base+'/admin/investor',
        'deleteInvestor':base+'/admin/investor/',
        'updateInvestor':base+'/admin/investor/',
        'listInvestor':base+'/admin/investor/get',
        'uploadImg':base+'/upload/img',
        'addMapLight':base+'/admin/maplight',
        'deleteMapLight':base+'/admin/maplight/',
        'updateMapLight':base+'/admin/maplight/',
        'listMapLight':base+'/admin/maplight/get',
        'light':base+'/admin/maplight/light',
        'addMovie':base+'/admin/movie',
        'updateMovie':base+'/admin/movie/',
        'deleteMovie':base+'/admin/movie/',
        'listMovie':base+'/admin/movie/get',
        'listKindMovie':base+ '/admin/movie/category/get',
        'addJudge':base+ '/admin/review',
        'deleteJudge':base+ '/admin/review/',
        'updateJudge':base+ '/admin/review/',
        'listJudge':base+ '/admin/review/get',
        'addTutor':base+'/admin/mentor',
        'updateTutor':base+'/admin/mentor/',
        'deleteTutor':base+'/admin/mentor/',
        'listTutor':base+'/admin/mentor/get',
        'listEmailSetting':base,
        'addEmailSetting':base,
        'updateEmailSetting':base,
        'setCurrentAccount':base,
        'deleteEmailSetting':base,
        'tl_addAward':base + '/admin/award',
        'tl_updateAward':base +'/admin/award/',
        'tl_deleteAward':base + '/admin/award/',
        'tl_listAward':base+'/admin/award/get',
        'tl_periodNum':base+'/admin/period/get',
        'tl_addJudger':base + '/admin/judger',
        'tl_updateJudger':base + '/admin/judger/',
        'tl_deleteJudger':base + '/admin/judger/',
        'tl_listJudger':base + '/admin/judger/get',
        'tl_addVolunteer':base + '/admin/volunteer',
        'tl_updateVolunteer':base + '/admin/volunteer/',
        'tl_deleteVolunteer':base + '/admin/volunteer/',
        'tl_listVolunteer':base + '/admin/volunteer/get'
        };

    var baseUrl = '../../../assets/admin/images';
    var url = {
        'picturePlaceholder' : baseUrl + '/picturePlaceholder.jpg',
        'avatarPlaceholder' : baseUrl + '/avatarPlaceholder.png'
    };

//全局设置error
$(document).ajaxError(function(){
    alert("操作失败!");
});
/*//    文件上传格式控制
function fileChange(target) {
    var fileSize = 0;
    if (isIE && !target.files) {
        var filePath = target.value;
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size;
    } else {
        fileSize = target.files[0].size;
    }
    var size = fileSize / 1024;
    if (size > 20000) {
        alert("附件不能大于20M");
        target.value = "";
    }
    var name = target.value;
    var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    if (fileName != "xls") {
        alert("请选择execl格式文件上传！");
        target.value = "";
    }
}
//检测是否是IE
function isIE() {
    var navigatorName = "Microsoft Internet Explorer";
    var isIE = false;
    if (navigator.appName == navigatorName) {
        isIE = true;
    }
    return isIE;
}
//  日期格式转换
function myformatter(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0], 10);
    var m = parseInt(ss[1], 10);
    var d = parseInt(ss[2], 10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
        return new Date(y, m - 1, d);
    } else {
        return new Date();
    }
}
//    表单转js对象
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//        可编辑设置
/!*$.extend($.fn.datagrid.methods, {
    editCell: function(jq,param){
        return jq.each(function(){
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field){
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});*!/

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('.dg_selected').datagrid('validateRow', editIndex)){
        $('.dg_selected').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field){
    if (endEditing()){
        $('.dg_selected').datagrid('selectRow', index)
            .datagrid('editCell', {index:index,field:field});
        editIndex = index;
    }
}*/
