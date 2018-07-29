package dh01.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Time : 18-7-29 下午10:31
 * Author : hcy
 * Description :
 */
public class QuartzTest {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建scheduler容器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //定义一个trigger
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")//定义name/group
                .startNow()//一旦加入scheduler,立即生效
                .withSchedule(simpleSchedule()////使用SimpleSchedule
                        .withIntervalInSeconds(1)//每隔一秒执行一次
                        .repeatForever())//一直执行
                .build();

        //定义一个jobDetail
        JobDetail jobDetail = newJob(HelloQuartz.class)//定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("job1", "group1")
                .usingJobData("name", "quartz")//定义属性
                .build();

        //加入到调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //启动
        scheduler.start();

        Thread.sleep(10000);
        scheduler.shutdown();
    }


}
