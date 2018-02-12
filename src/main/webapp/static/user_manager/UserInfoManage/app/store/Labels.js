/**
 * Created by hao on 2015/12/20.
 */
Ext.define('UserManage.store.Labels', {
    extend: 'Ext.data.Store',
    model: 'UserManage.model.Label',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listTag,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'object',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});