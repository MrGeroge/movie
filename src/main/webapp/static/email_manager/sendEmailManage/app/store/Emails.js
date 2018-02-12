/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.store.Emails', {
    extend: 'Ext.data.Store',
    model: 'EmailManage.model.Email',
    //autoLoad: true,
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