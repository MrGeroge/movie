Ext.define('UX.view.component.DictDataModel', {
    extend: 'Ext.data.Model',
    fields: ['id','name', 'value','index']
});

Ext.define('UX.view.component.DictDataStore', {
    extend: 'Ext.data.Store',
    model: 'UX.view.component.DictDataModel',
    sorters: ['index', 'id'],
    pageSize:20,
    proxy: {
        type: 'ajax',
        url: './listDictData',
        reader: {
            type: 'json',
            root: 'object',
            successProperty: 'success'          
        }
    }  
});

Ext.define('UX.view.component.DictDataCombo' ,{
    extend: 'Ext.form.field.ComboBox',
    title : '数据字典下拉框',
    queryMode: 'local',
    displayField: 'name',
    valueField: 'value',
    triggerAction : 'all',  
    forceSelection: true,
    lazyRender : true, 
    labelAlign : 'right',
    width : '300',
//    allowBlank : false,
    
    initComponent: function() {
    	
    	this.store = Ext.create('UX.view.component.DictDataStore');
    	this.store.load({
			params : {
				dictTypeId : this.dictTypeId
			}
		});
    	this.callParent(arguments);
    }
    
});

Ext.define('UX.view.component.TrainingModeCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.trainingModeCombo',
    fieldLabel: '培训形式',
    dictTypeId : '1'
});

Ext.define('UX.view.component.CourseCategoryCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.courseCategoryCombo',
    fieldLabel: '课程分类',
    dictTypeId : '2'
});

Ext.define('UX.view.component.ClassCategory1Combo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.classCategory1Combo',
    fieldLabel: '培训班类别一',
    dictTypeId : '3'
});

Ext.define('UX.view.component.ClassCategory2Combo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.classCategory2Combo',
    fieldLabel: '培训班类别二',
    dictTypeId : '4'
});

Ext.define('UX.view.component.ClassCategory3Combo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.classCategory3Combo',
    fieldLabel: '培训班类别三',
    dictTypeId : '5'
});

Ext.define('UX.view.component.ClassCategory4Combo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.classCategory4Combo',
    fieldLabel: '培训班类别四',
    dictTypeId : '6'
});

Ext.define('UX.view.component.StudentIndustryCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.studentIndustryCombo',
    fieldLabel: '所属系统',
    dictTypeId : '7'
});

Ext.define('UX.view.component.StudentProfessionCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.studentProfessionCombo',
    fieldLabel: '工种',
    dictTypeId : '8'
});

Ext.define('UX.view.component.PoliticalStatusCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.politicalStatusCombo',
    fieldLabel: '政治面貌',
    dictTypeId : '9'
});

Ext.define('UX.view.component.DegreeOfEducationCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.degreeOfEducationCombo',
    fieldLabel: '学历',
    dictTypeId : '10'
});

Ext.define('UX.view.component.TitleCombo' ,{
    extend: 'UX.view.component.DictDataCombo',
    alias : 'widget.titleCombo',
    fieldLabel: '职称',
    dictTypeId : '11'
});