/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ApplyRecordManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.applyRecordGrid',
    store : 'ApplyRecords',
    title: '报名记录',
    //selType : 'checkboxmodel',
    //singleSelect : true,
    id : 'applyRecord_list_id',
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
        id : 'view-applyRecord-btn',
        text : '<span style="font-size:12px">查看</span>',
        iconCls : 'icon-search',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'userName',
        header : '用户名',
        dataIndex : 'username'
    },{
        id : 'formName',
        header : '报名表名',
        dataIndex : 'formName'
    },{
        id : 'contentDesc',
        header : '报名内容',
        hidden:true,
        dataIndex : 'contentDesc'
    },{
        header : '创建时间',
        dataIndex : 'createTime'
    }],
    dockedItems : [{
        xtype : 'pagingtoolbar',
        pageSize : 20,
        store : 'ApplyRecords',
        dock : 'bottom',
        displayInfo : true,
        displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
        emptyMsg : '无显示数据'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});