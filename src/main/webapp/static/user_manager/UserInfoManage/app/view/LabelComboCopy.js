/**
 * Created by hao on 2015/12/23.
 */
Ext.define('UserManage.view.LabelComboCopy',{
    extend:'Ext.form.field.ComboBox',
    id:'labelComboCopy',
    alias:'widget.labelComboCopy',
    fieldLabel:'标签',
    emptyText:'选择标签',
    store:'Labels',
    queryMode: 'local',
    displayField: 'name',
    valueField: 'id',
    editable:false,
    labelAlign: 'right',
    bodyPadding:'10'
});