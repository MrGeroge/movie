/**
 * Created by hao on 2015/12/18.
 */
/*var labelName = Ext.create('Ext.form.TextField', {
    fieldLabel : '名称',
    anchor : '90%',
    labelAlign : 'right',
    allowBlank:false,
    name : 'labelName'
});*/

Ext.define('UserManage.view.UserSearchSet', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.userSearchFieldSet',
    title : '标签',
    layout : 'column',
    margin : '5 5 5 5',
    collapsible : true,
    items : [{
        layout: 'form',
        columnWidth: .5,
        border: false,
        items: [{
            layout: 'anchor',
            items: [{
                xtype:'labelCombo'
            }]
        }
        ]

    }]

});