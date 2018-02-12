/**
 * Created by hao on 2015/12/21.
 */
Ext.define('SelectedListManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.selectedListGrid',
    store : 'SelectedLists',
    title: '入选名单',
    //selType : 'checkboxmodel',
    id : 'selectedList_list_id',
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
        id : 'add-selectedList-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-selectedList-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-selectedList-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        //hidden : true,
        width : 60
    }, {
        id : 'name',
        header : '入选名单',
        dataIndex : 'name'
    }, {
        header : '作者',
        dataIndex : 'author'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});