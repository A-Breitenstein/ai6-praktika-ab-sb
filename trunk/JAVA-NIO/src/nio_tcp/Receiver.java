package nio_tcp;


import config.Config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.14
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class Receiver  implements Runnable{
    @Override
    public void run() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 50000));

            ByteBuffer bb = ByteBuffer.allocateDirect(4 * 7 + 1);

            byte[] bbArray = new byte[29];
            long endTime;
            long starttime = System.currentTimeMillis();

            for (int i = 0; i < Config.test; i++) {
                socketChannel.read(bb);
                bb.clear();
                bb.get(bbArray,0,bbArray.length);
            }


            byte[] bitty = new byte[28];

            System.arraycopy(bbArray, 1, bitty, 0, 28);

            float[] fiffy = new float[7];
            ByteBuffer.wrap(bitty).asFloatBuffer().get(fiffy);

//            System.out.println("0x" + Integer.toHexString((int) bb.get(0)));
//            for (float v : fiffy) {
//                System.out.println(v);
//            }
            endTime = System.currentTimeMillis();
            System.out.println("elapsed Time: in milli "+(endTime-starttime));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {

        new Thread(new Receiver()).start();
    }
}
