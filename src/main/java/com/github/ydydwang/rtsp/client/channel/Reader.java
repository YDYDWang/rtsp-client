package com.github.ydydwang.rtsp.client.channel;

public interface Reader {

	public void listen();

	public void read(Bytes bytes);
}
