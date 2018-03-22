package com.github.ydydwang.rtsp.client.channel;

import java.util.LinkedList;

public class Header extends Line {
	private final int keyTo;
	private final int keyLength;
	private final int valueFrom;
	private final int valueLength;

	public Header(LinkedList<Bytes> bytesList, int from, int to, int length
			, int keyTo, int keyLength, int valueFrom, int valueLength) {
		super(bytesList, from, to, length);
		this.keyTo = keyTo;
		this.keyLength = keyLength;;
		this.valueFrom = valueFrom;
		this.valueLength = valueLength;
	}
	public final int getKeyTo() {
		return keyTo;
	}
	public final int getKeyLength() {
		return keyLength;
	}
	public final int getValueFrom() {
		return valueFrom;
	}
	public final int getValueLength() {
		return valueLength;
	}
}
