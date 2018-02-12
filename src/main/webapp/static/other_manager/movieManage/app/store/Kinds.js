/**
 * Created by hao on 2015/12/20.
 */
Ext.define('MovieManage.store.Kinds', {
    extend: 'Ext.data.Store',
    model: 'MovieManage.model.Kind',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listKindMovie,
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