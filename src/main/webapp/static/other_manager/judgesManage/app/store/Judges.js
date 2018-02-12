/**
 * Created by hao on 2015/12/21.
 */
Ext.define('JudgeManage.store.Judges', {
    extend: 'Ext.data.Store',
    model: 'JudgeManage.model.Judge',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listJudge,
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