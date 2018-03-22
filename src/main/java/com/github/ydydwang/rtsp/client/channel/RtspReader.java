package com.github.ydydwang.rtsp.client.channel;

import java.io.InputStream;

import com.github.ydydwang.rtsp.client.cache.BytesCacheManager;
import com.github.ydydwang.rtsp.client.util.SocketAddressUtils;

public class RtspReader extends ProxyReader {
	private final RtspClient client;

	private static final int BYTE_LENGTH = 1024;

	public RtspReader(InputStream inputStream, RtspClient client) {
		super(inputStream, BYTE_LENGTH);
		this.client = client;
		listen();
	}

	@Override
	public void read(Bytes bytes) {
		String address = SocketAddressUtils.getAddress(client.getRemote());
		boolean done = BytesCacheManager.addAndReturnIsDone(address, bytes);
		if (done) {
			InputStream inputStream = BytesCacheManager.get(address);
			try {
				byte[] b = new byte[1024];
				while (inputStream.read(b) != -1) {
					System.out.println(new String(b));
				}
			} catch (Exception e) {
				System.err.println(e);
				// TODO disconnect
			}
		}
	}
}
