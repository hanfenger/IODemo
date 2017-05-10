package com.lk.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author LK
 * @date 2017年5月9日00:51:12
 */
public class TimeServerHandlerExecutePool {
	private ExecutorService executor;
	public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize){
		//availableProcessors()方法返回到Java虚拟机的可用的处理器数量
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
	}
	public void execute(Runnable task){
		executor.execute(task);
	}
}
