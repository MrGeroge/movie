/**
 * Created by hao on 2015/12/20.
 */
Ext.define('MovieManage.view.MovieUpdateWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.movieUpdateWin',
    id:'movieUpdateWin',

    title : '电影',
    //layout: 'fit',
    autoScroll:true,
    height:500,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'movieUpdateForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name:'name',
                    fieldLabel:'影片名',
                    width:350
                },{
                    layout:'column',
                    width:400,
                    items:[{
                        columnWidth:0.71,
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
                    width:350
                },{
                    xtype:'textfield',
                    name : 'firstType',
                    fieldLabel: '首映类型',
                    width:350
                },{
                    xtype:'textfield',
                    name : 'year',
                    fieldLabel: '年代',
                    width:350
                },{
                    xtype:'textfield',
                    name : 'author',
                    fieldLabel: '作者',
                    width:350
                },{
                    xtype:'textfield',
                    name : 'genre',
                    fieldLabel: '体裁',
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'attribute',
                    fieldLabel: '属性串',
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'group',
                    fieldLabel: '制作班底',
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'description',
                    fieldLabel: '影片简介',
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'state',
                    fieldLabel: '导演阐述',
                    width:350
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