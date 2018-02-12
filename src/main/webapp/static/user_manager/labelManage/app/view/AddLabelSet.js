var labelName = Ext.create('Ext.form.TextField', {
    fieldLabel : '名称',
    anchor : '90%',
    labelAlign : 'right',
    allowBlank:false,
    name : 'name'
});

Ext.define('LabelManage.view.AddLabelSet', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.addLabelFieldSet',
    title : '填写名称',
    layout : 'column',
    margin : '5 5 5 5',
    collapsible : true,
    items : [{
        layout: 'form',
        columnWidth: .5,
        border: false,
        items: [{
            layout: 'anchor',
            items: [labelName]
        }
        ]

    }]

})/**
 * Created by hao on 2015/12/17.
 */
