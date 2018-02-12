/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.model.Article', {
    extend: 'Ext.data.Model',
    fields: ['id', 'coverUrl','title','browseNum','categoryName','content',
        {name:'createTime',type:'string'}]

});