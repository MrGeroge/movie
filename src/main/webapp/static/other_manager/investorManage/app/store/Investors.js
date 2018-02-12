/**
 * Created by hao on 2015/12/20.
 */
Ext.define('InvestorManage.store.Investors', {
    extend: 'Ext.data.Store',
    model: 'InvestorManage.model.Investor',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listInvestor,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'investorVOList',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});