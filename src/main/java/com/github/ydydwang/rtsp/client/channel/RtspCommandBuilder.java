package com.github.ydydwang.rtsp.client.channel;

public class RtspCommandBuilder {
	private static final String OPTIONS = "OPTIONS";
	private static final String SPACE = " ";
	private static final String RTSP = "rtsp://";
	private static final String COLON = ":";
	private static final String RTSP_VERSION = "RTSP/1.0";
	private static final String C_SEQ_HEAD = "Cseq";
	private static final String CR = "\r";
	private static final String LF = "\n";

	public static String options(String hostname, int port, long seq) {
		StringBuilder sb = new StringBuilder();
		requestLine(sb, OPTIONS, hostname, port);
		seq(sb, seq);
		end(sb);
		return sb.toString();
	}

	private static void requestLine(StringBuilder sb, String command, String hostname, int port) {
		sb.append(command)
				.append(SPACE)
				.append(RTSP)
				.append(hostname)
				.append(COLON)
				.append(port)
				.append(SPACE)
				.append(RTSP_VERSION);
		end(sb);
	}

	private static void seq(StringBuilder sb, long seq) {
		sb.append(C_SEQ_HEAD)
				.append(COLON)
				.append(SPACE)
				.append(seq);
		end(sb);
	}

	private static void end(StringBuilder sb) {
		sb.append(CR)
				.append(LF);
	}
}
