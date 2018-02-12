/**
 * Created by hao on 2015/12/21.
 */
Ext.define('MapLightManage.view.Grid', {
    extend : 'Ext.grid.Panel',
    alias : 'widget.mapLightGrid',
    store : 'MapLights',
    title: '地图点亮列表',
    //selType : 'checkboxmodel',
    id : 'mapLight_list_id',
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
        id : 'set-mapLight-btn',
        text : '<span style="font-size:12px">点亮地图</span>',
        iconCls : 'icon-lock',
        disabled : false
    }],
    columns : [{
        header : '编号',
        dataIndex : 'id',
        //hidden : true,
        width : 60
    }, {
        id : 'province',
        header : '省份',
        dataIndex : 'province'
    }, {
        header : '状态',
        dataIndex : 'status',
        renderer:function(value){
            if(value == true){
                return '已亮';
            }else{
                return '--';
            }
        }
    }],
    initComponent : function() {
        this.callParent(arguments);
    }

});