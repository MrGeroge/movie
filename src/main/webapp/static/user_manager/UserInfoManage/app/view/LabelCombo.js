/**
 * Created by hao on 2015/12/20.
 */
Ext.define('UserManage.view.LabelCombo',{
    extend:'Ext.form.field.ComboBox',
    id:'labelCombo',
    alias:'widget.labelCombo',
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