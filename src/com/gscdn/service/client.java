package com.gscdn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		try {

			Socket sock = new Socket("localhost", 8080);

			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			pw.println(line);
			pw.flush();

			String echo = br.readLine();
			System.out.println(echo);

			pw.close();
			br.close();
			sock.close();
		} catch (Exception e) {
			System.out.println(e);
		}
				
	}


}
