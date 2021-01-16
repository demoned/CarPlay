package com.cxsz.framework.tool.thread_pool;

/**
 * 线程池使用控制类
 */
public class UiThreadPoolManager extends ThreadPoolManager {
    //参数初始化
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数量大小
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    //线程池最大容纳线程数
    private static final int maximumPoolSize = CPU_COUNT * 2 + 1;
    //线程空闲后的存活时长
    private static final int keepAliveTime = 30;

    public static UiThreadPoolManager getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static final UiThreadPoolManager instance = new UiThreadPoolManager();
    }

    @Override
    protected String getThreadPoolName() {
        return "com.tool.me.ui";
    }

    @Override
    protected int corePoolSize() {
        return corePoolSize;
    }

    @Override
    protected int maximumPoolSize() {
        return maximumPoolSize;
    }

    @Override
    protected int blockingQueueCount() {
        return 200000;
    }

    @Override
    protected long keepAliveTime() {
        return keepAliveTime;
    }
}