/**
 * Created by hao on 2015/12/21.
 */
Ext.define('JudgeManage.view.JudgeUpdateWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.judgeUpdateWin',
    id:'judgeUpdateWin',

    title : '评委信息',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'judgeUpdateForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'textfield',
                    name : 'name',
                    fieldLabel: '姓名',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textfield',
                    name : 'position',
                    fieldLabel: '职位',
                    allowBlank:false,
                    width:350
                },{
                    xtype:'textareafield',
                    name : 'summary',
                    fieldLabel: '简介',
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
                            text:'头像(建议尺寸 宽高比3：4):'
                            //margin: '50 0 0 0'
                        },{
                            xtype:'textfield',
                            name:'avatarUrl',
                            fieldLabel:'头像',
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
                    margin:'0 0 0 150',
                    width : 150,
                    height: 200,
                    autoEl:{
                        tag:'img',
                        src:'../../../assets/admin/themes/icons/blank.gif'
                    }
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