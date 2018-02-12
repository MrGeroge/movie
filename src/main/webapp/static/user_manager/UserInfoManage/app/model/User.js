/**
 * Created by hao on 2015/12/17.
 */
Ext.define('UserManage.model.User', {
    extend: 'Ext.data.Model',
    fields: ['account', 'accountProfileVO','tags',
        {name:"id",mapping:"account.id"},
        {name:"name",mapping:"account.username"},
        {name:"sex",mapping:"accountProfileVO.sex"},
        {name:"birthday",mapping:"accountProfileVO.birthday"},
        {name:"identity",mapping:"accountProfileVO.identity"},
        {name:"address",mapping:"accountProfileVO.address"},
        {name:"phone",mapping:"accountProfileVO.phone"}
    ],

    hasMany:[{
        model:'UserManage.model.Label'
    }]
});