/**
 * Created by hao on 2015/12/20.
 */
Ext.define('BuoyManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.buoyGrid',
    store : 'Buoys',
    title: '浮标',
    //selType : 'checkboxmodel',
    id : 'buoy_list_id',
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
        id : 'add-buoy-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-buoy-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-buoy-btn',
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
        id : 'logoUrl',
        header : '浮标',
        dataIndex : 'logoUrl',
        renderer: function(value){
            return '<img src="'+value+'"style="height:50px">';
        }
    },{
        id : 'website',
        header : '网址',
        dataIndex : 'website'
    }],

    initComponent : function() {
        this.callParent(arguments);
    }

});