/**
 * Created by hao on 2015/12/20.
 */
Ext.define('InvestorManage.view.InvestorUpdateWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.investorUpdateWin',
    id:'investorUpdateWin',

    title : '编辑投资方',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'investorUpdateForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'name',
                    fieldLabel:'名称',
                    allowBlank:false
                },{
                    xtype:'textareafield',
                    name : 'introduction',
                    fieldLabel: '介绍',
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