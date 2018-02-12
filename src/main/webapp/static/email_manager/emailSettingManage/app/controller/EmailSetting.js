/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailSettingManage.controller.EmailSetting', {
    extend: 'Ext.app.Controller',

    models:['EmailAccount'],
    stores:['EmailAccounts'],
    views:['Grid',
            'EmailSettingWin',
            'EmailSettingUpdateWin'
    ],

    init: function(){
        this.control({
            '#add-emailAccount-btn':{
                click:this.addEmailSetting
            },

            '#edit-emailAccount-btn':{
                click:this.editEmailSetting
            },

            '#delete-emailAccount-btn':{
                click:this.deleteEmailSetting
            },

            '#reset-emailAccount-btn':{
                click:this.resetEmailAccount
            },

            'emailSettingWin button[action=saveChange]':{
                click:this.createEmailSetting
            },
            'emailSettingUpdateWin button[action=saveChange]':{
                click:this.updateEmailSetting
            }
        });
    },

    addEmailSetting : function(){
        var win = Ext.widget('emailSettingWin');
        win.show();
    },

    editEmailSetting : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('emailSettingWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createEmailSetting: function(button){
        var form = Ext.getCmp('emailSettingForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('EmailSettings').reload();
                    Ext.getCmp('emailSettingWin').close();
                }
            });
        }
    },

    updateEmailSetting: function(button){
        var form = Ext.getCmp('emailSettingUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('EmailSettings').reload();
                    Ext.getCmp('emailSettingUpdateWin').close();
                }
            });
        }
    },

    deleteEmailSetting: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null){
            Ext.msg.confirm('警告','你确定删除？',function(){
                Ext.Ajax.request({
                    url:'',
                    method:'POST',
                    params:{
                        id:rec.id
                    },
                    success: function(response){
                        Ext.Msg.alert('提示', '删除成功！');
                        Ext.getStore('EmailSettings').reload();
                    }
                })
            });
        }
    },

    resetEmailAccount :function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];
        if(rec != null){
            Ext.msg.confirm('警告','你确定设置该账号为当前账号？',function(){
                Ext.Ajax.request({
                    url:'',
                    method:'POST',
                    params:{
                        id:rec.id
                    },
                    success: function(response){
                        Ext.Msg.alert('提示', '设置成功！');
                    }
                })
            });
        }
    }
});