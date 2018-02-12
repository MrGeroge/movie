/**
 * Created by hao on 2015/12/18.
 */
Ext.define('ApplyFormManage.view.ApplyFormUpdate',{
    extend: 'Ext.window.Window',
    alias: 'widget.applyFormUpdate',
    id: 'applyFormUpdate',

    title : '报名表',
    //layout: 'fit',
    width:800,
    height:550,
    autoScroll:true,
    modal: true,
    html: '<iframe id="applyUpdateFrame" name ="applyUpdateFrame" style="height: 450px;width:750px"src="editApplication.html" frameborder="0" width="800px",height="800px"></iframe>',
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
    /*tbar : [{
        id : 'add-textfield-btn',
        text : '<span style="font-size:12px">文本框</span>',
        disabled : false
    },{
        id : 'add-textareafield-btn',
        text : '<span style="font-size:12px">文本域</span>',
        disabled : false
    },{
        id : 'add-radio-btn',
        text : '<span style="font-size:12px">单选框</span>',
        disabled : false
    },{
        id : 'add-checkbox-btn',
        text : '<span style="font-size:12px">复选框</span>',
        disabled : false
    },{
        id : 'add-select-btn',
        text : '<span style="font-size:12px">下拉框</span>',
        disabled : false
    },{
        id : 'add-upload-btn',
        text : '<span style="font-size:12px">上传框</span>',
        disabled : false
    }]*/
});