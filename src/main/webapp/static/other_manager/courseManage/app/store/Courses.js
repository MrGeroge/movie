/**
 * Created by hao on 2015/12/21.
 */
Ext.define('CourseManage.store.Courses', {
    extend: 'Ext.data.Store',
    model: 'CourseManage.model.Course',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listCourse,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'object',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});