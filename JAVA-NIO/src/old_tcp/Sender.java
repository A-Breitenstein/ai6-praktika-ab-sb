package old_tcp;


import config.Config;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class Sender implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
            Socket client = serverSocket.accept();
            ObjectOutputStream objOS = new ObjectOutputStream(client.getOutputStream());

            for (int i = 1; i < Config.test; i++) {
                objOS.writeObject(new float[]{i, 1, 2, 3, 4, 5, 6});
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static void main(String[] args) {

        new Thread(new Sender()).start();
    }

}
