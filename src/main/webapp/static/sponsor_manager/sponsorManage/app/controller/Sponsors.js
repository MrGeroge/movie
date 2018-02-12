/**
 * Created by hao on 2015/12/20.
 */
Ext.define('SponsorManage.controller.Sponsors', {
    extend: 'Ext.app.Controller',

    models:['Sponsor'],
    stores:['Sponsors'],
    views:[ 'Grid',
            'SponsorWin',
            'SponsorUpdateWin',
            'PictureUpload'
    ],

    init: function(){
        this.control({
             '#add-sponsor-btn':{
                click:this.addSponsor
             },

             '#edit-sponsor-btn':{
                click:this.editSponsor
             },

             '#delete-sponsor-btn':{
                click:this.deleteSponsor
             },

             'sponsorWin button[action=saveChange]':{
                click:this.createSponsor
             },
             'sponsorUpdateWin button[action=saveChange]':{
                click:this.updateSponsor
             },
            'pictureUpload button[action=upload]':{
                click:this.closeUploadWin
            }
        });
    },

    addSponsor : function(){
         var win = Ext.widget('sponsorWin');
         win.show();
    },

    editSponsor : function(button){
         var grid = button.up('grid');

         var flag = grid.getSelectionModel().hasSelection();
         if(!flag){
         Ext.Msg.alert('提示','请选择一项');
            return;
        }

         var records = grid.getSelectionModel().getSelection();
         var rec = records[0];


         var win = Ext.widget('sponsorUpdateWin');
         win.down('form').loadRecord(rec);
         win.show();
        var picture = Ext.getCmp('viewPicture');
        picture.getEl().dom.src = rec.data.logoUrl;
     },

    createSponsor: function(button){
         var form = Ext.getCmp('sponsorForm');
         var record = form.getRecord;
         var values = form.getValues();

        if(form.getForm().isValid()){
             Ext.Ajax.request({
                 url:ip.addSponsor,
                 method:'POST',
                 params:{
                     logoUrl:values.logoUrl,
                     website:values.website
                 },
                 success: function(response){
                     Ext.Msg.alert('提示', '保存成功！');
                     Ext.getStore('Sponsors').reload();
                     var win = Ext.getCmp('sponsorWin');
                     win.close();
                 }
             });
         }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateSponsor: function(button){
        var form = Ext.getCmp('sponsorUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*//if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Sponsors').reload();
                }
            });
        //}*/
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateSponsor + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"logoUrl":"' + values.logoUrl + '","website":"' + values.website + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Sponsors').reload();
                    var win = Ext.getCmp('sponsorUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteSponsor: function(button){
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
                            Ext.getStore('Sponsors').reload();
                        }
                    });
            });*/
            Ext.Msg.confirm('警告','你确定删除？',function(button) {
                if(button=='yes') {
                    $.ajax({
                        url: ip.deleteSponsor + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Sponsors').reload();
                        }
                    });
                }else{

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
                    var sponsorForm = Ext.getCmp('sponsorForm');
                    var sponsorUpdateForm = Ext.getCmp('sponsorUpdateForm');
                    if(sponsorForm != null) {
                        var data = eval('('+action.response.responseText+')');
                        //alert(data);
                        sponsorForm.getForm().findField('logoUrl').setValue(data.link);
                        var picture = Ext.getCmp('viewPicture');
                        picture.getEl().dom.src = data.link;
                    }
                    if(sponsorUpdateForm != null){
                        var data = eval('('+action.response.responseText+')');
                        sponsorUpdateForm.getForm().findField('logoUrl').setValue(data.link);
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