/**
 * Created by hao on 2015/12/20.
 */
Ext.define('InvestorManage.controller.Investors', {
    extend: 'Ext.app.Controller',

    models:['Investor'],
    stores:['Investors'],
    views:['Grid',
            'InvestorWin',
            'InvestorUpdateWin'
    ],

    init: function(){
        this.control({
            '#add-investor-btn':{
                click:this.addInvestor
            },

            '#edit-investor-btn':{
                click:this.editInvestor
            },

            '#delete-investor-btn':{
                click:this.deleteInvestor
            },

            'investorWin button[action=saveChange]':{
                click:this.createInvestor
            },
            'investorUpdateWin button[action=saveChange]':{
                click:this.updateInvestor
            }
        });
    },

    addInvestor : function(){
        var win = Ext.widget('investorWin');
        win.show();
    },

    editInvestor : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('investorUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createInvestor: function(button){
        var form = Ext.getCmp('investorForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addInvestor,
                method:'POST',
                //extraParams:values,
                params:{
                    name:values.name,
                    introduction:values.introduction
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Investors').reload();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateInvestor: function(button){
        var form = Ext.getCmp('investorUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Investors').reload();
                }
            });
        }*/
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateInvestor + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"name":"' + values.name + '","introduction":"' + values.introduction + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Investors').reload();
                    var win = Ext.getCmp('investorUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }

    },

    deleteInvestor: function(button){
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
                        Ext.getStore('Investors').reload();
                    }
                })
            });*/
            Ext.Msg.confirm('警告','你确定删除？',function(button) {
                if(button == 'yes') {
                    $.ajax({
                        url: ip.deleteInvestor + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Investors').reload();
                        }
                    });
                }
            });
        }
    }
});