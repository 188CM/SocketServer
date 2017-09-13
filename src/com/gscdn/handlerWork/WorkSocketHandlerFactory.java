package com.gscdn.handlerWork;

import java.util.concurrent.atomic.AtomicInteger;

import com.gscdn.model.SocketHandler;
import com.gscdn.model.SocketHandlerFactory;

/**
 * Factory that returns socket handlers that simulate work being done on the server.
 */
public class WorkSocketHandlerFactory implements SocketHandlerFactory {

	public String handlerType;
	
    private final AtomicInteger nextId = new AtomicInteger();

	@Override
    public SocketHandler newSocketHandler(String handlerType) {
        int id = nextId.getAndIncrement();        
        if(handlerType=="Type2")
        	return new WorkSocketHandler2(id);
        else
        	return new WorkSocketHandler(id);
    }
}
