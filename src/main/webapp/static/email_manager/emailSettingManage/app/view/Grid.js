/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailSettingManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.emailAccountGrid',
    store : 'EmailAccounts',
    title: '账户列表',
    //selType : 'checkboxmodel',
    //singleSelect : true,
    id : 'emailAccount_list_id',
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
        id : 'add-emailAccount-btn',
        text : '<span style="font-size:12px">新建</span>',
        iconCls : 'icon-add',
        disabled : false
    },{
        id : 'edit-emailAccount-btn',
        text : '<span style="font-size:12px">编辑</span>',
        iconCls : 'icon-edit',
        disabled : false
    },{
        id : 'delete-emailAccount-btn',
        text : '<span style="font-size:12px">删除</span>',
        iconCls : 'icon-remove',
        disabled : false
    },{
        id : 'reset-emailAccount-btn',
        text : '<span style="font-size:12px">设为当前账号</span>',
        iconCls : 'icon-lock',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        //hidden : true,
        width : 60
    }, {
        id : 'userName',
        header : '账户',
        dataIndex : 'userName'
    }, {
        header : '密码',
        dataIndex : 'password'
    }, {
        header : '状态',
        dataIndex : 'status',
        render:function(value){
            if(value == 0){
                return '正在使用';
            }else{
                return '';
            }
        }
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});