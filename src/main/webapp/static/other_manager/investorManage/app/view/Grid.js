/**
 * Created by hao on 2015/12/20.
 */
Ext.define('InvestorManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.InvestorGrid',
    store : 'Investors',
    title: '投资方',
    //selType : 'checkboxmodel',
    //singleSelect : true,
    id : 'investor_list_id',
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
        id : 'add-investor-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-investor-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-investor-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'name',
        header : '名称',
        dataIndex : 'name',
        width: 100
    }, {
        header : '介绍',
        dataIndex : 'introduction'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});