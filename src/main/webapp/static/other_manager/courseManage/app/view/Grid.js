/**
 * Created by hao on 2015/12/21.
 */
Ext.define('CourseManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.courseGrid',
    store : 'Courses',
    title: '课程',
    //selType : 'checkboxmodel',
    id : 'course_list_id',
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
        id : 'add-course-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-course-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-course-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    }/*,{
        id : 'reset-emailAccount-btn',
        text : '<span style="font-size:12px">设为当前账号</span>',
        iconCls : 'icon-lock',
        disabled : false
    }*/],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'startTime',
        header : '时间',
        dataIndex : 'startTime'
    }, {
        header : '课程方向',
        dataIndex : 'direction'
    }, {
        header : '形式',
        dataIndex : 'pattern'
    }, {
        header : '导师',
        dataIndex : 'mentorName'
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});