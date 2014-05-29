package nio_tcp_file;


import com.sun.java.swing.plaf.windows.WindowsRadioButtonMenuItemUI;
import config.Config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.*;

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
            final String address = InetAddress.getLocalHost().toString();
            socketChannel.connect(new InetSocketAddress(address, 50000));
            long endTime;
            long starttime = System.currentTimeMillis();

            System.out.println("Getting File Length");
            //Filename length
            ByteBuffer fileNameSize = ByteBuffer.allocateDirect(4);// ein int sind 32bit = 4Byte
            IntBuffer fileNameSizeINTBFF = fileNameSize.asIntBuffer();
            socketChannel.read(fileNameSize);

            final int filenameLengthINT = fileNameSizeINTBFF.get();

            System.out.println("Getting File Name");
            //Filename
            ByteBuffer fileName = ByteBuffer.allocateDirect(filenameLengthINT * 2);//ein char sind 16bit = 2Byte
            CharBuffer fileNameCHAR = fileName.asCharBuffer();
            socketChannel.read(fileName);

            final String filenameSTR = fileNameCHAR.toString();

            System.out.println("Getting BufferSize");
            //Buffersize
            ByteBuffer bufferSize = ByteBuffer.allocateDirect(8);
            LongBuffer bufferSizeL = bufferSize.asLongBuffer();
            socketChannel.read(bufferSize);

            final long sizeOfBufferL = bufferSizeL.get();

            System.out.println("Getting runs");
            //Buffersize
            ByteBuffer runsBuffer = ByteBuffer.allocateDirect(8);
            LongBuffer runsL = runsBuffer.asLongBuffer();
            socketChannel.read(runsBuffer);

            final long runs = runsL.get();

            System.out.println("Getting extrabuffer");
            //Buffersize
            ByteBuffer extraBuffer = ByteBuffer.allocateDirect(8);
            LongBuffer extraBufferL = extraBuffer.asLongBuffer();
            socketChannel.read(extraBuffer);

            final long extraBufferSize = extraBufferL.get();

            System.out.println("Getting File");
            //File
            ByteBuffer file = ByteBuffer.allocateDirect((int)sizeOfBufferL);
            //TODO: REST abändern
            while (file.hasRemaining()) {
                System.out.println((100.f/file.limit() * file.position()) +"%");
                socketChannel.read(file);
            }
            System.out.println(100 +"%");

            final String uri = "C:\\Users\\Akatsuki\\Desktop\\" + filenameSTR;
            Path path = Paths.get(uri);

            System.out.println("Writing File");
            //Write File to Destionation
            FileChannel fileChannel = FileChannel.open(path, CREATE_NEW, WRITE);
            file.flip();
            while(file.hasRemaining())
                fileChannel.write(file);


            endTime = System.currentTimeMillis();
            System.out.println("elapsed Time: in milli "+(endTime-starttime));

            System.out.println(filenameSTR);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {

        new Thread(new Receiver()).start();
    }
}
