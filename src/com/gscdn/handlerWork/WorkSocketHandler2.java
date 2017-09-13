package com.gscdn.handlerWork;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.gscdn.log.LogMgr;
import com.gscdn.model.SocketHandler;
import com.gscdn.util.GsUtil;


class WorkSocketHandler2 implements SocketHandler {

    private int id;

    WorkSocketHandler2(int id) {
        this.id = id;
    }

    @Override
    public void handleSocket(Socket clientSocket) {

    	BufferedReader in= null;
		DataOutputStream out = null;

        try {
            in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());

            String line = in.readLine();
            receiveMessage(line);          
            sendResponse(line, out);

        } catch (IOException ex) {
            LogMgr.getLogInstance().error("(Request #{0}) ERROR: ", ex);
        } finally {
            GsUtil.close(in);
            GsUtil.close(out);
            LogMgr.getLogInstance().info("(Request #{0}) FINISHED", id);
        }
    }
   
    private void receiveMessage(String line) throws IOException {
        LogMgr.getLogInstance().info("(Request #{0}) Received message: {1}", id, line);
    }


    private void sendResponse(String line, DataOutputStream out) throws IOException {
        
        String fileName = GsUtil.getReqFileName(line);
		File file = new File(fileName);
		FileInputStream inFile = new FileInputStream(fileName);
		byte[] fileInBytes = new byte[(int) file.length()];
		inFile.read(fileInBytes);
		out.write(GsUtil.getResOKHader());
		out.write(fileInBytes, 0, (int)file.length());     
		inFile.close();
    }
    
}
