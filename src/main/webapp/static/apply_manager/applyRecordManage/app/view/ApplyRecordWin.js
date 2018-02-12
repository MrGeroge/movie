/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ApplyRecordManage.view.ApplyRecordWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.applyRecordWin',
    id:'applyRecordWin',

    title : '报名内容',
    //layout: 'fit',
    width:800,
    height:550,
    autoScroll:true,
    modal: true,
    html: '<iframe id="applyRecordFrame" style="height: 450px;width:750px" src="viewApplyRecord.html" frameborder="0" width="800px",height="800px"></iframe>',

    initComponent: function() {
        this.callParent(arguments);
    }
});