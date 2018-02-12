/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.view.ArticleWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.articleWin',
    id:'articleWin',

    title : '新闻',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'articleForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'label',
                            text:'封面(建议尺寸 宽高比3：2):'
                            //margin: '50 0 0 0'
                        },{
                            xtype:'textfield',
                            name:'coverUrl',
                            fieldLabel:'封面',
                            allowBlank:false,
                            emptyText:'建议大小，宽高比3：2',
                            readOnly:true,
                            hidden:true
                        }]
                    },{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'button',
                            text:' 上传图片 ',
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
                    width : 400,
                    height: 266,
                    autoEl:{
                        tag:'img',
                        src:'../../../assets/admin/themes/icons/blank.gif'
                    }
                },{
                    xtype:'textfield',
                    name : 'title',
                    //width:280,
                    fieldLabel: '标题',
                    allowBlank:false
                },/*{
                    xtype:'textfield',
                    name : 'categoryName',
                    fieldLabel: '类别',
                    allowBlank:false
                },*/{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'label',
                            text:'内容:'
                        },{
                            xtype:'textfield',
                            name:'content',
                            fieldLabel:'内容',
                            allowBlank:false,
                            hidden:true,
                            readOnly:true
                        }]
                    },{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'button',
                            text:' 添加内容 ',
                            action:'openContentWin',
                            handler:function(button){
                                var uploadWin = Ext.widget('articleContentWin');
                                uploadWin.show();
                                var form = button.up('form');
                                var frm = document.getElementById('articleContent').contentWindow;
                                frm.onload = function() {
                                    //console.log(form.getForm().findField('content').value);
                                    //console.log(frm.document.getElementById('edit'));
                                    frm.setHtmlCode(form.getForm().findField('content').value);
                                    //console.log(frm.document.getElementById('edit'));
                                }
                            }
                        }]
                    }]
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