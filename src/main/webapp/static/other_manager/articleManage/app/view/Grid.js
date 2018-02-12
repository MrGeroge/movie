/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.articleGrid',
    store : 'Articles',
    title: '新闻列表',
    //selType : 'checkboxmodel',
    id : 'Article_list_id',
    border : false,
    enableColumnHide : false,
    forceFit : true,
    viewConfig : {
        loadMask : {
            msg : '加载数据中，请稍候...'
        }
    },
    autoShow : true,
    loadMask : true,
    autoScroll : true,
    containerScroll : true,
    tbar : [{
        id : 'add-article-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-article-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-article-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    },{
        id : 'view-article-btn',
        text : '<span style="font-size:12px">更多..</span>',
        iconCls : 'icon-search',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'coverUrl',
        header : '封面',
        dataIndex : 'coverUrl',
        renderer:function(value){
            return '<img src="'+value+'"style="height:150px">'
        }
    }, {
        header : '标题',
        dataIndex : 'title'
    }, {
        header : '创建时间',
        dataIndex : 'createTime'
        /*renderer:function(value){
            return Ext.Date.format(value, 'Y-m-d');
        }*/
    }, {
        header : '浏览次数',
        dataIndex : 'browseNum'
    },{
        header : '内容',
        dataIndex : 'content',
        hidden:true
    }],
    dockedItems : [{
        xtype : 'pagingtoolbar',
        pageSize : 10,
        store : 'Articles',
        dock : 'bottom',
        displayInfo : true,
        displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
        emptyMsg : '无显示数据'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});