/**
 * 初始化school详情对话框
 */
var SchoolInfoDlg = {
    schoolInfoData : {}
};

/**
 * 清除数据
 */
SchoolInfoDlg.clearData = function() {
    this.schoolInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SchoolInfoDlg.set = function(key, val) {
    this.schoolInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SchoolInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SchoolInfoDlg.close = function() {
    parent.layer.close(window.parent.School.layerIndex);
}

/**
 * 收集数据
 */
SchoolInfoDlg.collectData = function() {
    this
    .set('id')
    .set('scname');
}

/**
 * 提交添加
 */
SchoolInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/school/add", function(data){
        Feng.success(data+"!!!");
        window.parent.School.table.refresh();
        SchoolInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.schoolInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SchoolInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/school/update", function(data){
        Feng.success("修改成功!");
        window.parent.School.table.refresh();
        SchoolInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.schoolInfoData);
    ajax.start();
}

$(function() {

});
