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
		return isTail(list, 0);
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
			Boolean isTail = isTail(bytes, index, 0);
			if (isTail != null) {
				if (Boolean.FALSE == isTail) {
					break;
				}
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	private static Boolean isTail(Bytes bytes, int index, int length) {
		if (index == 4) {
			return Boolean.TRUE;
		} else if (bytes.getLength() > length) {
			if (END[index] == bytes.getBytes()[bytes.getLength() - length - 1]) {
				return isTail(bytes, ++index, ++length);
			}
			return Boolean.FALSE;
		}
		return null;
	}
}
