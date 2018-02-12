/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.controller.Articles', {
    extend: 'Ext.app.Controller',

    models:['Article',
            'ArticleDetail'],
    stores:['Articles',
            'ArticleDetails'],
    views:['Grid',
        'ArticleWin',
        'ArticleViewWin',
        'ArticleUpdateWin',
        'ArticleContentWin',
        'ArticleContentUpdateWin',
        'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-article-btn':{
                click:this.addArticle
            },

            '#edit-article-btn':{
                click:this.editArticle
            },

            '#delete-article-btn':{
                click:this.deleteArticle
            },

            '#view-article-btn':{
                click:this.viewDetail
            },

            '#add-articleContent-btn':{
                click:this.editArticleContent
            },

            'articleWin button[action=saveChange]':{
                click:this.createArticle
            },
            'articleUpdateWin button[action=saveChange]':{
                click:this.updateArticle
            },
            'articleContentWin button[action=saveChange]':{
                click:this.saveArticleContent
            },
            'articleContentUpdateWin button[action=saveChange]':{
                click:this.updateArticleContent
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
            /*'articleWin button[action=openContentWin]':{
                click:this.openContentWin
            }*/
        });
    },

    addArticle: function(){
        var win = Ext.widget('articleWin');
        win.show();
    },

    editArticle : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];
        if(rec.data.id != null){
            Ext.Ajax.request({
                url:ip.getArticleDetail,
                method:'POST',
                params:{
                    article_id:rec.data.id
                },
                success: function(response){
                    var data = eval('('+response.responseText+')');
                    var win = Ext.widget('articleUpdateWin');
                    rec.data.content = data.detail;
                    win.down('form').loadRecord(rec);
                    win.show();
                    var picture = Ext.getCmp('viewPicture');
                    picture.getEl().dom.src = rec.data.coverUrl;
                }
            });
        }



    },

    createArticle: function(button){
        var form = Ext.getCmp('articleForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addArticle,
                method:'POST',
                params:{
                    coverUrl:values.coverUrl,
                    title:values.title,
                    content:values.content
                    //category_id:1
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Articles').reload();
                    var win = Ext.getCmp('articleWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    updateArticle: function(button){
        var form = Ext.getCmp('articleUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Articles').reload();
                }
            });
        }*/
        var p = {
            "title":values.title,
            "coverUrl":values.coverUrl,
            "content":values.content};
        p =JSON.stringify(p);
        //console.log(p);

        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateArticle + values.id,
                type: 'put',
                contentType: 'application/json',
                /*data:'{"categoryId":"1",'+
                 '"title":"'+values.title+'",'+
                 '"coverUrl":"'+values.coverUrl+'",'+
                 '"content":"'+values.content+'"}',*/
                data: p,
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Articles').reload();
                    var win = Ext.getCmp('articleUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    deleteArticle: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null){
            Ext.Msg.confirm('警告','你确定删除？',function(button){
                if(button == 'yes') {
                    Ext.Ajax.request({
                        url: ip.deleteArticle+rec.data.id,
                        method: 'delete',
                        params: {
                            id: rec.data.id
                        },
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Articles').reload();
                        }
                    })
                }
            });
        }
    },

    viewDetail : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        /*if(rec.data.id != null){
            var store = Ext.getStore('ArticleDetails');
            store.load({
                params:{
                    article_id:rec.data.id
                },
                //scope:this,
                callback:function(record,options,success){
                    var viewWin = Ext.widget('articleViewWin');
                    var frm = document.getElementById("articleContent").contentWindow;
                    frm.document.getElementsByTagName('div').innerHTML = records.data.detail;
                    viewWin.show();
                }
            });
        }*/

        if(rec.data.id != null){
            Ext.Ajax.request({
                url:ip.getArticleDetail,
                method:'POST',
                params:{
                    article_id:rec.data.id
                },
                success: function(response){
                    var viewWin = Ext.widget('articleViewWin');
                    viewWin.show();
                    //console.log(document.getElementById('articleView').contentWindow);
                    //console.log('test');
                    //console.log($('#articleView').prop('contentWindow').document);
                    //var content = document.getElementById('articleView').contentWindow.setHtmlCodeView();
                    //document.frames['viewFrame'].contentWindow.setHtmlCode(response.responseText.detail);
                    //viewWin.show();
                    //console.log(document.getElementById('articleView').contentWindow);
                    var frm = document.getElementById('articleView').contentWindow;
                    if (frm.attachEvent){
                        frm.attachEvent("onload", function(){
                            //console.log(frm.setHtmlCodeView());
                            frm.setHtmlCodeView(response.responseText.detail);
                        });
                    }else{
                        frm.onload = function() {
                            var data = eval('('+response.responseText+')');
                            //console.log(data);
                            frm.setHtmlCodeView(data.detail);
                        }
                    }
                }
            });
        }


    },

    editArticleContent : function(button){
        var win = Ext.widget('articleContentWin');
        win.show();
    },

    saveArticleContent:function(button){
        //var frm = document.getElementById('articleContent').contentWindow;
        //var frm = $('#articleContent').contents();

        //var htmlCode = document.contentWindow.frames['articleContent'].htmlCode;
        //var htmlCode =frm.find('#edit');
        //var tset = $('#edit');
            //editable('getHTML', true, true);
        //console.log(tset.html());
            //.editable('getHTML', true, true);
        //alert(htmlCode.innerHTML);
        /*Ext.Ajax.request({
            url:'',
            method:'POST',
            params:{
                applyId:applyId,
                htmlCode:htmlCode
            },
            success: function(response){
                Ext.Msg.alert('提示', '保存成功！');
            }
        });*/

        //console.log(document.getElementById("articleContent").contentWindow);
        var htmlCode = document.getElementById("articleContent").contentWindow.returnHtmlCode();
        var form = Ext.getCmp('articleForm');
        form.getForm().findField('content').setValue(htmlCode);
        Ext.getCmp('articleContentWin').close();
    },

    updateArticleContent:function(button){
        var htmlCode = document.getElementById("articleContent").contentWindow.returnHtmlCode();
        var form = Ext.getCmp('articleUpdateForm');
        form.getForm().findField('content').setValue(htmlCode);
        var win = Ext.getCmp('articleContentUpdateWin');
        win.close();
    },

    closeUploadWin:function(button){
        var form = button.up('form');

        //var form = this.up('form').getForm();
        if(form.isValid()){
            form.submit({
                url: ip.uploadImg,
                waitMsg: '正在上传...',
                success: function(form,action) {
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('Success', '图片上传成功');
                    var articleForm = Ext.getCmp('articleForm');
                    var articleUpdateForm = Ext.getCmp('articleUpdateForm');
                    if(articleForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        articleForm.getForm().findField('coverUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(articleUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        articleUpdateForm.getForm().findField('coverUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }

                    var win = Ext.getCmp('pictureUpload');
                    win.close();
                },
                failure:function(form,action){
                    //console.log(form);
                    //nsole.log(action);
                    Ext.Msg.alert('失败', '图片上传失败');
                }
            });
        }


    }

    /*openContentWin:function(button){
        var uploadWin = Ext.widget('articleContentWin');
        uploadWin.show();
    }*/
});