/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.view.UserPanel',{
    extend:'Ext.panel.Panel',
    alias : 'widget.userPanel',
    //layout: 'fit',
    width:300,
    autoShow: true,
    initComponent: function() {
        this.items = [
            {xtype:'userSearchForm'},
            {
                xtype:'userGrid',
                listeners:{

                }
            }
        ];
        this.callParent(arguments);
    },
    renderTo: Ext.getBody()
});