package com.voids.ben.searchexpandtabview.bean;

import java.io.Serializable;

/**
 *@Description: 区划
 */
public class AreaBean implements Serializable {



    public static final String SELECT_ID        = "select_id";
    public static final String SELECT_NAME        = "select_name";

    private Long               id;

    /** 
     *@Fields areaId : 区 ID
     */
    private String             areaId;

    /** 
     *@Fields areaName :  区 Name
     */
    private String             areaName;

    /** 
     *@Fields pId : 区 Pid
     */
    private String             pid;

    
    private boolean            checked;

    /**
     * @Fields 备注
     */
    private String             remark1;

    /**
     * @Fields 备注
     */
    private String             remark2;

    /**
     * @Fields 备注
     */
    private String             remark3;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAreaId(){
        return areaId;
    }

    public void setAreaId(String areaId){
        this.areaId = areaId;
    }

    public String getAreaName(){
        return areaName;
    }

    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    public String getPid(){
        return pid;
    }

    public void setPid(String pid){
        this.pid = pid;
    }

    public boolean isChecked(){
        return checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public String getRemark1(){
        return remark1;
    }

    public void setRemark1(String remark1){
        this.remark1 = remark1;
    }

    public String getRemark2(){
        return remark2;
    }

    public void setRemark2(String remark2){
        this.remark2 = remark2;
    }

    public String getRemark3(){
        return remark3;
    }

    public void setRemark3(String remark3){
        this.remark3 = remark3;
    }

    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        } else {
            if (this.getClass () == o.getClass ()) {
                AreaBean item = (AreaBean) o;
                if (this.getAreaId ().equals (item.getAreaId ())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
