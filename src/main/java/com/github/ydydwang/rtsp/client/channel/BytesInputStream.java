package com.github.ydydwang.rtsp.client.channel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Queue;

public class BytesInputStream extends InputStream {

	protected Queue<Bytes> queue;
	protected Bytes buf;
	protected int pos;
	protected int mark = 0;
	protected int count;

	public BytesInputStream(Queue<Bytes> queue) {
		Iterator<Bytes> iterator = queue.iterator();
		while (iterator.hasNext()) {
			this.count += iterator.next().getLength();
		}
		this.queue = queue;
		this.pos = 0;
		this.buf = queue.poll();
	}

	public int read() {
		return -1;
	}

	public int read(byte b[], int off, int len) {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		}

		if (buf == null) {
			return -1;
		}

		int avail = buf.getLength() - pos;
		if (len > avail) {
			len = avail;
		}
		if (len <= 0) {
			return 0;
		}
		System.arraycopy(buf.getBytes(), pos, b, off, len);
		pos += len;
		if (pos == buf.getLength()) {
			this.buf = queue.poll();
		}
		return len;
	}

	public synchronized long skip(long n) {
		return -1;
	}

	public int available() {
		return count - pos;
	}

	public boolean markSupported() {
		return false;
	}

	public void mark(int readAheadLimit) {
		mark = pos;
	}

	public synchronized void reset() {
		pos = mark;
	}

	public void close() throws IOException {
	}

}
