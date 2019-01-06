package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2018-11-23
 */
@TableName("teacher")
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 教师姓名
     */
    private String tname;
    /**
     * 教师信息
     */
    private String tcontent;
    /**
     * 学校名称
     */
    private String scname;
    /**
     * 学院名称
     */
    private String coname;

    public int status =1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        if(tname ==null || tname.equals("")){
            status = 0;
        }else{
            this.tname = tname;
        }
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        if(tcontent ==null || tcontent.equals("")){
            status = 0;
        }else{
            this.tcontent = tcontent;
        }
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        if(scname ==null || scname.equals("") || scname.equals("undefined")){
            status = 0;
        }else{
            this.scname = scname;
        }

    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        if(coname == null || coname.equals("") || coname.equals("undefined")){
            status = 0;
        }else{
            this.coname = coname;
        }
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        "id=" + id +
        ", tname=" + tname +
        ", tcontent=" + tcontent +
        ", scname=" + scname +
        ", coname=" + coname +
        "}";
    }
}
