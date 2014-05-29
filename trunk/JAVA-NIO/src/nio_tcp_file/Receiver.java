package nio_tcp_file;


import com.sun.java.swing.plaf.windows.WindowsRadioButtonMenuItemUI;
import config.Config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            socketChannel.connect(new InetSocketAddress("192.168.1.18", 50000));
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

            System.out.println("Getting File Size");
            //FileSize <<<---- int nur max 2GB Datei, Long als String oder byteArray schicken oder wie in ad
            ByteBuffer fileSize = ByteBuffer.allocateDirect(4);// ein int sind 32bit = 4Byte
            IntBuffer fileSizeINT = fileSize.asIntBuffer();
            socketChannel.read(fileSize);

            final int sizeOfFileINT = fileSizeINT.get();

            System.out.println("Getting File");
            //File
            ByteBuffer file = ByteBuffer.allocateDirect(sizeOfFileINT);
            while (file.hasRemaining()) {
                System.out.println((file.limit() / 100 * file.position()) +"%");
                socketChannel.read(file);
            }

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
