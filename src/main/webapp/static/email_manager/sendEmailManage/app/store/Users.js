/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.store.Users', {
    extend: 'Ext.data.Store',
    model: 'EmailManage.model.User',
    autoLoad: true,
    //pageSize:20,
    proxy: {
        type: 'ajax',
        api: {
            read: '#',
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