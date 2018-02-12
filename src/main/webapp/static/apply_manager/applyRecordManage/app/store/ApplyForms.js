/**
 * Created by hao on 2015/12/28.
 */
Ext.define('ApplyRecordManage.store.ApplyForms', {
    extend: 'Ext.data.Store',
    model: 'ApplyRecordManage.model.ApplyForm',
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
            root: 'formSimpleVOList',
            successProperty: 'success'
        }
    }
});