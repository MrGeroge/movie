/**
 * Created by hao on 2015/12/20.
 */
Ext.define('ArticleManage.view.ArticleViewWin', {
    extend: 'Ext.window.Window',
    alias : 'widget.articleViewWin',
    id:'articleViewWin',

    title : '新闻内容',
    //layout: 'fit',
    width:800,
    height:550,
    autoScroll:true,
    modal: true,
    html: '<iframe name="viewFrame"id="articleView" style="height: 450px;width:100%" src="articleView.html" frameborder="0" width="80%" height="800px"></iframe>',

    initComponent: function() {
        this.items = [
            {

            }
        ];

        /*this.buttons = [
            {
                text: '保存',
                action: 'saveChange'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];*/

        this.callParent(arguments);
    }
});