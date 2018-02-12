/**
 * Created by hao on 2015/12/17.
 */
Ext.define('LabelManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.labelGrid',
    store : 'Labels',
    title: '标签列表',
    //selType : 'checkboxmodel',
    id : 'label_list_id',
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
        id : 'edit-label-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-application-edit',
        disabled : false
    },{
        id : 'delete-label-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        //hidden : true,
        width :50
    }, {
        id : 'name',
        header : '标签名',
        dataIndex : 'name',
        width:100
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});

