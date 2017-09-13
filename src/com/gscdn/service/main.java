package com.gscdn.service;

import java.io.IOException;

import com.gscdn.log.LogMgr;
import com.gscdn.model.SocketHandlerFactory;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int SOCKET_PORT = 8080;
		System.out.println(LogMgr.getLogInstance());
		try {
	    	SocketHandlerFactory factory = (SocketHandlerFactory)Class.forName("com.gscdn.handlerWork.WorkSocketHandlerFactory").newInstance();
	        Server server = new Server.Setup()
                                  .setPort(SOCKET_PORT)
                                  .setHandlerFactory(factory)
                                  .build();
	        server.start();
			
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
