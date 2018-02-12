/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.view.UserGrid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.userGrid',
    store : 'Users',
    //title: '用户列表',
    //selType : 'checkboxmodel',
    id : 'user_list_id',
    border : false,
    enableColumnHide : true,
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
    width:300,
    columns : [{
        id : 'id',
        header :'编号' ,
        dataIndex : 'id',
        hidden : true,
        width : 60
    }, {
        id : 'userName',
        header : '用户名',
        dataIndex : 'name',
        hidden : true
    }],
    /*dockedItems : [{
     xtype : 'pagingtoolbar',
     pageSize : 20,
     store : 'Teachers',
     dock : 'bottom',
     displayInfo : true,
     displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
     emptyMsg : '无显示数据'
     }],*/
    initComponent : function() {
        this.callParent(arguments);
    }

});
