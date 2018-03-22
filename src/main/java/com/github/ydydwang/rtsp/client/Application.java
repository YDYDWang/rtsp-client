package com.github.ydydwang.rtsp.client;

import java.io.IOException;

import com.github.ydydwang.rtsp.client.channel.RtspClient;

public class Application {
	private static final String HOST = "192.168.100.239";
	private static final int PORT = 554;

	public static void main(String[] args) throws IOException {
		RtspClient client = new RtspClient(HOST, PORT);
		client.connect();
	}

}
