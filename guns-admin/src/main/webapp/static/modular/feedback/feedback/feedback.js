/**
 * feedback管理初始化
 */
var Feedback = {
    id: "FeedbackTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Feedback.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈用户地址', field: 'userid', visible: false, align: 'center', valign: 'middle'},
            {title: '反馈内容', field: 'fbcontent', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈时间', field: 'fbtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Feedback.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Feedback.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加feedback
 */
Feedback.openAddFeedback = function () {
    var index = layer.open({
        type: 2,
        title: '添加反馈',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/feedback/feedback_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看feedback详情
 */
Feedback.openFeedbackDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '反馈详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/feedback/feedback_update/' + Feedback.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除feedback
 */
Feedback.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/feedback/delete", function (data) {
            Feng.success("删除成功!");
            Feedback.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("feedbackId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询feedback列表
 */
Feedback.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Feedback.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Feedback.initColumn();
    var table = new BSTable(Feedback.id, "/feedback/list", defaultColunms);
    table.setPaginationType("client");
    Feedback.table = table.init();
});
