package nio_udp;


import config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.2014
 * Time: 16:53
 */
public class Sender {
    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            ByteBuffer bb = ByteBuffer.allocateDirect(4 * 7);
            FloatBuffer fb = bb.asFloatBuffer();
            InetSocketAddress dest = new InetSocketAddress("localhost", 50000);
            long endTime;
            long starttime = System.currentTimeMillis();
            for (int i = 0; i < Config.test; i++) {
                bb.clear();
                fb.put(new float[]{i, 2, 3, 4, 5, 6, 7});
                fb.flip();
                channel.send(bb, dest);
            }
            endTime = System.currentTimeMillis();
            System.out.println("elapsed Time: in milli "+(endTime-starttime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
