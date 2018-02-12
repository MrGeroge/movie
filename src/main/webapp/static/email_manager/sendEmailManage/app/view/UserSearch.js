/**
 * Created by hao on 2015/12/18.
 */
var keyword = Ext.create('Ext.form.TextField', {
    //fieldLabel : '',
    anchor : '90%',
    labelAlign : 'right',
    name : 'labelName'
});

Ext.define('EmailManage.view.UserSearch', {
    extend : 'Ext.form.FieldSet',
    alias : 'widget.userSearch',
    title : '',
    layout : 'column',
    margin : '5 5 5 5',
    collapsible : true,
    items : [{
        layout: 'form',
        columnWidth: 1,
        border: false,
        items: [{
            layout: 'anchor',
            items: [keyword]
        }
        ]

    }]

})