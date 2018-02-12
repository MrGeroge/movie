/**
 * Created by hao on 2015/12/20.
 */
Ext.define('UserManage.view.UserWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.userWin',
    id:'userWin',

    title : '新建用户',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'userForm',
                items:[{
                    xtype:'textfield',
                    name:'username',
                    fieldLabel:'账户名',
                    allowBlank:false,
                    vtype:'email'
                },{
                    xtype:'textfield',
                    name : 'password',
                    fieldLabel: '密码',
                    hidden:true,
                    allowBlank:false,
                    value:'123456'
                },{
                    xtype:'textfield',
                    name : 'tag',
                    fieldLabel: '标记',
                    hidden:true,
                    allowBlank:false,
                    value:'0'
                }]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange',
                formBind:true
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});