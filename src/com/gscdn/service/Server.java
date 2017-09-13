package com.gscdn.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gscdn.log.LogMgr;
import com.gscdn.model.SocketHandler;
import com.gscdn.model.SocketHandlerFactory;

public class Server {

	private final int port;
	private final SocketHandlerFactory handlerFactory;
	private final ExecutorService thpool = Executors.newCachedThreadPool();

	private Server(int port, SocketHandlerFactory handlerFactory) {
		this.port = port;
		this.handlerFactory = handlerFactory;
	}

	public void start() throws IOException {
		LogMgr.getLogInstance().info("connections on port ::" + port);
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		while (true) {
			try {
				//blocking
				final Socket clientSocket = serverSocket.accept();
				final SocketHandler handler = handlerFactory.newSocketHandler("Type2");			
				thpool.submit(new Thread() {
					public void run() {
						handler.handleSocket(clientSocket);
					}
				});

			} catch (IOException ex) {
				LogMgr.getLogInstance().error("ERROR:: ", ex);
				serverSocket.close();
			} finally {
				
			}
		}
	}

	public static class Setup {
		private int port;
		private SocketHandlerFactory handlerFactory;
		
		public Setup setPort(int port) {
			this.port = port;
			return this;
		}

		public Setup setHandlerFactory(SocketHandlerFactory handlerFactory) {
			this.handlerFactory = handlerFactory;
			return this;
		}

		public Server build() {
			if (port < 0) {
				throw new IllegalStateException("port do not have defaults");
			}
			return new Server(port, handlerFactory);
		}
	}

}
