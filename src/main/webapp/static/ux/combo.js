/**
 * Created by hao on 2015/12/28.
 */
Ext.define('UX.view.component.PeriodNumModel', {
    extend: 'Ext.data.Model',
    fields: ['periodNum']
});

Ext.define('UX.view.component.PeriodNumStore', {
    extend: 'Ext.data.Store',
    model: 'UX.view.component.PeriodNumModel',
    sorters: ['periodNum'],
    //pageSize:20,
    proxy: {
        type: 'ajax',
        url: ip.tl_periodNum,
        reader: {
            type: 'json',
            root: 'periodVOList',
            successProperty: 'success'
        }
    }
});

Ext.define('UX.view.component.PeriodNumCombo' ,{
    extend: 'Ext.form.field.ComboBox',
    alias:'widget.periodNumCombo',
    title : '届数',
    queryMode: 'local',
    displayField: 'periodNum',
    valueField: 'periodNum',
    triggerAction : 'all',
    forceSelection: true,
    lazyRender : true,
    labelAlign : 'left',
    width : '300',
    allowBlank : false,
    editable:false,

    initComponent: function() {

        this.store = Ext.create('UX.view.component.PeriodNumStore');
        this.store.load();
        this.callParent(arguments);
    }

});