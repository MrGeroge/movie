/**
 * Created by hao on 2015/12/20.
 */
Ext.define('BuoyManage.controller.Buoys', {
    extend: 'Ext.app.Controller',

    models:['Buoy'],
    stores:['Buoys'],
    views:[ 'Grid',
        'BuoyWin',
        'BuoyUpdateWin',
        'PictureUpload'
    ],

    init: function(){
        this.control({
            '#add-buoy-btn':{
                click:this.addBuoy
            },

            '#edit-buoy-btn':{
                click:this.editBuoy
            },

            '#delete-buoy-btn':{
                click:this.deleteBuoy
            },

            'buoyWin button[action=saveChange]':{
                click:this.createBuoy
            },
            'buoyUpdateWin button[action=saveChange]':{
                click:this.updateBuoy
            },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addBuoy : function(){
        var win = Ext.widget('buoyWin');
        win.show();
    },

    editBuoy : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('buoyUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createBuoy: function(button){
        var form = Ext.getCmp('buoyForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addBuoy,
                method:'POST',
                params:{
                    logoUrl:values.logoUrl,
                    website:values.website
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Buoys').reload();
                    var win = Ext.getCmp('buoyWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    updateBuoy: function(button){
        var form = Ext.getCmp('buoyUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateBuoy + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"logoUrl":"' + values.logoUrl + '","website":"' + values.website + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Buoys').reload();
                    var win = Ext.getCmp('buoyUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteBuoy: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null) {
            Ext.Msg.confirm('警告', '你确定删除？', function (button) {
                if(button == 'yes') {
                    $.ajax({
                        url: ip.deleteBuoy + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Buoys').reload();
                        }
                    });
                }else{}
            });
        }
    },

    closeUploadWin: function(button){
        var form = button.up('form');

        if(form.isValid()){
            form.submit({
                url: ip.uploadImg,
                waitMsg: '正在上传...',
                success: function(form,action) {
                    //console.log(form);
                    //console.log(action);
                    Ext.Msg.alert('Success', '图片上传成功');
                    var buoyForm = Ext.getCmp('buoyForm');
                    var buoyUpdateForm = Ext.getCmp('buoyUpdateForm');
                    if(buoyForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        buoyForm.getForm().findField('logoUrl').setValue(data.link);
                    }
                    if(buoyUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        buoyUpdateForm.getForm().findField('logoUrl').setValue(data.link);
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