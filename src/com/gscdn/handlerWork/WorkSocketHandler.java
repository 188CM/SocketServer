package com.gscdn.handlerWork;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.gscdn.log.LogMgr;
import com.gscdn.model.SocketHandler;
import com.gscdn.model.SocketHandlerFactory;
import com.gscdn.util.GsUtil;


class WorkSocketHandler implements SocketHandler {

    private int id;

    WorkSocketHandler(int id) {
        this.id = id;
    }

    @Override
    public void handleSocket(Socket clientSocket) {

        InputStream in = null;
        OutputStream out = null;
        try {
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();

            receiveMessage(in);
            sendResponse(out);

        } catch (IOException ex) {
            LogMgr.getLogInstance().error("(Request #{0}) ERROR: ", ex);
        } finally {
            GsUtil.close(in);
            GsUtil.close(out);
            LogMgr.getLogInstance().info("(Request #{0}) FINISHED", id);
        }
    }

    private void receiveMessage(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        LogMgr.getLogInstance().info("(Request #{0}) Received message: {1}", id, line);
//        System.out.println(LogMgr.getLogInstance());
    }


    private void sendResponse(OutputStream out) throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(out));
        output.write("Received your command! Processing...");
        output.flush();
        printDots(output);
        output.write("OK");
        output.newLine();
        output.flush();
    }


    private void printDots(BufferedWriter out) throws IOException {
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        for(int i = 0; i < count; i++) {
            out.write(".");
            out.flush();
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
            } catch (InterruptedException e) {
                LogMgr.getLogInstance().error("(Request #{0}) Work interrupted! ", e, id);
            }
        }

    }
    
}
