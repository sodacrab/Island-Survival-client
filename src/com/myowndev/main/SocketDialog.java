package com.myowndev.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.myowndev.main.Main.*;

/**
 * Created by Iwan on 02.08.2016.
 */
public class SocketDialog extends Thread {

    private Socket serverSocket = null;

    public static BufferedReader in = null;
    public static PrintWriter out = null;
    public BufferedReader consoleInput = null;

    public static String fromServer = null;

    public SocketDialog(String ip, int port) {
        try {
            serverSocket = new Socket(ip, port);

            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream())); // get info from server
            out = new PrintWriter(serverSocket.getOutputStream(), true); // send info to server
            consoleInput = new BufferedReader(new InputStreamReader(System.in)); // write string in console

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void get_update() {
        try {
            fromServer = in.readLine();
            String s[] = fromServer.split(",");
            p1.setX(Double.parseDouble(s[0]));
            p1.setY(Double.parseDouble(s[1]));
            p2.setX(Double.parseDouble(s[2]));
            p2.setY(Double.parseDouble(s[3]));
            p3.setX(Double.parseDouble(s[4]));
            p3.setY(Double.parseDouble(s[5]));
            p4.setX(Double.parseDouble(s[6]));
            p4.setY(Double.parseDouble(s[7]));
            raindrops.get(0).setX(Double.parseDouble(s[8]));
            raindrops.get(0).setY(Double.parseDouble(s[9]));
            raindrops.get(1).setX(Double.parseDouble(s[10]));
            raindrops.get(1).setY(Double.parseDouble(s[11]));
            raindrops.get(2).setX(Double.parseDouble(s[12]));
            raindrops.get(2).setY(Double.parseDouble(s[13]));
            raindrops.get(3).setX(Double.parseDouble(s[14]));
            raindrops.get(3).setY(Double.parseDouble(s[15]));
            raindrops.get(4).setX(Double.parseDouble(s[16]));
            raindrops.get(4).setY(Double.parseDouble(s[17]));
            //System.out.println(fromServer); // DEBUG
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void request_move(String str) {
        out.println(str);
    }

    @Override
    public void run() {
        while (true) {
            get_update();
        }
    }

}





