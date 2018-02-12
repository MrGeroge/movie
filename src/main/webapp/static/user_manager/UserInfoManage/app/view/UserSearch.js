/**
 * Created by hao on 2015/12/18.
 */
Ext.define('UserManage.view.UserSearch', {
    extend : 'Ext.form.Panel',
    alias : 'widget.userSearchForm',
    id:'userSearchForm',
    bodyStyle:'background-color:white',
    items:[{
        xtype:'userSearchFieldSet'
    }],
    buttons:[{
        text:'查询用户',
        action:'searchUser',
        formBind:true
    }]

});