/**
 * Created by hao on 2015/12/17.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'UserManage',

    controllers: [
        'Users'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            bodyStyle : 'background-color:white',
            items : [{
                region : 'north',
                xtype : 'userSearchForm'

            },{
                region : 'center',
                xtype : 'userGrid'

            }
            ]
        });
    }
});