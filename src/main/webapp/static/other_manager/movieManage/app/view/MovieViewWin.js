/**
 * Created by hao on 2015/12/20.
 */
Ext.define('MovieManage.view.MovieViewWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.movieViewWin',
    id:'movieViewWin',

    title : '查看',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'movieForm',
                items:[{
                    xtype:'textareafield',
                    name : 'attribute',
                    fieldLabel: '属性串',
                    readOnly:true
                },{
                    xtype:'textareafield',
                    name : 'group',
                    fieldLabel: '制作班底',
                    readOnly:true
                },{
                    xtype:'textareafield',
                    name : 'description',
                    fieldLabel: '影片简介',
                    readOnly:true
                },{
                    xtype:'textareafield',
                    name : 'state',
                    fieldLabel: '导演阐述',
                    readOnly:true
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