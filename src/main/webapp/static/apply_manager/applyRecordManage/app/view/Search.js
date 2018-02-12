/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ApplyRecordManage.view.Search', {
    extend : 'Ext.form.Panel',
    alias : 'widget.applyRecordSearchForm',
    id:'applyRecordSearchForm',
    bodyStyle:'background-color:white',
    items:[{
        xtype:'applyRecordSearchFieldSet'
    }],
    buttons:[{
        text:'查询',
        action:'searchApplyRecord'
    }]

})