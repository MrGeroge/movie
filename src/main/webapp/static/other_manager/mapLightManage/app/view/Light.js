/**
 * Created by hao on 2015/12/21.
 */
Ext.define('MapLightManage.view.Light', {  //注意定义名称 的形式
    extend: 'Ext.window.Window',
    alias : 'widget.light',
    id : 'light',
    title : '地图点亮',
    layout: 'fit',
    modal: true,
    width : 550,
    height : 330,
    autoScroll: true,
    containerScroll : true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                bodyPadding : 5,
                layout : 'anchor',
                items: [{
                    xtype: 'provinceCombo',
                    listeners : {
                        'select' : function(arg) {
                            var newItem = Ext.create('Ext.form.field.Checkbox', {
                                boxLabel  : arg.rawValue,
                                name      : 'province',
                                inputValue: arg.value,
                                checked : true
                            });

                            var SC = Ext.getCmp('selectedProvinces');
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
                        {id : 'selectedProvinces',
                            xtype: 'checkboxgroup',
                            fieldLabel: '已点亮省份',
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