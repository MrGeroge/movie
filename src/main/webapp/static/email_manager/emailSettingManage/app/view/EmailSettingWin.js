/**
 * Created by hao on 2015/12/20.
 */
Ext.define('EmailSettingManage.view.EmailSettingWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.emailSettingWin',
    id:'emailSettingWin',

    title : '邮件账户设置',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'emailSettingForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'账户ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'userName',
                    fieldLabel:'账户名'
                },{
                    xtype:'textfield',
                    name : 'password',
                    fieldLabel: '密码'
                }]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange'
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