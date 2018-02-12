/**
 * Created by hao on 2015/12/20.
 */
var kinds = Ext.create('Ext.data.Store',{
    fields: ['id','kind'],
    data: [
        {"id":"1","kind":"喜剧"},
        {"id":"2","kind":"恐怖"},
        {"id":"3","kind":"爱情"},
        {"id":"4","kind":"纪实"},
        {"id":"5","kind":"悲剧"}
    ]
});

Ext.define('MovieManage.view.KindCombo',{
    extend:'Ext.form.field.ComboBox',
    id:'kindCombo',
    alias:'widget.kindCombo',
    fieldLabel:'影片类别',
    emptyText:'选择影片类别',
    store:'Kinds',
    queryMode: 'local',
    displayField: 'name',
    valueField: 'id',
    editable:false,
    labelAlign: 'right',
    bodyPadding:'10'
});