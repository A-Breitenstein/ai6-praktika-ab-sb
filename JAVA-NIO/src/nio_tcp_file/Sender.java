package nio_tcp_file;


import config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.READ;

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
            final String uri = "E:\\#Alex\\Eigene Bilder\\daserwartetdichALEX.JPG";
            final Path path = Paths.get(uri);
            final int filenameLength = path.getFileName().toString().toCharArray().length;

            //File
            FileChannel fileChannel = FileChannel.open(path, READ);
            MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            ByteBuffer file = ByteBuffer.allocateDirect((int)fileChannel.size());
            file.put(mbb);


            ByteBuffer filenameSize = ByteBuffer.allocateDirect(4);// ein int sind 32bit = 4Byte
            IntBuffer filenameSizeINTBFF = filenameSize.asIntBuffer();
            filenameSizeINTBFF.put(filenameLength);

            //FileName
            ByteBuffer filename = ByteBuffer.allocateDirect(filenameLength*2);//ein char sind 16bit = 2Byte
            CharBuffer filenameCHAR = filename.asCharBuffer();
            filenameCHAR.put(path.getFileName().toString().toCharArray());

            //FileSize <<<---- int nur max 2GB Datei, Long als String oder byteArray schicken
            ByteBuffer fileSize = ByteBuffer.allocateDirect(4);
            IntBuffer fileSizeINT = fileSize.asIntBuffer();
            fileSizeINT.put((int)path.toFile().length());

            //ServerSocket
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(50000));
            SocketChannel channel = ssc.accept();

            //Send FilenameSize
            filenameSizeINTBFF.flip();
            channel.write(filenameSize);
            filenameSize.clear();

            //Send Filename
            filenameCHAR.flip();
            channel.write(filename);
            filename.clear();

            //Send FileSize <<<---- int nur max 2GB Datei, Long als String oder byteArray schicken
            fileSizeINT.flip();
            channel.write(fileSize);
            fileSize.clear();

            //Send file
            file.flip();
            while(file.hasRemaining())
                channel.write(file);
            file.clear();

        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {

         new Thread(new Sender()).start();
    }
}
