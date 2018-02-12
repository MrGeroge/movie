/**
 * Created by hao on 2015/12/28.
 */
Ext.define('AwardManage.controller.Awards', {
    extend: 'Ext.app.Controller',

    models:['Award'],
    stores:['Awards'],
    views:['Grid',
        'AwardWin',
        'AwardUpdateWin'
    ],

    init: function(){
        this.control({
            '#add-award-btn':{
                click:this.addAward
            },

            '#edit-award-btn':{
                click:this.editAward
            },

            '#delete-award-btn':{
                click:this.deleteAward
            },

            'awardWin button[action=saveChange]':{
                click:this.createAward
            },
            'awardUpdateWin button[action=saveChange]':{
                click:this.updateAward
            }
        });
    },

    addAward : function(){
        var win = Ext.widget('awardWin');
        win.show();
    },

    editAward : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('awardUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createAward: function(button){
        var form = Ext.getCmp('awardForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()) {
            Ext.Ajax.request({
                url:ip.tl_addAward,
                method:'POST',
                params:{
                    category:values.category,
                    prize:values.prize,
                    number:values.number,
                    description:values.description,
                    periodNum:values.periodNum
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Awards').reload();
                    var win = Ext.getCmp('awardWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    updateAward: function(button){
        var form = Ext.getCmp('awardUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
         Ext.Ajax.request({
         url:'',
         method:'POST',
         extraParams:values,
         success: function(response){
         Ext.Msg.alert('提示', '更新成功！');
         Ext.getStore('SelectedLists').reload();
         }
         });
         }*/
        var d = {
            "category":values.category,
            "prize":values.prize,
            "number":values.number,
            "description":values.description,
            "periodNum":values.periodNum
        }
        d = JSON.stringify(d);
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.tl_updateAward + values.id,
                type: 'put',
                contentType: 'application/json',
                data: d,
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Awards').reload();
                    var win = Ext.getCmp('awardUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteAward: function(button){
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
             Ext.getStore('SelectedLists').reload();
             }
             })
             });*/
            Ext.Msg.confirm('警告','你确定删除？',function(button) {
                if(button == 'yes') {
                    $.ajax({
                        url: ip.tl_deleteAward + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Awards').reload();
                        }
                    });
                }
            });
        }
    }
});