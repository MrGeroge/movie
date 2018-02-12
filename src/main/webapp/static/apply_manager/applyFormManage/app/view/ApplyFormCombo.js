/**
 * Created by hao on 2015/12/18.
 */
var applyForms = Ext.create('Ext.data.Store',{
    fields: ['value','name'],
    data: [
        {"value":"1","name":"创投提案报名表"},
        {"value":"2","name":"创投资方报名表"},
        {"value":"3","name":"训练营报名表"},
        {"value":"4","name":"媒体报名表"},
        {"value":"5","name":"志愿者报名表"},
        {"value":"6","name":"主动放映报名表"},
        {"value":"7","name":"竞赛报名表"},
    ]
});

Ext.define('ApplyFormManage.view.ApplyFormCombo',{
    extend:'Ext.form.field.ComboBox',
    id:'applyFormCombo',
    alias:'widget.applyFormCombo',
    fieldLabel:'报名表',
    emptyText:'选择所要创建的报名表',
    store:applyForms,
    queryMode: 'local',
    displayField: 'name',
    valueField: 'name',
    editable:false,
    labelAlign: 'right',
    bodyPadding:'10'
});