/**
 * Created by hao on 2015/12/28.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'AwardManage',

    controllers: [
        'Awards'
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
                xtype : 'awardGrid'

            }
            ]
        });
    }
});