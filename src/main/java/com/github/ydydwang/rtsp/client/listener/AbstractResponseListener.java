package com.github.ydydwang.rtsp.client.listener;

import java.util.LinkedList;
import java.util.Queue;

import com.github.ydydwang.rtsp.client.channel.Bytes;
import com.github.ydydwang.rtsp.client.channel.Header;
import com.github.ydydwang.rtsp.client.channel.Line;

public abstract class AbstractResponseListener implements ResponseListener {
	private static final byte[] END = new byte[] {'\r', '\n'}; 

	@Override
	public void accept(Queue<Bytes> queue) throws Exception {
		Line line = extractRequestLine(queue);
		handleRequestLine(line);
		LinkedList<Header> list = extractHeaders(queue);
	}

	private Line extractRequestLine(Queue<Bytes> queue) throws Exception {
		int from = 0;
		int to = 0;
		int length = 0;
		int index = 0;
		while (!queue.isEmpty()) {
			Bytes bytes = queue.poll();
			for (int i = 0; i < bytes.getLength(); i++) {
				byte b = bytes.getBytes()[i];
				if (b == END[index++]) {
					
				}
			}
		}
		return null;
	}

	private LinkedList<Header> extractHeaders(Queue<Bytes> queue) throws Exception {
		return null;
	}

	private void handleRequestLine(Line line) throws Exception {
	}

	protected abstract void accept(LinkedList<Header> list) throws Exception;
}
