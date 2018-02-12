/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailSettingManage.store.EmailAccounts', {
    extend: 'Ext.data.Store',
    model: 'EmailSettingManage.model.EmailAccount',
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