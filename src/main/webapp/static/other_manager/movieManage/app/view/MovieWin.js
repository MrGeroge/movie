/**
 * Created by hao on 2015/12/20.
 */
Ext.define('MovieManage.view.MovieWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.movieWin',
    id:'movieWin',

    title : '添加电影',
    //layout: 'fit',
    autoScroll:true,
    modal: true,
    height:500,
    width:400,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'movieForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'name',
                    fieldLabel:'影片名',
                    allowBlank:false,
                    width:350
                },{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.5,
                        layout:'form',
                        items:[{
                            xtype:'label',
                            text:'图片(建议尺寸 宽高比7：4):'
                            //margin: '50 0 0 0'
                        },{
                            xtype:'textfield',
                            name:'imageUrl',
                            fieldLabel:'图片',
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
                    xtype:'kindCombo',
                    name : 'kindId',
                    //fieldLabel: '分类'
                    labelAlign : 'left',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textfield',
                    name : 'firstType',
                    fieldLabel: '首映类型',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textfield',
                    name : 'year',
                    fieldLabel: '年代',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textfield',
                    name : 'author',
                    fieldLabel: '作者',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textfield',
                    name : 'genre',
                    fieldLabel: '体裁',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'attribute',
                    fieldLabel: '电影属性',
                    allowBlank:false,
                    emptyText:'请输入电影属性，例如：中国|2015|彩色|21min|中英文字幕|剧情',
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'group',
                    fieldLabel: '制作班底',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'description',
                    fieldLabel: '影片简介',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'state',
                    fieldLabel: '导演阐述',
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