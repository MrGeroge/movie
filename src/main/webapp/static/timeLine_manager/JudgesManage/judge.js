/**
 * Created by hao on 2015/12/20.
 */
Ext.application({
    requires: 'Ext.container.Viewport',
    name: 'TLJudgeManage',

    controllers: [
        'Judges'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout : 'border',
            bodyStyle : 'background-color:white',
            items : [/*{
             region : 'north',
             xtype : 'applyRecordSearchForm'
             },*/{
                region : 'center',
                xtype : 'judgeGrid'
            }
            ]
        });
    }
});