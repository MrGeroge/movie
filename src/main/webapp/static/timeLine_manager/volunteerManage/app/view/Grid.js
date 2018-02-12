/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TLVolunteerManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.volunteerGrid',
    store : 'Volunteers',
    title: '志愿者列表',
    //selType : 'checkboxmodel',
    id : 'volunteer_list_id',
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
        id : 'add-volunteer-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-volunteer-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-volunteer-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    },/*,{
        id : 'name',
        header : '姓名',
        dataIndex : 'name'
    },{
        id : 'position',
        header : '职位',
        dataIndex : 'position'
    }*/{
        id : 'avatarUrl',
        header : '头像',
        dataIndex : 'avatarUrl',
        renderer: function(value){
            return '<img src="'+value+'"style="height:150px">';
        }
    },{
        id : 'summary',
        header : '简介',
        dataIndex : 'summary'
    },{
        id : 'periodNum',
        header : '届数',
        dataIndex : 'periodNum'
    } ],

    initComponent : function() {
        this.callParent(arguments);
    }

});