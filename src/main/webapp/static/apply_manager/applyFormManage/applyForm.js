/**
 * Created by hao on 2015/12/18.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'ApplyFormManage',

    controllers: [
        'ApplyForms'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            bodyStyle : 'background-color:white',
            items : [{
                region : 'center',
                xtype : 'applyFormGrid'
            }
            ]
        });
    }
});