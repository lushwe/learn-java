package com.lushwe.thread;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 说明：线程池并行处理任务测试
 *
 * @author Jack Liu
 * @date 2019-12-31 17:54
 * @since 0.1
 */
public class ThreadPoolTest {

    private static final ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1024), (r, executor) -> {
        System.out.println(JSON.toJSONString(executor));
        System.out.println("任务 " + r + " 丢失");
    });


    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        List<Boolean> results = executeList(Arrays.asList("1", "2", "3", "4"));
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
        System.out.println(JSON.toJSONString(results, true));

        executorService.shutdown();
    }


    static List<Boolean> executeList(List<String> codes) throws ExecutionException, InterruptedException {

        InheritableThreadLocalContext.set("主线程设置B");

        List<Callable<Boolean>> tasks = new ArrayList<>(codes.size());
        for (String code : codes) {
            tasks.add(new CheckTask(code));
        }

        // 使用场景，批量校验车型库存

        List<Future<Boolean>> futures = executorService.invokeAll(tasks);

        List<Boolean> results = new ArrayList<>(futures.size());
        for (Future<Boolean> future : futures) {
            results.add(future.get());
        }

        return results;
    }


    static class CheckTask implements Callable<Boolean> {

        private final String code;

        public CheckTask(String code) {
            this.code = code;
        }

        @Override
        public Boolean call() {

            System.out.println("子线程获取的结果2：" + InheritableThreadLocalContext.get());

            System.out.println("执行：" + code);

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        }
    }
}
