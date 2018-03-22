package com.github.ydydwang.rtsp.client.cache;

import java.io.InputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.collections4.CollectionUtils;

import com.github.ydydwang.rtsp.client.channel.Bytes;
import com.github.ydydwang.rtsp.client.channel.BytesInputStream;

public class BytesCacheManager {
	private static final Map<String, LinkedList<Bytes>> map = new HashMap<String, LinkedList<Bytes>>();
	private static final byte[] END = new byte[] {'\n', '\r', '\n', '\r'};

	public static boolean addAndReturnIsDone(String address, Bytes bytes) {
		if (!map.containsKey(address)) {
			synchronized (map) {
				if (!map.containsKey(address)) {
					map.put(address, new LinkedList<Bytes>());
				}
			}
		}
		LinkedList<Bytes> list = map.get(address);
		list.add(bytes);
		return isTail(list, -1);
	}

	public static InputStream get(String address) {
		Queue<Bytes> queue = map.get(address);
		return new BytesInputStream(queue);
	}

	public static void remove(String address) {
		List<Bytes> byteBufList = map.get(address);
		if (CollectionUtils.isNotEmpty(byteBufList)) {
			map.remove(address);
		}
	}

	private static boolean isTail(Deque<Bytes> deque, int index) {
		Iterator<Bytes> iterator = deque.descendingIterator();
		while (iterator.hasNext()) {
			Bytes bytes = iterator.next();
			if (bytes.getLength() > 0) {
				if (END[++index] == bytes.getBytes()[bytes.getLength() - 1]) {
					if (bytes.getLength() > 1) {
						if (END[++index] == bytes.getBytes()[bytes.getLength() - 2]) {
							if (bytes.getLength() > 2) {
								if (END[++index] == bytes.getBytes()[bytes.getLength() - 3]) {
									if (bytes.getLength() > 3) {
										if (END[++index] == bytes.getBytes()[bytes.getLength() - 4]) {
											return Boolean.TRUE;
										} else {
											break;
										}
									}
								} else {
									break;
								}
							}
						} else {
							break;
						}
					}
				} else {
					break;
				}
			}
		}
		return Boolean.FALSE;
	}
}
