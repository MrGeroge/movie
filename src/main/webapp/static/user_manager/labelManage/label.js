Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'LabelManage',

    controllers: [
        'Labels'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            bodyStyle : 'background-color:white',
            items : [{
                region : 'north',
                xtype : 'addLabelForm'
            }, {
                region : 'center',
                xtype : 'labelGrid'

            }
            ]
        });
    }
});