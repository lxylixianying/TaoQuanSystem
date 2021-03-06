package com.system.entity;

public class SqlLog {
    private Integer id;       //任务ID
    private String task_type;      //任务类型
    private String obj;       //任务对象
    private String status;    //任务状态
    private String code;      //任务代码
    private String create_time;  //创建时间
    private String start_time;  //开始时间
    private String end_time;    //结束时间

    public SqlLog() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return task_type;
    }

    public void setType(String type) {
        this.task_type = type;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
