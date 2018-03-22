package com.github.ydydwang.rtsp.client.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RtspClient {
	private static final int TIMEOUT = 3000;
	private static final Writer emptyWriter = new EmptyWriter();

	private final InetSocketAddress remote;
	private final Session session;
	private Socket socket;
	private Writer writer;

	public RtspClient(String hostname, int port) {
		this.remote = new InetSocketAddress(hostname, port);
		this.socket = new Socket();
		this.writer = emptyWriter;
		this.session = new Session();
	}

	public void connect() throws IOException {
		socket.connect(this.remote, TIMEOUT);
		this.writer = new RtspWriter(socket.getOutputStream());
		new RtspReader(socket.getInputStream(), this);
		this.write(RtspCommandBuilder.options(remote.getHostName(), remote.getPort(), session.getSeq()).getBytes());
	}

	public void write(byte[] bytes) throws IOException {
		this.writer.write(bytes);
	}

	public InetSocketAddress getRemote() {
		return remote;
	}

	public Session getSession() {
		return session;
	}
}
