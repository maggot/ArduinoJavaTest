package RaspberryPi;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteControllListener {
    private InputStream in;
    SmartCarComm sc;


    public RemoteControllListener(int port, SmartCarComm sc) throws IOException {
        ServerSocket listener = new ServerSocket(port);
        this.sc = sc;

        Socket socket = listener.accept();
        while (!socket.isClosed()) {
            try {
                in = socket.getInputStream();
                String buffer = "";
                //if there's any input do the following
                while (in.available() > 0) {
                    buffer += (char)in.read();
                }

                if(buffer.length() > 0)
                {
                    char first = buffer.charAt(0);
                    if (first == 's') {
                        sc.setSpeed(Integer.parseInt(buffer.substring(1,buffer.indexOf('/'))));
                    } else if (first == 'a') {
                        sc.setAngle(Integer.parseInt(buffer.substring(1,buffer.indexOf('/'))));
                    } else if (first == 'r') {
                        sc.setRotate(Integer.parseInt(buffer.substring(1,buffer.indexOf('/'))));
                    }else if (first == 'c') {
                        if(buffer.charAt(1) == 's') {
                            listener.close();
                            socket.close();
                            this.sc.close();
                        }
                    }
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
