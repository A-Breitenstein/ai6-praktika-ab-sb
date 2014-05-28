package old_tcp;


import config.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class Receiver implements Runnable {

    @Override
    public void run() {
        try {
            Socket server = new Socket(InetAddress.getLocalHost(), 50000);
            ObjectInputStream objIs = new ObjectInputStream(server.getInputStream());


            float[] float_array;
            long endTime;
            long starttime = System.currentTimeMillis();

            for (int i = 1; i < Config.test; i++) {
                float_array = (float[]) objIs.readObject();
//                System.out.println(float_array[0]);
            }
            endTime = System.currentTimeMillis();
            System.out.println("elapsed Time: in milli " + (endTime - starttime));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static void main(String[] args) {

        new Thread(new Receiver()).start();
    }

}
