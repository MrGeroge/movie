/**
 * Created by hao on 2015/12/18.
 */
Ext.define('MovieManage.store.Movies', {
    extend: 'Ext.data.Store',
    model: 'MovieManage.model.Movie',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listMovie,
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