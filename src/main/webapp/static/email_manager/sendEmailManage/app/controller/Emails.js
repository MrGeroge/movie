/**
 * Created by hao on 2015/12/18.
 */
Ext.define('EmailManage.controller.Emails', {
    extend: 'Ext.app.Controller',

    models:['Email','User'],
    stores:['Emails','Users'],
    views:['SendForm',
        'UserGrid',
        'UserPanel',
        'UserSearch',
        'UserSearchForm'],

    init: function(){
        this.control({

        });
    }
});