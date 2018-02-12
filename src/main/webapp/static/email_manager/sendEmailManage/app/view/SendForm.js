/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.view.SendForm',{
    extend:'Ext.form.FormPanel',
    alias:'widget.sendEmailForm',
    title:'邮件发送界面',
    id:'sendEmailForm',
    autoHeight:true,
    width:600,
    autoShow:true,
    bodyPadding:50,
    //frame:true,

    url:'#',

    defaults: {
        anchor: '90%'
    },

    layout:'form',
    items:[{
        xtype:'textfield',
        fieldLabel:'收件人',
        width:250,
        allowBlank:'false',
        blankText:'从右侧选择用户'
    },{
        xtype:'textfield',
        fieldLabel:'主题',
        width:250,
        allowBlank:'false'
    },{
        xtype:'textareafield',
        fieldLabel:'内容',
        width:250,
        height:300,
        allowBlank:'flase'
    }] ,

    buttons:[{
        text:'重置',
        handler: function(){
            this.up('form').getForm().reset();
        }
    },{
        text:'发送',
        handler: function() {
            var form = this.up('form').getForm();
            if (form.isValid()) {
                form.submit({
                    success: function (form, action) {
                        Ext.Msg.alert('发送成功');
                    },
                    failure: function (form, action) {
                        Ext.Msg.alert('发送失败');
                    }
                });
            }
        }
    }]
});