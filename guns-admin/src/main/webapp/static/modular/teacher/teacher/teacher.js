/**
 * teacher管理初始化
 */
var Teacher = {
    id: "TeacherTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teacher.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '教师姓名', field: 'tname', visible: true, align: 'center', valign: 'middle'},
            {title: '关系', field: 'relationship', visible: true, align: 'center', valign: 'middle'},
            {title: '学院名称', field: 'coname', visible: true, align: 'center', valign: 'middle'},
            {title: '学校名称', field: 'scname', visible: true, align: 'center', valign: 'middle'},
            {title: '教师信息', field: 'tcontent', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Teacher.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teacher.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加teacher
 */
Teacher.openAddTeacher = function () {
    var index = layer.open({
        type: 2,
        title: '添加教师',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teacher/teacher_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看teacher详情
 */
Teacher.openTeacherDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '教师详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacher/teacher_update/' + Teacher.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看知识图谱
 */
Teacher.zstp = function () {
        var index = layer.open({
            type: 2,
            title: '知识图谱展示',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacher/zstp'
        });
        this.layerIndex = index;

};

/**
 * 删除teacher
 */
Teacher.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teacher/delete", function (data) {
            Feng.success("删除成功!");
            Teacher.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("teacherId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询teacher列表
 */
Teacher.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Teacher.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Teacher.initColumn();
    var table = new BSTable(Teacher.id, "/teacher/list", defaultColunms);
    table.setPaginationType("client");
    Teacher.table = table.init();
});
