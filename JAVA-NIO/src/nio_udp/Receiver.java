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
 * Time: 17:02
 */
public class Receiver {
    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(50000));
            ByteBuffer bb = ByteBuffer.allocateDirect(4 * 7);
            FloatBuffer fb = bb.asFloatBuffer();
            long endTime;
            long starttime = System.currentTimeMillis();
            for (int i = 0; i < Config.test; i++) {
                bb.clear();
                channel.receive(bb);
//                System.out.println(fb.get(0));
                bb.flip();
            }
            endTime = System.currentTimeMillis();
            System.out.println("elapsed Time: in milli "+(endTime-starttime));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
