/**
 * Created by hao on 2015/12/18.
 */
Ext.define('LabelManage.view.EditLabelWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.editLabelWin',

    title : '修改标签',
    layout: 'fit',
    //autoShow: true,
    modal: true,
    initComponent: function() {
        this.items = [
            {
                xtype: 'editLabelForm'

            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange',
                formBind:true
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