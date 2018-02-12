/**
 * Created by hao on 2015/12/22.
 */
Ext.define('SponsorManage.view.PictureUpload', {
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
                    formBind:true,
                    action:'upload',
                    handler: function() {
                        /*var form = this.up('form').getForm();
                        if(form.isValid()){
                            form.submit({
                                url: ip.uploadImg,
                                waitMsg: '正在上传...',
                                success: function(form,action) {
                                    console.log(form);
                                    console.log(action);
                                    Ext.Msg.alert('Success', '图片上传成功');
                                    var sponsorForm = Ext.getCmp('sponsorForm');
                                    var sponsorUpdateForm = Ext.getCmp('sponsorUpdateForm');
                                    if(sponsorForm != null) {
                                        var data = eval('('+action.response.responseText+')');
                                        //alert(data);
                                        sponsorForm.getForm().findField('logoUrl').setValue(data.link);
                                    }
                                    if(sponsorUpdateForm != null){
                                        var data = eval('('+action.response.responseText+')');
                                        sponsorUpdateForm.getForm().findField('logoUrl').setValue(data.link);
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