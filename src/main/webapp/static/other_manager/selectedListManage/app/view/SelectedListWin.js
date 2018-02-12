/**
 * Created by hao on 2015/12/21.
 */
Ext.define('SelectedListManage.view.SelectedListWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.selectedListWin',
    id:'selectedListWin',

    title : '入选名单',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'selectedListForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'name',
                    fieldLabel:'入选影片',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name : 'author',
                    fieldLabel: '作者',
                    allowBlank:false
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