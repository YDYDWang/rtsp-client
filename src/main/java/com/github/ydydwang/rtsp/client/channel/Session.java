package com.github.ydydwang.rtsp.client.channel;

import java.util.concurrent.atomic.AtomicLong;

public class Session {
	private final AtomicLong seq = new AtomicLong();

	public long getSeq() {
		return seq.incrementAndGet();
	}
}
