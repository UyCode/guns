/**
 * 初始化college详情对话框
 */
var CollegeInfoDlg = {
    collegeInfoData : {}
};

/**
 * 清除数据
 */
CollegeInfoDlg.clearData = function() {
    this.collegeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CollegeInfoDlg.set = function(key, val) {
    this.collegeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CollegeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CollegeInfoDlg.close = function() {
    parent.layer.close(window.parent.College.layerIndex);
}

/**
 * 收集数据
 */
CollegeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('coname')
    .set('scname')
    .set('web');
}

/**
 * 提交添加
 */
CollegeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/college/add", function(data){
        Feng.success(data);
        window.parent.College.table.refresh();
        CollegeInfoDlg.close();
    },function(data){
        Feng.error("错误：不能添加空数据!-->" + data.responseJSON.message + "!");
    });
    ajax.set(this.collegeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CollegeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/college/update", function(data){
        Feng.success(data);
        window.parent.College.table.refresh();
        CollegeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.collegeInfoData);
    ajax.start();
}

$(function() {
    $("#scname").val($("#scnameValue").val());
    var ajaxx =new $ax(Feng.ctxPath +"/school/list",function(data){
        var school_list=data;
        for(var temp in school_list){
                $("#scname").append("<option value='"+school_list[temp].scname+"'>"+school_list[temp].scname+"</option>");
        }
    },function (data){
        Feng.error("传参失败！"+data.responseJSON.message+"!");
    });
    ajaxx.start();
});
