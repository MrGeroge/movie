/**
 * Created by hao on 2015/12/21.
 */
Ext.define('MapLightManage.view.ProvinceCombo',{
    extend:'Ext.form.field.ComboBox',
    id:'provinceCombo',
    alias:'widget.provinceCombo',
    fieldLabel:'省份',
    emptyText:'选择需要点亮的省份',
    store:'MapLights',
    queryMode: 'local',
    displayField: 'province',
    valueField: 'id',
    editable:false,
    labelAlign: 'right',
    bodyPadding:'10'
});