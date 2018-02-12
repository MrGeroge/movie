/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TutorManage.store.Tutors', {
    extend: 'Ext.data.Store',
    model: 'TutorManage.model.Tutor',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listTutor,
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