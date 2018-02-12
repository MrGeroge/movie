/**
 * Created by hao on 2015/12/20.
 */

Ext.define('ApplyRecordManage.view.SearchSet', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.applyRecordSearchFieldSet',
    title : '报名表',
    layout : 'column',
    margin : '5 5 5 5',
    collapsible : true,
    items : [{
        layout: 'form',
        columnWidth: .5,
        border: false,
        items: [{
            layout: 'anchor',
            items: [{
                xtype:'applyFormCombo'
            }]
        }
        ]
    }]

});