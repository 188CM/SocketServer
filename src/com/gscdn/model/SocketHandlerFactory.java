package com.gscdn.model;

public interface SocketHandlerFactory {
    public SocketHandler newSocketHandler(String handlerType);    
}
