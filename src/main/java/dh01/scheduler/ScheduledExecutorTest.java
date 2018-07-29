package dh01.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Time : 18-7-29 下午10:15
 * Author : hcy
 * Description :
 */
public class ScheduledExecutorTest implements Runnable {
    private String jobName = "";

    public ScheduledExecutorTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("execute " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1,基于固定时间间隔
        service.scheduleAtFixedRate(new ScheduledExecutorTest("job1"),initialDelay1,period1, TimeUnit.SECONDS);

        long initialDelay2 = 2;
        long delay2 = 2;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2,取决于每次任务执行的时间长短，基于不固定时间间隔
        service.scheduleWithFixedDelay(
                new ScheduledExecutorTest("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
    }

}
