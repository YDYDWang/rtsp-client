package com.github.ydydwang.rtsp.client.listener;

import java.util.Queue;

import com.github.ydydwang.rtsp.client.channel.Bytes;

public interface ResponseListener {

	public void accept(Queue<Bytes> queue) throws Exception;
}
