/**
 * Created by hao on 2015/12/22.
 */
Ext.define('TLVolunteerManage.view.PictureUpload', {
    extend: 'Ext.window.Window',
    alias : 'widget.pictureUpload',
    id:'pictureUpload',

    title : '图片上传',
    layout: 'fit',
    //width:400,
    //height:250,
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                //title: '上传一张图片',
                width: 400,
                bodyPadding: 10,
                frame: true,
                style:'border-width:0 0 0 0',
                renderTo: Ext.getBody(),
                items: [{
                    xtype: 'filefield',
                    name: 'file',
                    fieldLabel: '图片',
                    labelWidth: 50,
                    msgTarget: 'side',
                    allowBlank: false,
                    anchor: '100%',
                    buttonText: '选择图片...'
                }],

                buttons: [{
                    text: '上传',
                    action:'upload',
                    formBind:true,
                    handler: function() {
                        //var form = this.up('form').getForm();
                        /*if(form.isValid()){
                            form.submit({
                                url: ip.uploadImg,
                                waitMsg: '正在上传...',
                                success: function(form,action) {
                                    console.log(form);
                                    console.log(action);
                                    Ext.Msg.alert('Success', '图片上传成功');
                                    var judgeForm = Ext.getCmp('judgeForm');
                                    var judgeUpdateForm = Ext.getCmp('judgeUpdateForm');
                                    if(judgeForm != null) {
                                        var data = eval('('+action.response.responseText+')');
                                        //alert(data);
                                        judgeForm.getForm().findField('avatarUrl').setValue(data.link);
                                    }
                                    if(judgeUpdateForm != null){
                                        var data = eval('('+action.response.responseText+')');
                                        judgeUpdateForm.getForm().findField('avatarUrl').setValue(data.link);
                                    }
                                },
                                failure:function(form,action){
                                    console.log(form);
                                    console.log(action);
                                }
                            });
                        }*/
                        //var win = Ext.getCmp('pictureUpload');
                        //win.close();
                    }
                }]

            }
        ];

        this.callParent(arguments);
    }
});