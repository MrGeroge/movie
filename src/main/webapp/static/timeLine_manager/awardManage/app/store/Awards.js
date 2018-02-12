/**
 * Created by hao on 2015/12/28.
 */
Ext.define('AwardManage.store.Awards', {
    extend: 'Ext.data.Store',
    model: 'AwardManage.model.Award',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.tl_listAward,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'awardVOList',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});