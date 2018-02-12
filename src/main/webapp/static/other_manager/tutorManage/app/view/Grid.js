/**
 * Created by hao on 2015/12/21.
 */
Ext.define('TutorManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.tutorGrid',
    store : 'Tutors',
    title: '导师列表',
    //selType : 'checkboxmodel',
    id : 'tutor_list_id',
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
        id : 'add-tutor-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-tutor-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-tutor-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    },{
        id : 'name',
        header : '姓名',
        dataIndex : 'name'
    },{
        id : 'position',
        header : '职位',
        dataIndex : 'position'
    },{
        id : 'summary',
        header : '简介',
        dataIndex : 'summary'
    }, {
        id : 'avatarUrl',
        header : '头像',
        dataIndex : 'avatarUrl',
        renderer: function(value){
            return '<img src="'+value+'"style="height:150px">';
        }
    }],

    initComponent : function() {
        this.callParent(arguments);
    }

});