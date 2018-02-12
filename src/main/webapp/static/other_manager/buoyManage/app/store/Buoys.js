/**
 * Created by hao on 2015/12/20.
 */
Ext.define('BuoyManage.store.Buoys', {
    extend: 'Ext.data.Store',
    model: 'BuoyManage.model.Buoy',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listBuoy,
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