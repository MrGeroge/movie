/**
 * Created by hao on 2015/12/20.
 */
Ext.define('BuoyManage.view.BuoyUpdateWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.buoyUpdateWin',
    id:'buoyUpdateWin',

    title : '浮标',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'buoyUpdateForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.71,
                        layout:'form',
                        items:[{
                            xtype:'textfield',
                            name:'logoUrl',
                            fieldLabel:'商标',
                            allowBlank:false,
                            readOnly:true
                        }]
                    },{
                        columnWidth:0.29,
                        layout:'form',
                        items:[{
                            xtype:'button',
                            text:'上传图片',
                            handler:function(){
                                var uploadWin = Ext.widget('pictureUpload');
                                uploadWin.show();
                            }
                        }]
                    }]
                },{
                    xtype:'textfield',
                    name : 'website',
                    fieldLabel: '网址'
                }]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange'
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