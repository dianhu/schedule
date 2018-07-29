package dh01.scheduler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Time : 18-7-29 下午10:00
 * Author : hcy
 * Description :Timer任务都是串行执行的，同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务
 */
public class TimerTest extends TimerTask {

    private String jobName = "";
    public TimerTest(String jobName){
        this.jobName = jobName;
    }

    public void run() {
        System.out.println("execute " + jobName);
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        long delay1 = 1*1000;
        long period1 = 1000;
        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        timer.schedule(new TimerTest("job1"),delay1,period1);

        long delay2 = 2 * 1000;
        long period2 = 2000;
        // 从现在开始 2 秒钟之后，每隔 2 秒钟执行一次 job2
        timer.schedule(new TimerTest("job2"), delay2, period2);
    }
}
