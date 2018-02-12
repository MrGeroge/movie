Ext.define('LabelManage.controller.Labels', {
    extend: 'Ext.app.Controller',

    models:['Label'],
    stores:['Labels'],
    views:['AddLabel',
            'AddLabelSet',
            'Grid',
            'EditLabelForm',
            'EditLabelWin'],

    init: function(){
        this.control({
            'labelGrid':{
                itemdblclick:this.editLabel
            },

            'addLabelForm button[action=addLabel]':{
                click:this.addLabel
            },
            'editLabelWin button[action=saveChange]':{
                click:this.updateLabel
            },

            '#edit-label-btn':{
                click:this.editLabel
            },
            '#delete-label-btn':{
                click:this.deleteLabel
            }
        });
    },

    addLabel: function(button){
        var form = Ext.getCmp('addLabelForm');
        var record = form.getRecord();
        var values = form.getValues();
        var name = values.name;

        if(form.getForm().isValid()) {
            Ext.Ajax.request({
                url: ip.addTag,
                method: 'POST',
                params: {
                    name: name
                },
                success: function (response) {
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Labels').reload();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }

        /*if(record != null){
            record.set(values);
        }else{
            var newrecord = new Ext.create('LabelManage.model.Label',values);
            this.getLabelsStore().add(newrecord);
            record = newrecord;
        }

        this.getLabelsStore().sync({
            success : function ( batch ,option ){
                //console.log( 'success' ) ;
                Ext.Msg.alert('提示', '保存成功！');
                this.getLabelsStore().reload();
            } ,
            failure : function ( batch ,option ){
                //console.log( 'failure...' ) ;
                Ext.Msg.alert('提示', '管理操作失败！');
                this.getLabelsStore().rejectChanges() ; // rollback...
            } ,
            scope : this
        });*/
    },

    editLabel : function(button){
        var win = Ext.widget('editLabelWin');
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];
        win.down('form').loadRecord(rec);
        win.show();
    },

    updateLabel : function(button){

        var form = Ext.getCmp('editLabelForm');
        var record = form.getRecord();
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:ip.updateTag,
                method:'put',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                }
            });
        }*/
        if(form.getForm().isValid()){
            $.ajax({
                url: ip.updateTag+"/"+values.id,
                type:'put',
                contentType:'application/json',
                data:'{"name":"'+values.name+'"}',
                success:function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Labels').reload();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
        var win = button.up('window');
        win.close();
    },

    deleteLabel : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }else{
            var records = grid.getSelectionModel().getSelection();
            var rec = records[0];
            /*Ext.Ajax.request({
                url:ip.deleteTag,
                method:'POST',
                params:{
                    tag_id:rec.id
                },

                success:function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                }
            });*/

            Ext.Msg.confirm('警告','你确定删除？',function(button) {
                if(button == 'yes') {
                    $.ajax({
                        url: ip.updateTag + "/" + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Labels').reload();
                        }
                    });
                }else{}
            });
        }
    }
});