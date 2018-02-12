/**
 * Created by hao on 2015/12/20.
 */
Ext.define('UserManage.view.EditLabels', {  //注意定义名称 的形式
    extend: 'Ext.window.Window',
    alias : 'widget.editLabel',
    id : 'editLabel',
    title : '标签编辑',
    layout: 'fit',
    modal: true,
    width : 550,
    height : 330,
    //autoScroll: true,
    //containerScroll : true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'editLabelForm',
                bodyPadding : 5,
                layout : 'anchor',
                items: [{
                    xtype : 'textfield',
                    name : 'id',
                    hidden : true,
                    hideLabel : true
                },{
                    xtype: 'labelComboCopy',
                    listeners : {
                        'select' : function(arg) {
                            var newItem = Ext.create('Ext.form.field.Checkbox', {
                                boxLabel  : arg.rawValue,
                                name      : 'labelName',
                                inputValue: arg.value,
                                checked : true
                            });

                            var SC = Ext.getCmp('selectedLabels');
                            var arr = SC.items.items;
                            for(var k=0;k<arr.length;k++){
                                if(arg.value==arr[k].inputValue){
                                    return;
                                }
                            }
                            SC.add(newItem);

                        }
                    }
                },{
                    xtype:'panel',
                    autoScroll: true,
                    autoShow: true,
                    height: 220,
                    items:[
                        {
                            xtype: 'checkboxgroup',
                            id : 'selectedLabels',
                            fieldLabel: '已存在标签',
                            columns: 1
                        }]
                }]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'save'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});