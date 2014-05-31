package nio_tcp_file;


import com.sun.java.swing.plaf.windows.WindowsRadioButtonMenuItemUI;
import config.Config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketOptions;
import java.nio.*;
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
            final String address = "";
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 50000));
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


            //FILE RECEIVING
            System.out.println("Getting File");
            final String uri = "C:\\Users\\Akatsuki\\Desktop\\" + filenameSTR;
            Path path = Paths.get(uri);

            ByteBuffer file = ByteBuffer.allocateDirect((int) sizeOfBufferL);
            FileChannel fileChannel = FileChannel.open(path, CREATE_NEW, WRITE, READ);
            MappedByteBuffer mbb;
            int currentPosition = 0;

            for (int i = 0; i < runs; i++) {
                System.out.println((100.f/file.limit() * file.position()) +"%");

                mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE, currentPosition, sizeOfBufferL);
                while(file.hasRemaining())
                    socketChannel.read(file);
                file.flip();
                mbb.put(file); //nochmal file flip?????
                mbb.force();
                file.clear();
                currentPosition += sizeOfBufferL;
            }

            if (extraBufferSize > 0) {
                mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE, currentPosition, extraBufferSize);
                file.flip();
                while(file.hasRemaining())
                    socketChannel.read(file);
                file.flip();
                mbb.put(file); //nochmal file flip?????
                mbb.force();
                file.clear();
            }
            System.out.println(100 +"%");
            System.out.println("File written");
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
