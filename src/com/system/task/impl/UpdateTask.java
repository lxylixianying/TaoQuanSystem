package com.system.task.impl;

import com.system.dao.LogDao;
import com.system.entity.SqlLog;
import com.system.task.ProcessTask;
import com.system.spring.SpringTool;
import com.system.task.UpdateGoodsData;
import com.system.task.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class UpdateTask implements Task {
    private ProcessTask processTask;
    private LogDao dao;
    private Integer id;    //任务id
    private SqlLog log;   //log
    private UpdateGoodsData update_data;  //
    private String obj;     // 更新的对象
    private UpdateTimer timer;   //定时任务计时器

    public UpdateTask() {
        processTask = new ProcessTask();
        dao = (LogDao) SpringTool.getBean("logDao");
    }

    // 初始化
    @Override
    public void init(String obj) {
        this.obj = obj;
        if (obj.equals("tkzs")) {
            update_data = new UpdateTkzsData();
        }
        if (obj.equals("dtklm")) {
            update_data = new UpdateDtklmData();
        }
    }

    @Override
    // 创建并插入日志
    public void createLog() {
        id = dao.getAllLogNum() + 1;
        log = new SqlLog();
        log.setId(id);
        log.setType("update");
//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now_time = df.format(new Date());
        log.setCreate_time(now_time);
        log.setObj(obj);


        log.setStatus("wait");
        log.setCode("wait");
        // 插入日志表
        dao.insertLog(log);
        System.out.println("插入日志");
    }

    // 执行任务
    @Override
    public void run() {
        timer = new UpdateTimer(update_data);
        timer.setLog(log);
        timer.setTask(this);
        Timer temp_timer = new Timer();
        temp_timer.scheduleAtFixedRate(timer, 0, 1000);
    }

    // 任务结束执行操作
    @Override
    public void end() {
        processTask.scanTask();
    }

    // 检查是否正在执行
    @Override
    public String getStatus() {
        return log.getStatus();
    }

    @Override
    public void setLog(SqlLog log) {
        this.log = log;
        init(log.getObj());
    }

}
