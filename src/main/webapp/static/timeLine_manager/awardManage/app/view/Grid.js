/**
 * Created by hao on 2015/12/28.
 */
Ext.define('AwardManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.awardGrid',
    store : 'Awards',
    title: '奖项',
    //selType : 'checkboxmodel',
    id : 'award_list_id',
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
        id : 'add-award-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-award-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-award-btn',
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
        id : 'category',
        header : '分类',
        dataIndex : 'category'
    }, {
        header : '奖项',
        dataIndex : 'prize'
    }, {
        header : '提奖名额',
        dataIndex : 'number'
    }, {
        header : '描述',
        dataIndex : 'description'
    }, {
        header : '届数',
        dataIndex : 'periodNum'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});