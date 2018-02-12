/**
 * Created by hao on 2015/12/21.
 */
Ext.define('ArticleManage.view.ArticleContentWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.articleContentWin',
    id:'articleContentWin',

    title : '新闻内容',
    //layout: 'fit',
    width:800,
    height:550,
    autoScroll:true,
    modal: true,
    html: '<iframe id="articleContent" style="height: 450px;width:100%" src="articleContent.html" frameborder="0" width="80%"height="800px"></iframe>',

    initComponent: function() {
        /*this.items=[{
            xtype:'panel',
            title: '文章内容',
            width: 550,
            height: 250,
            frame: true,
            layout: 'fit',
            items: {
                xtype: 'htmleditor',
                fileUpload:true
                //enableColors: false,
                //enableAlignments: false
            }
        }];*/
        this.buttons = [
            {
                text: '保存',
                action: 'saveChange'
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