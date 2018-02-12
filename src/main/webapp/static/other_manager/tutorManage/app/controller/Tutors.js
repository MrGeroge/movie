/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TutorManage.controller.Tutors', {
    extend: 'Ext.app.Controller',

    models:['Tutor'],
    stores:['Tutors'],
    views:[ 'Grid',
        'TutorWin',
        'TutorUpdateWin',
        'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-tutor-btn':{
                click:this.addTutor
            },

            '#edit-tutor-btn':{
                click:this.editTutor
            },

            '#delete-tutor-btn':{
                click:this.deleteTutor
            },

            'tutorWin button[action=saveChange]':{
                click:this.createTutor
            },
            'tutorUpdateWin button[action=saveChange]':{
                click:this.updateTutor
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addTutor : function(){
        var win = Ext.widget('tutorWin');
        win.show();
    },

    editTutor : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('tutorUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
        var picture = Ext.getCmp('viewPicture');
        picture.getEl().dom.src = rec.data.avatarUrl;
    },

    createTutor: function(button){
        var form = Ext.getCmp('tutorForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addTutor,
                method:'POST',
                params:{
                    name:values.name,
                    position:values.position,
                    summary:values.summary,
                    avatar_url:values.avatarUrl
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Tutors').reload();
                    var win = Ext.getCmp('tutorWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateTutor: function(button){
        var form = Ext.getCmp('tutorUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Tutors').reload();
                }
            });
        }*/
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateTutor + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"name":"' + values.name + '",' +
                '"position":"' + values.position + '",' +
                '"summary":"' + values.summary + '",' +
                '"avatarUrl":"' + values.avatarUrl + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Tutors').reload();
                    var win = Ext.getCmp('tutorUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteTutor: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null) {
            /*Ext.Msg.confirm('警告', '你确定删除？', function () {
                Ext.Ajax.request({
                    url: '',
                    method: 'POST',
                    params: {
                        id: rec.id
                    },
                    success: function (response) {
                        Ext.Msg.alert('提示', '删除成功！');
                        Ext.getStore('Tutors').reload();
                    }
                });
            });*/
            Ext.Msg.confirm('警告','你确定删除？',function(button){
                if(button == 'yes') {
                    $.ajax({
                        url: ip.deleteTutor + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Tutors').reload();
                        }
                    });
                }
            });
        }
    },

    closeUploadWin : function(button){
        var form = button.up('form');

        if(form.isValid()){
            form.submit({
                url: ip.uploadImg,
                waitMsg: '正在上传...',
                success: function(form,action) {
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('Success', '图片上传成功');
                    var tutorForm = Ext.getCmp('tutorForm');
                    var tutorUpdateForm = Ext.getCmp('tutorUpdateForm');
                    if(tutorForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        tutorForm.getForm().findField('avatarUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(tutorUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        tutorUpdateForm.getForm().findField('avatarUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    var win = Ext.getCmp('pictureUpload');
                    win.close();
                },
                failure:function(form,action){
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('失败', '图片上传失败');
                }
            });
        }
    }
});