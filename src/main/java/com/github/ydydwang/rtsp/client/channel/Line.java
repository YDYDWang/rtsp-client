package com.github.ydydwang.rtsp.client.channel;

import java.util.LinkedList;

public class Line {
	private final LinkedList<Bytes> bytesList;
	private final int from;
	private final int to;
	private final int length;

	public Line(LinkedList<Bytes> bytesList, int from, int to, int length) {
		this.bytesList = bytesList;
		this.from = from;;
		this.to = to;
		this.length = length;
	}
	public final LinkedList<Bytes> getBytesList() {
		return bytesList;
	}
	public final int getFrom() {
		return from;
	}
	public final int getTo() {
		return to;
	}
	public final int getLength() {
		return length;
	}
}
