package nio_tcp_file;


import config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.AsynchronousSocketChannel;
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

    final private long BUFFER_SIZE = 4194304;//4 MB

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

            long buffersize = 0, runs = 0, extrabuffer = 0;
            if (fileChannel.size() <= BUFFER_SIZE) {
                buffersize = fileChannel.size();
            } else {
                buffersize = BUFFER_SIZE;
                runs = fileChannel.size() / BUFFER_SIZE;
                extrabuffer = runs * BUFFER_SIZE - fileChannel.size();
            }


            ByteBuffer filenameSize = ByteBuffer.allocateDirect(4);// ein int sind 32bit = 4Byte
            IntBuffer filenameSizeINTBFF = filenameSize.asIntBuffer();
            filenameSizeINTBFF.put(filenameLength);

            //FileName
            ByteBuffer filename = ByteBuffer.allocateDirect(filenameLength*2);//ein char sind 16bit = 2Byte
            CharBuffer filenameCHAR = filename.asCharBuffer();
            filenameCHAR.put(path.getFileName().toString().toCharArray());

            //BufferSize
            ByteBuffer bufferSize = ByteBuffer.allocateDirect(8); // ein long sind 64bit = 8 byte
            LongBuffer bufferSizeL = bufferSize.asLongBuffer();
            bufferSizeL.put(buffersize);
            //runs
            ByteBuffer runsSize = ByteBuffer.allocateDirect(8); // ein long sind 64bit = 8 byte
            LongBuffer runsSizeL = runsSize.asLongBuffer();
            runsSizeL.put(runs);
            //extrabuffer
            ByteBuffer extraBufferSize = ByteBuffer.allocateDirect(8); // ein long sind 64bit = 8 byte
            LongBuffer extraBufferSizeL = extraBufferSize.asLongBuffer();
            extraBufferSizeL.put(extrabuffer);

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

                //Send BufferSize
                bufferSize.flip();
                channel.write(bufferSize);
                bufferSize.clear();

                //Send runs
                runsSize.flip();
                channel.write(runsSize);
                runsSize.clear();

                //Send extraBuffer
                extraBufferSize.flip();
                channel.write(extraBufferSize);
                extraBufferSize.clear();

            //TODO:rest abändern
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
