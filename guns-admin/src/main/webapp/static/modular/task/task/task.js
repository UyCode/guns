/**
 * task管理初始化
 */
var Task = {
    id: "TaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Task.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'stime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'etime', visible: true, align: 'center', valign: 'middle'},
            {title: '设置时间', field: 'settime', visible: true, align: 'center', valign: 'middle'},
            {title: '标志位', field: 'flag', visible: true, align: 'center', valign: 'middle'},
            {title: '学校名称', field: 'scname', visible: true, align: 'center', valign: 'middle'},
            {title: '学院名称', field: 'coname', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Task.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Task.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加task
 */
Task.openAddTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加task',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/task/task_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看task详情
 */
Task.openTaskDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'task详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/task/task_update/' + Task.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除task
 */
Task.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/task/delete", function (data) {
            Feng.success("删除成功!");
            Task.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询task列表
 */
Task.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Task.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Task.initColumn();
    var table = new BSTable(Task.id, "/task/list", defaultColunms);
    console.log(table);
    table.setPaginationType("client");
    Task.table = table.init();
});
