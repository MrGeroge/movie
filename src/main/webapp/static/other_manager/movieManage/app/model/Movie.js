/**
 * Created by hao on 2015/12/18.
 */
Ext.define('MovieManage.model.Movie', {
    extend: 'Ext.data.Model',
    fields: ['id', 'name','imageUrl','kindId','kind','firstType','attribute','year',
        'author','genre','group','description','state']

});