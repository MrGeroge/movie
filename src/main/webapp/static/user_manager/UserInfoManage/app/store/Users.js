/**
 * Created by hao on 2015/12/17.
 */
Ext.define('UserManage.store.Users', {
    extend: 'Ext.data.Store',
    model: 'UserManage.model.User',
    autoLoad: true,
    pageSize:10,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listAccount,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'accountVOList',
            successProperty: 'success',
            totalProperty : "total"
        }
    },
    listener:{
        'beforeload': function(store,op,options){
            //var userSearchForm = Ext.getCmp('userSearchForm');
            var combo = Ext.getCmp('labelCombo');
            var tagId = combo.value;
            var params = {
                tagId:tagId
                //count:15
            };
            Ext.apply(store.proxy.extraParams,params);
        }
    }
});