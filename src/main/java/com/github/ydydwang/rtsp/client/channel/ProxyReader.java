package com.github.ydydwang.rtsp.client.channel;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ProxyReader extends ProxyExcutor implements Runnable, Reader {
	private static final Logger logger = Logger.getLogger(Writer.class.getName());

	private final InputStream inputStream;
	private final int byteLength;
	private byte[] bytes;

	public ProxyReader(InputStream inputStream, int byteLength) {
		this.inputStream = inputStream;
		this.byteLength = byteLength;
	}

	@Override
	public void run() {
		try {
			this.bytes = new byte[this.byteLength];
			int length = inputStream.read(bytes);
			if (length >= 0) {
				this.read(new Bytes(length, bytes));
				execute(this);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public void listen() {
		execute(this);
	}
}
