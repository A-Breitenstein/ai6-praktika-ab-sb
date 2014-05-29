package nio_tcp;


import config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sven
 * Date: 27.05.14
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class Sender implements Runnable{

    @Override
    public void run() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(50000));
            SocketChannel channel = ssc.accept();

            float[] floaty = new float[]{123, 1, 2, 3, 4, 5, 6};
            byte[] bity = new byte[floaty.length * 4];
            ByteBuffer byteBuffer = ByteBuffer.wrap(bity);
            FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
            floatBuffer.put(floaty);

            byte[] sending = new byte[29];
            sending[0] = 7;
            System.arraycopy(bity, 0, sending, 1, sending.length - 1);
            ByteBuffer bb = ByteBuffer.allocateDirect(29);
            bb.put(sending);

            for (int i = 0; i < Config.test; i++) {
                bb.flip();
                channel.write(bb);
                bb.clear();
            }
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {

         new Thread(new Sender()).start();
    }
}
