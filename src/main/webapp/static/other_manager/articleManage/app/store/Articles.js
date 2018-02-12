/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.store.Articles', {
    extend: 'Ext.data.Store',
    model: 'ArticleManage.model.Article',
    autoLoad: true,
    pageSize:10,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listArticle,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'articleVOList',
            successProperty: 'success',
            //root : "teacherList",
            totalProperty : "total"
        }
    }
});