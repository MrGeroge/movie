/**
 * Created by hao on 2015/12/21.
 */
Ext.define('MapLightManage.store.MapLights', {
    extend: 'Ext.data.Store',
    model: 'MapLightManage.model.MapLight',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        api: {
            read: ip.listMapLight,
            create: '#',
            update: '#',
            destroy:'#'
        },
        reader: {
            type: 'json',
            root: 'mapLightVOList',
            successProperty: 'success'
            //root : "teacherList",
        }
    }
});