/**
 * Created by hao on 2015/12/18.
 */
Ext.define('ApplyFormManage.controller.ApplyForms', {
    extend: 'Ext.app.Controller',

    models:['ApplyForm'],
    stores:['ApplyForms'],
    views:[ 'Grid',
            'ApplyFormWin',
            'ApplyFormUpdate'
            ],

    init: function(){
        this.control({
            '#add-applyForm-btn':{
                click:this.addApplyForm
            },

            '#edit-applyForm-btn':{
                click:this.editApplyForm
            },

            'applyFormWin button[action=saveChange]':{
                click:this.createTextField
            },
            'applyFormUpdate button[action=saveChange]':{
                click:this.UpdateTextField
            }
        });
    },

    addApplyForm : function(){
        var win = Ext.widget('applyFormWin');
        win.show();
    },

    editApplyForm : function(button){

        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('applyFormUpdate');

        win.show();
        var frm = document.getElementById('applyUpdateFrame').contentWindow;
        frm.onload = function(){
            frm.document.getElementById('sortable').innerHTML = rec.data.controlDesc;
        }
    },

    createTextField: function(button){
        var frm = document.getElementById('applyFrame').contentWindow;
        var htmlCode = frm.document.getElementById('sortable');

        var applyName = frm.document.getElementById("selects").value;

        //alert(htmlCode.innerHTML);
        Ext.Ajax.request({
             url:ip.addForm,
             method:'POST',
             params:{
                 form_name:applyName,
                 control_desc:htmlCode.innerHTML
             },
             success: function(response){
                Ext.Msg.alert('提示', '保存成功！');
                 Ext.getStore('ApplyForms').reload();
                 var win = Ext.getCmp('applyForm');
                 win.close();
             }
         });
    },

    UpdateTextField:function(button){
        var frm = document.getElementById('applyUpdateFrame').contentWindow;
        var htmlCode = frm.document.getElementById('sortable').innerHTML;

        var grid = Ext.getCmp('applyForm_list_id');
        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];
        //console.log(htmlCode);
        var p = {
            "controlDesc":htmlCode
        };
        p = JSON.stringify(p);

        $.ajax({
            url: ip.updateForm+rec.data.id,
            type:'put',
            contentType:'application/json',
            data:p,
            success:function(response){
                Ext.Msg.alert('提示', '更新成功！');
                Ext.getStore('ApplyForms').reload();
                var win = Ext.getCmp('applyFormUpdate');
                win.close();
            }
        });
    }

});