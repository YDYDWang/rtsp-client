package com.github.ydydwang.rtsp.client.util;

import java.net.InetSocketAddress;

public class SocketAddressUtils {
	private static final String COLON = ":";

	public static String getAddress(InetSocketAddress socketAddress) {
		return socketAddress.getHostString() + COLON + socketAddress.getPort();
	}

	public static String getIp(InetSocketAddress socketAddress) {
		return socketAddress.getHostString();
	}

}
