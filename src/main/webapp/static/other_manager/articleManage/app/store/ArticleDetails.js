/**
 * Created by hao on 2015/12/24.
 */
Ext.define('ArticleManage.store.ArticleDetails', {
    extend: 'Ext.data.Store',
    model: 'ArticleManage.model.ArticleDetail',
    //autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.getArticleDetail,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'articleDetailVO',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});