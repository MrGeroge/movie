/**
 * Created by hao on 2015/12/20.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'ArticleManage',

    controllers: [
        'Articles'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            bodyStyle : 'background-color:white',
            items : [/*{
             region : 'east',
             xtype : 'userPanel'

             },*/{
                region : 'center',
                xtype : 'articleGrid'

            }
            ]
        });
    }
});