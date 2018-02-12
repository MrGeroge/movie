/**
 * Created by hao on 2015/12/18.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'EmailSettingManage',

    controllers: [
        'EmailSetting'
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
                xtype : 'emailAccountGrid'

            }
            ]
        });
    }
});