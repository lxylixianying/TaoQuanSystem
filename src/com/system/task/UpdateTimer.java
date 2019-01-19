package com.system.task;

import com.system.dao.LogDao;
import com.system.entity.SqlLog;
import com.system.task.UpdateGoodsData;
import com.system.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

@Component
public class UpdateTimer extends TimerTask {

    @Autowired
    LogDao dao;
    private UpdateGoodsData goods_data;
    private Boolean start;   // 开始标志
    private Boolean end;     // 结束标志
    private SqlLog log;      // 日志
    private Task task;

    public UpdateTimer(UpdateGoodsData goods) {
        this.goods_data = goods;
        this.start = false;
        this.end = false;
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dao = (LogDao) ctx.getBean("logDao");
    }

    //开始任务
    @Override
    public void run() {
        if (!start) {
            start = true;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now_time = df.format(new Date()).toString();
            log.setStart_time(now_time);
            log.setStatus("running");
            log.setCode("running");
            dao.updateLog(log);
        }

        goods_data.runGetData();
        //没有更多数据可以读取
        if (goods_data.getStatusCode() == "success") {
            this.cancel();
            end = true;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now_time = df.format(new Date()).toString();
            log.setEnd_time(now_time);
            log.setStatus("success");
            log.setCode("success");
            dao.updateLog(log);
            task.end();
            dao = null;
        }
//        if (goods_data.getStatusCode() == "parse error") {
//            this.cancel();
//            end = true;
//        }
//        if (goods_data.getStatusCode() == "connection error") {
//            this.cancel();
//            end = true;
//        }
    }

    // 设置获取商品数据类
    public void setGoods_data(UpdateGoodsData goods) {
        this.goods_data = goods;
    }

    // 设置日志类
    public void setLog(SqlLog log) {
        this.log = log;
    }

    // 设置任务类
    public void setTask(Task task) {
        this.task = task;
    }
}

