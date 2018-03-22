package com.github.ydydwang.rtsp.client.channel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProxyExcutor {
	private static final ExecutorService executorService;

	static {
		int count = Runtime.getRuntime().availableProcessors();
		executorService = Executors.newFixedThreadPool(count);
	}

	public void execute(Runnable command) {
		executorService.execute(command);
	}
}
