package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("feedback")
public class Feedback extends Model<Feedback> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 反馈用户id
     */
    private Integer userid;
    /**
     * 反馈内容
     */
    private String fbcontent;
    /**
     * 反馈时间
     */
    private Date fbtime;

    public int status = 1;    //状态，用来验证空数据。


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFbcontent() {
        return fbcontent;
    }

    public void setFbcontent(String fbcontent) {
        if(fbcontent == null || fbcontent.equals("")){
            status = 0;
        }else{
            this.fbcontent = fbcontent;
        }
    }

    public Date getFbtime() {
        return fbtime;
    }

    public void setFbtime(Date fbtime) {
        this.fbtime = fbtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Feedback{" +
        "id=" + id +
        ", userid=" + userid +
        ", fbcontent=" + fbcontent +
        ", fbtime=" + fbtime +
        "}";
    }
}
