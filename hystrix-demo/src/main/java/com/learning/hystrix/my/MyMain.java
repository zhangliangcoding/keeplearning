package com.learning.hystrix.my;

import com.learning.hystrix.UserAccount;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhangll
 * @Date: 2019/1/23 11:47
 * @Description:
 */
public class MyMain {

    private final ThreadPoolExecutor pool =
            new ThreadPoolExecutor(5, 5, 5,
                    TimeUnit.DAYS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());

    public void startMetricsMonitor() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    /**
                     * Since this is a simple example and we know the exact HystrixCommandKeys we are interested in
                     * we will retrieve the HystrixCommandMetrics objects directly.
                     *
                     * Typically you would instead retrieve metrics from where they are published which is by default
                     * done using Servo: https://github.com/Netflix/Hystrix/wiki/Metrics-and-Monitoring
                     */

                    // wait 5 seconds on each loop
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        // ignore
                    }

                    // we are using default names so can use class.getSimpleName() to derive the keys
                    HystrixCommandMetrics myCommandMetrics = HystrixCommandMetrics.getInstance(HystrixCommandKey.Factory.asKey(MyCommand.class.getSimpleName()));

                    // print out metrics
                    StringBuilder out = new StringBuilder();
                    out.append("\n");
                    out.append("#####################################################################################").append("\n");
                    out.append("# MyCommand: " + getStatsStringFromMetrics(myCommandMetrics)).append("\n");
                    out.append("#####################################################################################").append("\n");
                    System.out.println(out.toString());
                }
            }

            private String getStatsStringFromMetrics(HystrixCommandMetrics metrics) {
                StringBuilder m = new StringBuilder();
                if (metrics != null) {
                    HystrixCommandMetrics.HealthCounts health = metrics.getHealthCounts();
                    m.append("Requests: ").append(health.getTotalRequests()).append(" ");
                    m.append("Errors: ").append(health.getErrorCount()).append(" (").append(health.getErrorPercentage()).append("%)   ");
                    m.append("Mean: ").append(metrics.getExecutionTimePercentile(50)).append(" ");
                    m.append("75th: ").append(metrics.getExecutionTimePercentile(75)).append(" ");
                    m.append("90th: ").append(metrics.getExecutionTimePercentile(90)).append(" ");
                    m.append("99th: ").append(metrics.getExecutionTimePercentile(99)).append(" ");
                }
                return m.toString();
            }

        });
        t.setDaemon(true);
        t.start();
    }


    public void runSimulatedRequestOnThread() {
        pool.execute(new Runnable() {

            @Override
            public void run() {
                HystrixRequestContext context = HystrixRequestContext.initializeContext();
                try{
                    UserAccount user = new MyCommand().execute();
                    System.out.println("Request => " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    context.shutdown();
                }
            }

        });

    }

    public void startDemo() {
        startMetricsMonitor();
        while (true) {
            runSimulatedRequestOnThread();
        }
    }

    public static void main(String[] args) {
        HystrixPlugins hystrixPlugins = HystrixPlugins.getInstance();
        MyMain main = new MyMain();
        main.startDemo();
    }
}
