package com.github.ydydwang.rtsp.client.listener;

import java.util.LinkedList;
import java.util.Queue;

import com.github.ydydwang.rtsp.client.channel.Bytes;
import com.github.ydydwang.rtsp.client.channel.Header;
import com.github.ydydwang.rtsp.client.channel.Line;

public abstract class AbstractResponseListener implements ResponseListener {

	@Override
	public void accept(Queue<Bytes> queue) throws Exception {
		Line line = extractRequestLine(queue);
		handleRequestLine(line);
		LinkedList<Header> list = extractHeaders(queue);
	}

	private Line extractRequestLine(Queue<Bytes> queue) throws Exception {
		int from = 0;
		int to = -1;
		int length = 0;
		LinkedList<Bytes> bytesList = new LinkedList<Bytes>();
		while (!queue.isEmpty()) {
			Bytes bytes = queue.poll();
			bytesList.add(bytes);
			for (int i = 0; i < bytes.getLength(); i++) {
				byte b = bytes.getBytes()[i];
				if (b == '\r') {
					int nextIndex = i + 1;
					if (nextIndex < bytes.getLength()) {
						if (bytes.getBytes()[nextIndex] == '\n') {
							return new Line(bytesList, from, to, length);
						}
					} else {
						if (!queue.isEmpty()) {
							Bytes nextBytes = queue.peek();
							if (nextBytes.getBytes()[0] == '\n') {
								return new Line(bytesList, from, to, length);
							}
						}
					}
				} else {
					to = i;
					length++;
				}
			}
		}
		return null;
	}

	private LinkedList<Header> extractHeaders(Queue<Bytes> queue) throws Exception {
		return null;
	}

	protected abstract void accept(LinkedList<Header> list) throws Exception;

	private void handleRequestLine(Line line) throws Exception {
	}
}
