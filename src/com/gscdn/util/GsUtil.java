package com.gscdn.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.activation.MimetypesFileTypeMap;

import com.gscdn.log.LogMgr;

public class GsUtil {
	public static byte[] getResOKHader() throws UnsupportedEncodingException {
		StringBuilder r = new StringBuilder();
		r.append("HTTP/1.1 200 OK\r\n");
		r.append("Content-Type: text/html\r\n\r\n");
		return r.toString().getBytes("UTF-8");
	}

	public static byte[] getResErrHader() throws UnsupportedEncodingException {
		StringBuilder r = new StringBuilder();
		r.append("HTTP/1.0 400 Bad Request Message \\r\\n");
		r.append("Connection: close\\r\\n");
		r.append("\r\n");
		return r.toString().getBytes("UTF-8");
	}

	public static String getMineType(File file) {
		return new MimetypesFileTypeMap().getContentType(file);
	}

	public static String getReqFileName(String requestMessageLine) throws IOException {
		String fileName = null;
		StringTokenizer tokenizedLine = new StringTokenizer(requestMessageLine);

		if (tokenizedLine.nextToken().equals("GET")) {
			fileName = tokenizedLine.nextToken();

			if (fileName.startsWith("/") == true) {
				if (fileName.length() > 1) {
					fileName = fileName.substring(1);
				}
				// 파일명을 따로 입력하지 않았을 경우 기본 파일을 출력한다.
				else {
					fileName = "index.html";
				}
			}
		}
		return fileName;
	}
	
	public static void close(Closeable closeable) {
        if(closeable != null) try {
            closeable.close();
        } catch (IOException e) {
            LogMgr.getLogInstance().error("(Request #{0}) FAILED TO CLOSE STREAM: ", e);
        } finally { /* we tried! */ }
    }
	
	
}
