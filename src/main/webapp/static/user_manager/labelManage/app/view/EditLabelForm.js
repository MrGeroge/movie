/**
 * Created by hao on 2015/12/18.
 */
Ext.define('LabelManage.view.EditLabelForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.editLabelForm',
    id:'editLabelForm',
    defaults:{xtype:'textfield',anchor:'95%',allowBlank:false,labelWidth:60, labelAlign:'right'},

    items: [
        {
            name:'id',
            fieldLabel: '标签ID',
            hidden:true
        },
        {
            name : 'name',
            fieldLabel: '名称',
            allowBlank:false
        }
    ]
});