/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.view.UserSearchForm', {
    extend : 'Ext.form.Panel',
    alias : 'widget.userSearchForm',
    id:'userSearchForm',
    bodyStyle:'background-color:white',
    items:[{
        xtype:'userSearch'
    }],
    buttons:[{
        text:'查询',
        action:'search'
    }]

})