/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TLVolunteerManage.controller.Volunteers', {
    extend: 'Ext.app.Controller',

    models:['Volunteer'],
    stores:['Volunteers'],
    views:[ 'Grid',
        'VolunteerWin',
        'VolunteerUpdateWin',
        'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-volunteer-btn':{
                click:this.addVolunteer
            },

            '#edit-volunteer-btn':{
                click:this.editVolunteer
            },

            '#delete-volunteer-btn':{
                click:this.deleteVolunteer
            },

            'volunteerWin button[action=saveChange]':{
                click:this.createVolunteer
            },
            'volunteerUpdateWin button[action=saveChange]':{
                click:this.updateVolunteer
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addVolunteer : function(){
        var win = Ext.widget('volunteerWin');
        win.show();
    },

    editVolunteer : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('volunteerUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
        var picture = Ext.getCmp('viewPicture');
        picture.getEl().dom.src = rec.data.avatarUrl;
    },

    createVolunteer: function(button){
        var form = Ext.getCmp('volunteerForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.tl_addVolunteer,
                method:'POST',
                params:{
                    //name:values.name,
                    //position:values.position,
                    summary:values.summary,
                    avatar_url:values.avatarUrl,
                    period_num:values.periodNum
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Volunteers').reload();
                    var win = Ext.getCmp('volunteerWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateVolunteer: function(button){
        var form = Ext.getCmp('volunteerUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Judges').reload();
                }
            });
        }*/
        var d = {
            "summary":values.summary,
            "avatarUrl":values.avatarUrl,
            "periodNum":values.periodNum
        };
        d = JSON.stringify(d);

        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.tl_updateVolunteer + values.id,
                type: 'put',
                contentType: 'application/json',
                data: d,
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Volunteers').reload();
                    var win = Ext.getCmp('volunteerUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteVolunteer: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null) {
            Ext.Msg.confirm('警告','你确定删除？',function(button){
                if(button == 'yes') {
                    $.ajax({
                        url: ip.tl_deleteVolunteer + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Volunteers').reload();
                        }
                    });
                }
            });
        }
    },

    closeUploadWin:function(button){
        var form = button.up('form');

        if(form.isValid()){
            form.submit({
                url: ip.uploadImg,
                waitMsg: '正在上传...',
                success: function(form,action) {
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('Success', '图片上传成功');
                    var volunteerForm = Ext.getCmp('volunteerForm');
                    var volunteerUpdateForm = Ext.getCmp('volunteerUpdateForm');
                    if(volunteerForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        volunteerForm.getForm().findField('avatarUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(volunteerUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        volunteerUpdateForm.getForm().findField('avatarUrl').setValue(data.link);
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