/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ApplyRecordManage.store.ApplyRecords', {
    extend: 'Ext.data.Store',
    model: 'ApplyRecordManage.model.ApplyRecord',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listApplyForm,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'formVOList',
            successProperty: 'success'
        }
    },

    listeners: {
        'beforeload': function (store, op, options) {

            var combo = Ext.getCmp('applyFormCombo');
            var formName = combo.value;
            var params = {
                formName:formName
            };
            Ext.apply(store.proxy.extraParams,params);
        }
    }
});