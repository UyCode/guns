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
@TableName("college")
public class College extends Model<College> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 学院名称
     */
    private String coname;
    /**
     * 学校名称
     */
    private String scname;
    /**
     * 网址
     */
    private String web;

    public int status = 1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        if(coname == null || coname.equals("")){
            status = 0;
        }else{
            this.coname = coname;
        }
    }

    public String getScname() {
        return scname;
    }

    public void setScname(String scname) {
        if(scname == null || scname.equals("")){
            status = 0;
        }else {
            this.scname = scname;
        }
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        if(web == null || web.equals("")){
            status = 0;
        }else {
            this.web = web;
        }
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "College{" +
        "id=" + id +
        ", coname=" + coname +
        ", scname=" + scname +
        ", web=" + web +
        "}";
    }
}
