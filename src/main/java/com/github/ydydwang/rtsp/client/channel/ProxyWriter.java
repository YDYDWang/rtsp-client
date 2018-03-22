package com.github.ydydwang.rtsp.client.channel;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProxyWriter extends ProxyExcutor implements Runnable, Writer {
	private static final Logger logger = Logger.getLogger(Writer.class.getName());

	private final OutputStream outputStream;
	private byte[] bytes;

	public ProxyWriter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void run() {
		try {
			outputStream.write(bytes);
			outputStream.flush();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public void write(byte[] bytes) {
		this.bytes = bytes;
		execute(this);
	}
}
