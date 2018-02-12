/**
 * Created by hao on 2015/12/17.
 */
Ext.define('UserManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.userGrid',
    store : 'Users',
    title: '用户列表',
    //selType : 'checkboxmodel',
    id : 'user_list_id',
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
        id : 'add-user-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-user-add',
        disabled : false
    },/*{
        id : 'edit-user-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-user-edit',
        disabled : false
    },{
        id : 'delete-user-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-user-delete',
        disabled : false
    },*/{
        id : 'relatedLabel-user-btn',
        text : '<span style="font-size:12px">标签</span>',
        iconCls : 'icon-tag-blue',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'name',
        header : '用户名',
        dataIndex : 'name'
    }, /*{
        header : '头像',
        dataIndex : 'avater_url',
        render: function(value){
            return '<img src="'+value+'">';
        }
    },*/{
        header : '性别',
        dataIndex : 'sex'
    }, {
        id : 'birthday',
        header : '出生日期',
        dataIndex : 'birthday'
    }, {
        id : 'identity',
        header : '身份证号',
        dataIndex : 'identity'
    }, {
        id : 'address',
        header : '地址',
        dataIndex : 'address'
    }, {
        id : 'phone',
        header : '电话',
        dataIndex : 'phone'
    }],
    dockedItems : [{
        xtype : 'pagingtoolbar',
        pageSize : 10,
        store : 'Users',
        dock : 'bottom',
        displayInfo : true,
        displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
        emptyMsg : '无显示数据'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});
