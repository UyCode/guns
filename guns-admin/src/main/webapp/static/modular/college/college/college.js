/**
 * college管理初始化
 */
var College = {
    id: "CollegeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
College.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '学院名称', field: 'coname', visible: true, align: 'center', valign: 'middle'},
            {title: '学校名称', field: 'scname', visible: true, align: 'center', valign: 'middle'},
            {title: '网址', field: 'web', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
College.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        College.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加college
 */
College.openAddCollege = function () {
    var index = layer.open({
        type: 2,
        title: '添加college',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/college/college_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看college详情
 */
College.openCollegeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'college详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/college/college_update/' + College.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除college
 */
College.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/college/delete", function (data) {
            Feng.success("删除成功!");
            College.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("collegeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询college列表
 */
College.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    College.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = College.initColumn();
    var table = new BSTable(College.id, "/college/list", defaultColunms);
    table.setPaginationType("client");
    College.table = table.init();
});
