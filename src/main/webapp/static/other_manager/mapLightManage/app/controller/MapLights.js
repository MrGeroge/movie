/**
 * Created by hao on 2015/12/21.
 */
Ext.define('MapLightManage.controller.MapLights', {
    extend: 'Ext.app.Controller',

    models:['MapLight'],
    stores:['MapLights'],
    views:['Grid',
        'Light',
        'ProvinceCombo'
    ],

    init: function(){
        this.control({
            '#set-mapLight-btn':{
                click:this.setMapLight
            },

            'light button[action=save]':{
                click:this.light
            }
        });
    },

    setMapLight :function(button){
        var grid = button.up('grid');

        /*var flag = grid.getSelectionModel().hasSelection();
        if(!flag){
            Ext.Msg.alert('提示','请选择一项');
            return;
        }*/

        var win = Ext.widget('light');
        var store = grid.getStore();
        if(store.getCount() != 0){
            var SC = Ext.getCmp('selectedProvinces');
            for(var i=0; i<store.getCount(); i++){
                var record = store.getAt(i);
                if(record.get('status') == true) {
                    var newItem = Ext.create('Ext.form.field.Checkbox', {
                        boxLabel: record.data.province,
                        name: 'province',
                        inputValue: record.data.id,
                        checked: true
                    });
                    SC.add(newItem);
                }
            }
        }
        win.show();
    },

    light : function(button){
        var win = button.up('window');
        var form = win.down('form');

        if(form.getForm().isValid()){
            var maps = Ext.getCmp('selectedProvinces').getChecked();
            var array = new Array(maps.length);
            for(var i=0;i<maps.length;i++){
                /*if(i != tags.length-1){
                 tagIds += tags.get(i).inputValue + ";";
                 }else{
                 tagIds += tags.get(i).inputValue;
                 }*/
                array[i] = maps[i].inputValue;
            }

            $.ajax({
                url: ip.light,
                type:'put',
                contentType:'application/json',
                //data:'{"tagids":"'+tagIds+'"}',
                data:JSON.stringify(array),
                success:function(response){
                    Ext.Msg.alert('提示', '更新成功！');
                    Ext.getStore('MapLights').reload();
                    win.close();
                }
            });
        }
    }
});