/**
 * Created by hao on 2015/12/18.
 */
/**
 * Created by hao on 2015/12/17.
 */
Ext.define('ApplyFormManage.store.ApplyForms', {
    extend: 'Ext.data.Store',
    model: 'ApplyFormManage.model.ApplyForm',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listForm,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'object',
            successProperty: 'success'
        }
    }
});