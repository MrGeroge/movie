/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TLVolunteerManage.store.Volunteers', {
    extend: 'Ext.data.Store',
    model: 'TLVolunteerManage.model.Volunteer',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.tl_listVolunteer,
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