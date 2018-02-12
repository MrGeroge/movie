/**
 * Created by hao on 2015/12/28.
 */
Ext.define('AwardManage.view.AwardWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.awardWin',
    id:'awardWin',

    title : '奖项设置',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'awardForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'category',
                    fieldLabel:'类别',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name : 'prize',
                    fieldLabel: '奖项',
                    allowBlank:false
                },{
                    xtype:'numberfield',
                    name:'number',
                    fieldLabel:'提奖名额',
                    allowBlank:false,
                    minValue:0
                },{
                    xtype:'textareafield',
                    name:'description',
                    fieldLabel:'描述',
                    allowBlank:false
                },{
                    xtype:'periodNumCombo',
                    name:'periodNum',
                    fieldLabel:'届数'
                    //allowBlank:false
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