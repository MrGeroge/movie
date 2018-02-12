/**
 * Created by hao on 2015/12/17.
 */
Ext.define('LabelManage.view.AddLabel', {
    extend : 'Ext.form.Panel',
    alias : 'widget.addLabelForm',
    id:'addLabelForm',
    bodyStyle:'background-color:white',
    items:[{
        xtype:'addLabelFieldSet'
    }],
    buttons:[{
        text:'添加标签',
        action:'addLabel',
        formBind:true
    }]

})