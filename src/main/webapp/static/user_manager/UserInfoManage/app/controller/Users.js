/**
 * Created by hao on 2015/12/17.
 */
Ext.define('UserManage.controller.Users', {
    extend: 'Ext.app.Controller',

    models:['User','Label'],
    stores:['Users','Labels'],
    views:[ 'Grid',
            'UserSearch',
            'UserSearchSet',
            'UserWin',
            'EditLabels',
            'LabelCombo',
            'LabelComboCopy'],

    init: function(){
        this.control({
            '#add-user-btn':{
                click:this.addUser
            },
            '#relatedLabel-user-btn':{
                click:this.relateLabel
            },

            'userSearchForm button[action=searchUser]':{
                click:this.searchUser
            },
            'userWin button[action=saveChange]':{
                click:this.createUser
            },
            'editLabel button[action=save]':{
                click:this.saveLabels
            }
        });
    },

    addUser : function(){
        var win = Ext.widget('userWin');
        win.show();
    },

    createUser: function(button){
        var form = Ext.getCmp('userForm');
        var record = form.getRecord;
        var username = form.getForm().findField('username').value;
        var password = form.getForm().findField('password').value;

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.register,
                method:'POST',
                params:{
                    username:username,
                    password:password
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Users').reload();
                    Ext.getCmp('userWin').close();
                }
            });
        }else{
            Ext.Msg.alert('提示','用户格式有误！');
        }
    },

    searchUser: function(button){
        var combo = Ext.getCmp('labelCombo');
        var tagId = combo.value;

        var store = Ext.getStore('Users');
        var userSearchForm = Ext.getCmp('userSearchForm');
        //Ext.apply(store.proxy.extraParams,userSearchForm.getValues());
        store.load({
            params:{
                tagId:tagId
            }
        });

        //if(record != null){
            /*Ext.Ajax.request({
                url:ip.listAccountByTag+tagId,
                method:'GET',
                params:{
                    page:1,
                    count:10
                },
                success: function(response){
                    var obj = eval('('+response.responseText+')');

                    var array = {"total":obj.total,"accountVOList":obj.accountVOList};
                    console.log(array);
                    //array = JSON.stringify(array);
                    //console.log(array);
                    //store.loadData('{"accountVOList":'+obj.accountVOList+'}');
                    store.loadRawData(array,false);
                }
            });*/
       // }
    },

    relateLabel: function(button){
        var grid = button.up('grid');
        console.log(grid);

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        grid.store.reload();
        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];
        console.log(rec);
        var tags = rec.get('tags');
        var win = Ext.widget('editLabel');
        var SC = Ext.getCmp('selectedLabels');

        for(var i=0; i<tags.length; i++){
            var tag = tags[i];
            var newItem = Ext.create('Ext.form.field.Checkbox', {
                boxLabel  : tag.name,
                name      : 'labelName',
                inputValue: tag.id,
                checked : true
            });
            SC.add(newItem);
        }


        var form = win.down('form');
        form.getForm().findField('id').setValue(rec.get('id'));

        win.show();
    },

    saveLabels : function(button){
        var win = button.up('window');
        var form = win.down('form');
        var userId = form.getForm().findField('id').value;
        var tagIds="";

        if(form.getForm().isValid()){
            var tags = Ext.getCmp('selectedLabels').getChecked();
            var array = new Array(tags.length);
            for(var i=0;i<tags.length;i++){
                /*if(i != tags.length-1){
                    tagIds += tags.get(i).inputValue + ";";
                }else{
                    tagIds += tags.get(i).inputValue;
                }*/
                array[i] = tags[i].inputValue;
            }

            $.ajax({
                url: ip.editUserTag+userId,
                type:'put',
                contentType:'application/json',
                //data:'{"tagids":"'+tagIds+'"}',
                data:JSON.stringify(array),
                success:function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    var selectionModel = Ext.getCmp('user_list_id').getSelectionModel();
                    selectionModel.deselectAll();
                    Ext.getStore('Users').load();
                    win.close();
                }
            });
        }
    }
});