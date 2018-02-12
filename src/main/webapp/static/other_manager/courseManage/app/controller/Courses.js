/**
 * Created by hao on 2015/12/21.
 */
Ext.define('CourseManage.controller.Courses', {
    extend: 'Ext.app.Controller',

    models:['Course'],
    stores:['Courses'],
    views:['Grid',
        'CourseWin',
        'CourseUpdateWin'
    ],

    init: function(){
        this.control({
            '#add-course-btn':{
                click:this.addCourse
            },

            '#edit-course-btn':{
                click:this.editCourse
            },

            '#delete-course-btn':{
                click:this.deleteCourse
            },

            'courseWin button[action=saveChange]':{
                click:this.createCourse
            },
            'courseUpdateWin button[action=saveChange]':{
                click:this.updateCourse
            }
        });
    },

    addCourse : function(){
        var win = Ext.widget('courseWin');
        win.show();
    },

    editCourse : function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        var win = Ext.widget('courseUpdateWin');
        win.down('form').loadRecord(rec);
        win.show();
    },

    createCourse: function(button){
        var form = Ext.getCmp('courseForm');
        var record = form.getRecord;
        var values = form.getValues();

        if(form.getForm().isValid()){
            Ext.Ajax.request({
                url:ip.addCourse,
                method:'POST',
                params:{
                    start_time:values.startTime,
                    direction:values.direction,
                    pattern:values.pattern,
                    mentor_name:values.mentorName
                },
                success: function(response){
                    Ext.Msg.alert('提示', '保存成功！');
                    Ext.getStore('Courses').reload();
                    var win = Ext.getCmp('courseWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空');
        }
    },

    updateCourse: function(button){
        var form = Ext.getCmp('courseUpdateForm');
        var record = form.getRecord;
        var values = form.getValues();

        /*if(record != null){
            Ext.Ajax.request({
                url:'',
                method:'POST',
                extraParams:values,
                success: function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Courses').reload();
                    Ext.getCmp('courseUpdateWin').close();
                }
            });

        }*/
        if(form.getForm().isValid()) {
            $.ajax({
                url: ip.updateCourse + values.id,
                type: 'put',
                contentType: 'application/json',
                data: '{"startTime":"' + values.startTime + '",' +
                '"direction":"' + values.direction + '",' +
                '"pattern":"' + values.pattern + '",' +
                '"mentorName":"' + values.mentorName + '"}',
                success: function (response) {
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('Courses').reload();
                    var win = Ext.getCmp('courseUpdateWin');
                    win.close();
                }
            });
        }else{
            Ext.Msg.alert('提示','输入内容有空！');
        }
    },

    deleteCourse: function(button){
        var grid = button.up('grid');

        var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }

        var records = grid.getSelectionModel().getSelection();
        var rec = records[0];

        if(rec != null){
            Ext.Msg.confirm('警告','你确定删除？',function(button){
                if(button == 'yes') {
                    $.ajax({
                        url: ip.deleteCourse + rec.data.id,
                        type: 'delete',
                        contentType: 'application/json',
                        //data:"{'name':'"+values.name+"'}",
                        success: function (response) {
                            Ext.Msg.alert('提示', '删除成功！');
                            Ext.getStore('Courses').reload();
                        }
                    });
                }
            });
        }
    }
});