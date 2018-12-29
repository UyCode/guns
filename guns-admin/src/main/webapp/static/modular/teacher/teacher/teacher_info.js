/**
 * 初始化teacher详情对话框
 */
var TeacherInfoDlg = {
    teacherInfoData : {}
};

/**
 * 清除数据
 */
TeacherInfoDlg.clearData = function() {
    this.teacherInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.set = function(key, val) {
    this.teacherInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeacherInfoDlg.close = function() {
    parent.layer.close(window.parent.Teacher.layerIndex);
}

/**
 * 收集数据
 */
TeacherInfoDlg.collectData = function() {
    this
    .set('id')
    .set('tname')
    .set('tcontent')
    .set('scname')
    .set('coname');
}

/**
 * 提交添加
 */
TeacherInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacher/add", function(data){
        Feng.success("添加成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeacherInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacher/update", function(data){
        Feng.success("修改成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

$(function() {
    $("#scname").val($("#scnameValue").val());
    var ajaxx =new $ax(Feng.ctxPath +"/school/list",function(data){
        var school_list=data;
        console.log(school_list);
        $("#scname").append("<option value='"+$("#scnameValue").val()+"'>"+$("#scnameValue").val()+"</option>");
        for(var temp in school_list){
            if($("#scnameValue").val()!=school_list[temp].scname){
                $("#scname").append("<option value='"+school_list[temp].scname+"'>"+school_list[temp].scname+"</option>");
            }
        }
    },function (data){
        Feng.error("传参失败！"+data.responseJSON.message+"!");
    });
    ajaxx.start();

    $("#coname").val($("#conameValue").val());
    var ajaxx =new $ax(Feng.ctxPath +"/college/list",function(data){
        var college_list=data;
        console.log(college_list);
        $("#coname").append("<option value='"+$("#conameValue").val()+"'>"+$("#conameValue").val()+"</option>");
        for(var temp in college_list){
            if($("#conameValue").val()!=college_list[temp].coname){
                $("#coname").append("<option value='"+college_list[temp].coname+"'>"+college_list[temp].coname+"</option>");
            }
        }
    },function (data){
        Feng.error("传参失败！"+data.responseJSON.message+"!");
    });
    ajaxx.start();
});
