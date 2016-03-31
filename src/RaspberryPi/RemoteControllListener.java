package RaspberryPi;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteControllListener {
    private InputStream in;

    public static void main(String[] args) {
        try {
            System.out.println("Listening");
            RemoteControllListener rcl = new RemoteControllListener(1234);//change this port number
        } catch (Exception e) {
            System.out.println("Failed " + e.getMessage());
        }
        SerialConnect main = new SerialConnect();
        Thread t = new Thread() {
            public void run() {
                // the following line will keep this app alive for 1000 seconds,
                // waiting for events to occur and responding to them (printing
                // incoming messages to console).
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        t.start();
        System.out.println("Started");

    }

    public RemoteControllListener(int port) throws IOException {
        ServerSocket listener = new ServerSocket(port);
        SmartCarComm sc = new SmartCarComm();

        Socket socket = listener.accept();
        while (!socket.isClosed()) {
            try {
                //System.out.println("in the true loop");
                in = socket.getInputStream();
                String buffer = "";
                //if there's any input do the following
                while (in.available() > 0) {
                    buffer = buffer + (char)in.read();
                    //TODO
                    // Remember to change the method inside to make it work
                }

                if(buffer.length() > 0)
                {
                    char first;
                    first = buffer.charAt(0);
                    if (first == 's') {
                        sc.setSpeed(Integer.parseInt(buffer.substring(1)));
                    } else if (first == 'a') {
                        sc.setAngle(Integer.parseInt(buffer.substring(1)));
                    } else if (first == 'r') {
                        sc.setRotate(Integer.parseInt(buffer.substring(1)));
                    }
                }

            }catch(Exception e){}
        }

    }
}
