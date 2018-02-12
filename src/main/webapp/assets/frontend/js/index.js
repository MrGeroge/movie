function showRegister(){
  $('.m-mask').css('display','block');
  $('.m-login').css('display','none');
  $('.m-register').css('display','block');
}

function showLogin(){
  $('.m-mask').css('display','block');
  $('.m-register-success').css('display','none');
  $('.m-register').css('display','none');
  $('.m-login').css('display','block');
}

function closeMask(){
  $('.m-register').css('display','none');
  $('.m-login').css('display','none');
  $('.m-mask').css('display','none');
}

function registerSuccess(){
  $('.m-register').css('display','none');
  $('.m-register-success').css('display','block');
}
function login(){
    var data=$('#login_form').serialize();
    $.ajax({
       url:'/account/login',
        type:'POST',
        data:data,
        success:function(data){
            window.location='/';
        },
        error:function(data){
            alert('登陆失败');
        }
    });
}
function register(){
    var data=$('#register_form').serialize();
    $.ajax({
        url:'/account/register',
        type:'POST',
        data:data,
        success:function(data){
            alert('注册成功');
            window.location='/';
//            registerSuccess();
        },
    error:function(data){
        alert('注册失败');
    }
    });
}


//window.onload=function(){
//  var odiv = document.getElementById('div1');
//  var oul = odiv.getElementsByTagName('ul')[0];
//  var ali = oul.getElementsByTagName('li');
//  var spa = -2;
//  oul.innerHTML=oul.innerHTML+oul.innerHTML;
//  oul.style.width=ali[0].offsetWidth*(ali.length + 1)+'px';
//  function move(){
//    if(oul.offsetLeft<-oul.offsetWidth/2){
//      oul.style.left='0';
//    }
//    if(oul.offsetLeft>0){
//      oul.style.left=-oul.offsetWidth/2+'px'
//    }
//    oul.style.left=oul.offsetLeft+spa+'px';
//  }
//  var timer = setInterval(move,30);
//
//  odiv.onmousemove=function(){clearInterval(timer);};
//  odiv.onmouseout=function(){timer = setInterval(move,30)};
//  document.getElementsByTagName('a')[0].onclick = function(){
//    spa=-2;
//  };
//  document.getElementsByTagName('a')[1].onclick = function(){
//    spa=2;
//  }
//};

function pageScroll(){
  //把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
  window.scrollBy(0,-10);
  //延时递归调用，模拟滚动向上效果
  scrolldelay = setTimeout('pageScroll()',10);
  var sTop=document.documentElement.scrollTop+document.body.scrollTop;
  //判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
  if(sTop==0) clearTimeout(scrolldelay);
}



/**
 A jQuery plugin for search hints

 Author: Lorenzo Cioni - https://github.com/lorecioni
 */

(function($) {
    $.fn.autocomplete = function(params) {

        //Selections
        var currentSelection = -1;
        var currentProposals = [];

        //Default parameters
        params = $.extend({
            hints: [],
            placeholder: 'Search',
            width: 200,
            height: 16,
            showButton: true,
            buttonText: 'Search',
            onSubmit: function(text){},
            onBlur: function(){}
        }, params);

        //Build messagess
        this.each(function() {
            //Container
            var searchContainer = $('<div></div>')
                .addClass('autocomplete-container')
                .css('height', params.height * 2);

            //Text input
            var input = $('<input type="text" autocomplete="off" name="query">')
                .attr('placeholder', params.placeholder)
                .addClass('autocomplete-input')
                .css({
                    'width' : params.width,
                    'height' : params.height
                });

            if(params.showButton){
                input.css('border-radius', '3px 0 0 3px');
            }

            //Proposals
            var proposals = $('<div></div>')
                .addClass('proposal-box')
                .css('width', params.width + 18)
                .css('top', input.height() + 20);
            var proposalList = $('<ul></ul>')
                .addClass('proposal-list');

            proposals.append(proposalList);

            input.keydown(function(e) {
                switch(e.which) {
                    case 38: // Up arrow
                        e.preventDefault();
                        $('ul.proposal-list li').removeClass('selected');
                        if((currentSelection - 1) >= 0){
                            currentSelection--;
                            $( "ul.proposal-list li:eq(" + currentSelection + ")" )
                                .addClass('selected');
                        } else {
                            currentSelection = -1;
                        }
                        break;
                    case 40: // Down arrow
                        e.preventDefault();
                        if((currentSelection + 1) < currentProposals.length){
                            $('ul.proposal-list li').removeClass('selected');
                            currentSelection++;
                            $( "ul.proposal-list li:eq(" + currentSelection + ")" )
                                .addClass('selected');
                        }
                        break;
                    case 13: // Enter
                        if(currentSelection > -1){
                            var text = $( "ul.proposal-list li:eq(" + currentSelection + ")" ).html();
                            input.val(text);
                        }
                        currentSelection = -1;
                        proposalList.empty();
                        params.onSubmit(input.val());
                        break;
                    case 27: // Esc button
                        currentSelection = -1;
                        proposalList.empty();
                        input.val('');
                        break;
                }
            });

            input.bind("change paste keyup", function(e){
                if(e.which != 13 && e.which != 27
                    && e.which != 38 && e.which != 40){
                    currentProposals = [];
                    currentSelection = -1;
                    proposalList.empty();
                    if(input.val() != ''){
                        var word = "^" + input.val() + ".*";
                        proposalList.empty();
                        for(var test in params.hints){
                            if(params.hints[test].match(word)){
                                currentProposals.push(params.hints[test]);
                                var element = $('<li></li>')
                                    .html(params.hints[test])
                                    .addClass('proposal')
                                    .click(function(){
                                        input.val($(this).html());
                                        proposalList.empty();
                                        params.onSubmit(input.val());
                                    })
                                    .mouseenter(function() {
                                        $(this).addClass('selected');
                                    })
                                    .mouseleave(function() {
                                        $(this).removeClass('selected');
                                    });
                                proposalList.append(element);
                            }
                        }
                    }
                }
            });

            input.blur(function(e){
                currentSelection = -1;
                //proposalList.empty();
                params.onBlur();
            });

            searchContainer.append(input);
            searchContainer.append(proposals);

            if(params.showButton){
                //Search button
                var button = $('<div></div>')
                    .addClass('autocomplete-button')
                    .html(params.buttonText)
                    .css({
                        'height': params.height + 2,
                        'line-height': params.height + 'px'
                    })
                    .click(function(){
                        proposalList.empty();
                        params.onSubmit(input.val());
                    });
                searchContainer.append(button);
            }

            $(this).append(searchContainer);

            if(params.showButton){
                //Width fix
                searchContainer.css('width', params.width + button.width() + 50);
            }
        });

        return this;
    };

})(jQuery);

$('#search_btn').on('input propertychange',function(){
    var value = $('#search_btn').val();
    //var list;
    //$.get("/movie/name/get?name="+value,function(data){
    //    list = data;
    //});
    //console.log(list);
    $("#search_btn").autocomplete("/movie/name/get"+"?name="+value, {
        minChars: 2,
        width: 310,
        matchContains: true,
        autoFill: false,
        formatItem: function(data, i, n, v) {
            return i + ":" + v;
        },
        formatResult: function(data,v) {
            return v;
        }
    });
});


//赞助商信息轮播

var len = $(".m-bottom-sponsors-list li").length;
var index = 0;  //图片序号
var adTimer;

//滚动广告

$(".m-bottom_list li").mouseover(function() {
    index = $(".m-bottom_list li").index(this);  //获取鼠标悬浮 li 的index
    showImg(index);
}).eq(0).mouseover();
//滑入停止动画，滑出开始动画.
$('.m-bottom_list').hover(function() {
    clearInterval(adTimer);
}, function() {
    adTimer = setInterval(function() {

        if (index >=(len-6)) {       //最后一张图片之后，转到第一张
            index = 0;
        }
        index++;
        showImg(index);
    }, 1000);
}).trigger("mouseleave");;

function showImg(index) {
    var adHeight =$(".m-bottom_list li:first").width(); ;
    $(".m-bottom-sponsors-list ul").stop(true, false).animate({
        "marginLeft":-adHeight * index + "px"  //改变 marginLeft 属性的值达到轮播的效果
    });
    $(".m-bottom_list li").removeClass("on")
        .eq(index).addClass("on");
}