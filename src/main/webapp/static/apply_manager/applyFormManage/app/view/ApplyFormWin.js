/**
 * Created by hao on 2015/12/18.
 */
Ext.define('ApplyFormManage.view.ApplyFormWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.applyForm',
    id:'applyForm',

    title : '报名表',
    //layout: 'fit',
    width:850,
    height:600,
    autoScroll:true,
    modal: true,
    html: '<iframe id="applyFrame" style="height: 450px;width:750px"src="createApplication.html" frameborder="0" width="800px",height="800px"></iframe>',

    initComponent: function() {
        this.items = [
            /*{
                xtype: 'applyFormForm'

            }*/
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});