package com.github.ydydwang.rtsp.client.channel;

public class Bytes {
	private final int length;
	private final byte[] bytes;

	public Bytes(int length, byte[] bytes) {
		this.length = length;
		this.bytes = bytes;
	}
	public int getLength() {
		return length;
	}
	public byte[] getBytes() {
		return bytes;
	}
}
