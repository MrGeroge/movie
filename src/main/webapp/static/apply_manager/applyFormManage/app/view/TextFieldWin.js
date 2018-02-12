/**
 * Created by hao on 2015/12/18.
 */
Ext.define('ApplyFormManage.view.TextFieldWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.textFieldWin',

    title : '文本框设置',
    layout: 'fit',
    autoScroll:true,
    modal: true,
    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'textFieldForm',
                items:[{
                    xtype:'textfield',
                    name:'textName',
                    fieldLabel:'文本框名称',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name:'placeHold',
                    fieldLabel:'提示信息'
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