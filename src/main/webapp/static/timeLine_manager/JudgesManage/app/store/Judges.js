/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TLJudgeManage.store.Judges', {
    extend: 'Ext.data.Store',
    model: 'TLJudgeManage.model.Judge',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.tl_listJudger,
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