/**
 * Created by hao on 2015/12/20.
 */
Ext.define('SponsorManage.store.Sponsors', {
    extend: 'Ext.data.Store',
    model: 'SponsorManage.model.Sponsor',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listSponsor,
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