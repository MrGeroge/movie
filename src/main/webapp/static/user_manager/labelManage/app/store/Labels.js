/**
 * Created by hao on 2015/12/17.
 */
Ext.define('LabelManage.store.Labels', {
    extend: 'Ext.data.Store',
    model: 'LabelManage.model.Label',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listTag,
            create: ip.addTag,
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            //root: 'object',
            successProperty: 'success'
            //root : "teacherList",
        },
        writer:{
            type:'json'
        }
    }
});