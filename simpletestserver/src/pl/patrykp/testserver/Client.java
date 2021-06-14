package pl.patrykp.testserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {

    Socket socket;
    SimpleServer parent;

    Thread t;

    public Client(SimpleServer parent, Socket socket){
        this.socket = socket;
        this.parent = parent;

        t = new Thread(new InputReader());
        t.start();
    }

    public void forceStop(){
        t.stop();
        parent.removeClient(this);
    }

    class InputReader implements Runnable {

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                final int MSG_LEN = 2;
                byte[] msg = new byte[MSG_LEN];
                int len = 0;
                while(len < MSG_LEN)
                     len += is.read(msg, len, MSG_LEN - len);

                try {
                    Thread.sleep(parent.getFakePing());
                }catch (InterruptedException er){}

                if(msg[0] == 0 && msg[1] == 1){

                    ByteBuffer byteBuffer = ByteBuffer.allocate(9);
                    byteBuffer.put((byte) 0b1);
                    byteBuffer.putInt(parent.getMaxPlayers());
                    byteBuffer.putInt(parent.getCurrPlayers());

                    os.write(byteBuffer.array());
                    System.out.println("CLIENT: " + socket.getPort() + " send payload");
                }
                else {
                    System.out.println("CLIENT: " + socket.getPort() + " wrong payload");
                }
            }
            catch (IOException er){
                er.printStackTrace();
            }
            try {
                socket.close();
            }catch (IOException er){}
            parent.removeClient(Client.this);
        }
    }
}
