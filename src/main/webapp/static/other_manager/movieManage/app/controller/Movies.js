/**
 * Created by hao on 2015/12/18.
 */
Ext.define('MovieManage.controller.Movies', {
    extend: 'Ext.app.Controller',

    models:['Movie',
            'Kind'],
    stores:['Movies',
            'Kinds'],
    views:['Grid',
            'MovieWin',
            'MovieUpdateWin',
            'KindCombo',
            'MovieViewWin',
            'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-movie-btn':{
                click:this.addMovie
            },

            '#edit-movie-btn':{
                click:this.editMovie
            },

            '#delete-movie-btn':{
                click:this.deleteMovie
            },

            '#view-movie-btn':{
                click:this.viewDetail
            },

            'movieWin button[action=saveChange]':{
                click:this.createMovie
            },
            'movieUpdateWin button[action=saveChange]':{
                click:this.updateMovie
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addMovie: function(){
        var win = Ext.widget('movieWin');
        win.show();
    },

    editMovie : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('movieUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
        var picture = Ext.getCmp('viewPicture');
        picture.getEl().dom.src = rec.data.imageUrl;
    },

    createMovie: function(button){
        var form = Ext.getCmp('movieForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addMovie,
                method:'POST',
                params:{
                    name:values.name,
                    kindId:values.kindId,
                    firstType:values.firstType,
                    attribute:values.attribute,
                    year:values.year,
                    author:values.author,
                    genre:values.genre,
                    group:values.group,
                    description:values.description,
                    state:values.state,
                    imageUrl:values.imageUrl
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Movies').reload();
                    var win = Ext.getCmp('movieWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateMovie: function(button){
        var form = Ext.getCmp('movieUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Movies').reload();
                }
            });
        }*/
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateMovie + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"name":"' + values.name + '",' +
                '"imageUrl":"' + values.imageUrl + '",' +
                '"kindId":"' + values.kindId + '",' +
                '"firstType":"' + values.firstType + '",' +
                '"attribute":"' + values.attribute + '",' +
                '"year":"' + values.year + '",' +
                '"author":"' + values.author + '",' +
                '"genre":"' + values.genre + '",' +
                '"group":"' + values.group + '",' +
                '"description":"' + values.description + '",' +
                '"state":"' + values.state + '"}',
                //extraParams:values,
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Movies').reload();
                    var win = Ext.getCmp('movieUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteMovie: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null){
            /*Ext.msg.confirm('警告','你确定删除？',function(){
                Ext.Ajax.request({
                    url:'',
                    method:'POST',
                    params:{
                        id:rec.id
                    },
                    success: function(response){
                        Ext.Msg.alert('提示', '删除成功！');
                        Ext.getStore('Movies').reload();
                    }
                })
            });*/
            Ext.Msg.confirm('警告','你确定删除？',function(button) {
                if(button=='yes') {
                    $.ajax({
                        url: ip.deleteMovie + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Movies').reload();
                        }
                    });
                }else{

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

        var viewWin = Ext.widget('movieViewWin');
        viewWin.down('form').loadRecord(rec);
        viewWin.show();
    },

    closeUploadWin: function(button){
        var form = button.up('form');

        if(form.isValid()){
            //console.log('dd');
            form.submit({
                url: ip.uploadImg,
                waitMsg: '正在上传...',
                success: function(form,action) {
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('Success', '图片上传成功');
                    var movieForm = Ext.getCmp('movieForm');
                    var movieUpdateForm = Ext.getCmp('movieUpdateForm');
                    if(movieForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        movieForm.getForm().findField('imageUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(movieUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        movieUpdateForm.getForm().findField('imageUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    var win = Ext.getCmp('pictureUpload');
                    win.close();
                },
                failure:function(form,action){
                    //Ext.Msg.alert('发生错误');
                    Ext.Msg.alert('失败', '图片上传失败');
                }
            });
        }
    }
});