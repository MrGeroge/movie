/**
 * Created by hao on 2015/12/21.
 */
Ext.define('CourseManage.view.CourseWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.courseWin',
    id:'courseWin',

    title : '课程信息',
    layout: 'fit',
    autoScroll:true,
    modal: true,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                id:'courseForm',
                items:[{
                    xtype:'textfield',
                    name:'id',
                    fieldLabel:'ID',
                    hidden:true
                },{
                    xtype:'datefield',
                    name:'startTime',
                    fieldLabel:'时间',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name : 'direction',
                    fieldLabel: '课程方向',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name : 'pattern',
                    fieldLabel: '形式',
                    allowBlank:false
                },{
                    xtype:'textfield',
                    name : 'mentorName',
                    fieldLabel: '导师',
                    allowBlank:false
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