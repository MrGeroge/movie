/**
 * Created by hao on 2015/12/18.
 */
Ext.define('ApplyFormManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.applyFormGrid',
    store : 'ApplyForms',
    title: '报名表列表',
    //selType : 'checkboxmodel',
    //singleSelect : true,
    id : 'applyForm_list_id',
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
    tbar : [/*{
        id : 'add-applyForm-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },*/{
        id : 'edit-applyForm-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    }/*,{
        id : 'delete-applyForm-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }*/],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        //hidden : true,
        width : 60
    }, {
        id : 'formName',
        header : '报名表名',
        dataIndex : 'formName'
    },{
        header : '创建时间',
        dataIndex : 'createTime'
        /*renderer:function(value) {
            return Ext.Date.format(value, 'Y-m-d');
        }*/
    }, {
        id : 'controlDesc',
        header : '报名表内容',
        dataIndex : 'controlDesc',
        hidden:true
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});