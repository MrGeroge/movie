/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ApplyRecordManage.controller.ApplyRecords', {
    extend: 'Ext.app.Controller',

    models:['ApplyRecord','ApplyForm'],
    stores:['ApplyRecords','ApplyForms'],
    views:[ 'Grid',
            'ApplyFormCombo',
            'Search',
            'SearchSet',
            'ApplyRecordWin'
    ],

    init: function(){
        this.control({
            '#view-applyRecord-btn':{
                click:this.viewApplyRecord
            },

            'applyRecordSearchForm button[action=searchApplyRecord]':{
                click:this.searchApplyRecord
            }
        });
    },

    viewApplyRecord : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('applyRecordWin');
        win.show();
        var frm = document.getElementById('applyRecordFrame').contentWindow;
        frm.onload = function() {
            frm.document.getElementById('applyRecord').innerHTML = rec.data.contentDesc;
        }
        //win.show();
    },

    searchApplyRecord: function(button){
        var combo = Ext.getCmp('applyFormCombo');
        var formName = combo.value;

        var store = Ext.getStore('ApplyRecords');
        store.load({
            params:{
                formName:formName
            }
        });
    }

});