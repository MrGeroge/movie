/**
 * Created by hao on 2015/12/20.
 */
Ext.define('SponsorManage.view.SponsorWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.sponsorWin',
    id:'sponsorWin',

    title : '赞助商',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'sponsorForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'赞助商ID',
                    hidden:true
                },{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'label',
                            text:'赞助商logo(建议尺寸 宽高比7：4):'
                        },{
                            xtype:'textfield',
                            name:'logoUrl',
                            fieldLabel:'赞助商logo',
                            allowBlank:false,
                            readOnly:true,
                            hidden:true
                        }]
                    },{
                        columnWidth:0.5,
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
                    xtype:'component',
                    id:'viewPicture',
                    fieldLabel:'预览',
                    width : 350,
                    height: 200,
                    autoEl:{
                        tag:'img',
                        src:'../../../assets/admin/themes/icons/blank.gif'
                    }
                },{
                    xtype:'textfield',
                    name : 'website',
                    fieldLabel: '网址',
                    allowBlank:false,
                    width:350
                }]
             }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'saveChange',
                formBind:true
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