/**
 * Created by hao on 2015/12/18.
 */
Ext.define('MovieManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.movieGrid',
    store : 'Movies',
    title: '电影列表',
    //selType : 'checkboxmodel',
    id : 'Movie_list_id',
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
        id : 'add-movie-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-movie-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-movie-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    },{
        id : 'view-movie-btn',
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
        id : 'name',
        header : '影片名',
        dataIndex : 'name'
    },{
        header : '图片',
        dataIndex : 'imageUrl',
        renderer:function(value){
            return '<img src="'+value+'"style="height:150px">';
        }
    }, {
        header : '分类ID',
        dataIndex : 'kindId',
        hidden:true
    }, {
        header : '分类',
        dataIndex : 'kind'
    }, {
        header : '首映类型',
        dataIndex : 'firstType'
    }, /*{
        header : '属性串',
        dataIndex : 'attribute'
    },*/{
        header : '年代',
        dataIndex : 'year'
    },{
        header : '作者',
        dataIndex : 'author'
    },{
        header : '体裁',
        dataIndex : 'genre'
    },{
        header : '制作班底',
        dataIndex : 'group',
        hidden:true
    },{
        header : '影片简介',
        dataIndex : 'description',
        hidden:true
    },{
        header : '导演阐述',
        dataIndex : 'state',
        hidden:true
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});