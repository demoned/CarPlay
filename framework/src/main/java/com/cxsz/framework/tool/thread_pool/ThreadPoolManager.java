package com.cxsz.framework.tool.thread_pool;

import java.util.concurrent.TimeUnit;

public abstract class ThreadPoolManager {
    private ThreadPool executor = null;

    public ThreadPoolManager() {
        if (executor == null) {
            executor = new ThreadPool(getThreadPoolName(), corePoolSize(), maximumPoolSize(), keepAliveTime(), TimeUnit.SECONDS, blockingQueueCount());
        }
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * @return name
     * 线程池名称 the String of pool name
     */
    protected abstract String getThreadPoolName();

    /**
     * @return corePoolSize
     * 核心线程池大小 the number of threads to keep in the pool, even if
     * they are idle, unless {@code allowCoreThreadTimeOut} is set
     */
    protected abstract int corePoolSize();

    /**
     * @return maximumPoolSize
     * 最大线程池大小 the maximum number of threads to allow in the pool
     */
    protected abstract int maximumPoolSize();

    /**
     * @return blockingQueueCount
     * 阻塞任务队列数
     */
    protected abstract int blockingQueueCount();

    /**
     * @return keepAliveTime
     * 线程池中超过corePoolSize数目的空闲线程最大存活时间 when the number of threads is
     * greater than the core, this is the maximum time that excess
     * idle threads will wait for new tasks before terminating.
     */
    protected abstract long keepAliveTime();
}