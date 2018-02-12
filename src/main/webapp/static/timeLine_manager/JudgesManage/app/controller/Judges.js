/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TLJudgeManage.controller.Judges', {
    extend: 'Ext.app.Controller',

    models:['Judge'],
    stores:['Judges'],
    views:[ 'Grid',
        'JudgeWin',
        'JudgeUpdateWin',
        'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-judge-btn':{
                click:this.addJudge
            },

            '#edit-judge-btn':{
                click:this.editJudge
            },

            '#delete-judge-btn':{
                click:this.deleteJudge
            },

            'judgeWin button[action=saveChange]':{
                click:this.createJudge
            },
            'judgeUpdateWin button[action=saveChange]':{
                click:this.updateJudge
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addJudge : function(){
        var win = Ext.widget('judgeWin');
        win.show();
    },

    editJudge : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('judgeUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
        var picture = Ext.getCmp('viewPicture');
        picture.getEl().dom.src = rec.data.avatarUrl;
    },

    createJudge: function(button){
        var form = Ext.getCmp('judgeForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.tl_addJudger,
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
                    Ext.getStore('Judges').reload();
                    var win = Ext.getCmp('judgeWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateJudge: function(button){
        var form = Ext.getCmp('judgeUpdateForm');
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
        }
        d = JSON.stringify(d);
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.tl_updateJudger + values.id,
                type: 'put',
                contentType: 'application/json',
                data: d,
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Judges').reload();
                    var win = Ext.getCmp('judgeUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteJudge: function(button){
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
                        url: ip.tl_deleteJudger + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Judges').reload();
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
                    var judgeForm = Ext.getCmp('judgeForm');
                    var judgeUpdateForm = Ext.getCmp('judgeUpdateForm');
                    if(judgeForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        judgeForm.getForm().findField('avatarUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(judgeUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        judgeUpdateForm.getForm().findField('avatarUrl').setValue(data.link);
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