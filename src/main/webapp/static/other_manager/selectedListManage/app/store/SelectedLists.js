/**
 * Created by hao on 2015/12/21.
 */
Ext.define('SelectedListManage.store.SelectedLists', {
    extend: 'Ext.data.Store',
    model: 'SelectedListManage.model.SelectedList',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listEnter,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'enterVOList',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});