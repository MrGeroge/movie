/**
 * Created by hao on 2015/12/21.
 */
Ext.define('SelectedListManage.controller.SelectedLists', {
    extend: 'Ext.app.Controller',

    models:['SelectedList'],
    stores:['SelectedLists'],
    views:['Grid',
        'SelectedListWin',
        'SelectedListUpdateWin'
    ],

    init: function(){
        this.control({
            '#add-selectedList-btn':{
                click:this.addSelectedList
            },

            '#edit-selectedList-btn':{
                click:this.editSelectedList
            },

            '#delete-selectedList-btn':{
                click:this.deleteSelectedList
            },

            'selectedListWin button[action=saveChange]':{
                click:this.createSelectedList
            },
            'selectedListUpdateWin button[action=saveChange]':{
                click:this.updateSelectedList
            }
        });
    },

    addSelectedList : function(){
        var win = Ext.widget('selectedListWin');
        win.show();
    },

    editSelectedList : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('selectedListUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createSelectedList: function(button){
        var form = Ext.getCmp('selectedListForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()) {
            Ext.Ajax.request({
                url:ip.addEnter,
                method:'POST',
                params:{
                    name:values.name,
                    author:values.author
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('SelectedLists').reload();
                    var win = Ext.getCmp('selectedListWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    updateSelectedList: function(button){
        var form = Ext.getCmp('selectedListUpdateForm');
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
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateEnter + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"name":"' + values.name + '","author":"' + values.author + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('SelectedLists').reload();
                    var win = Ext.getCmp('selectedListUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteSelectedList: function(button){
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
                        url: ip.deleteEnter + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('SelectedLists').reload();
                        }
                    });
                }
            });
        }
    }
});