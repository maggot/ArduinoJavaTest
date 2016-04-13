package RaspberryPi;

/**
 * Created by Raphael on 07/04/2016 for ArduinoJavaTest.
 */
public class Main {

    public static void main(String[] args) {
        try {
            DiscoveryBroadcaster d = new DiscoveryBroadcaster();
            Thread t1 = new Thread(d);
            t1.start();

            SmartCarComm sc = new SmartCarComm();
            new RemoteControllListener(1234, sc);
            System.out.println("Listening");

        } catch (Exception e) {
            System.out.println("Failed " + e.getMessage());
        }

        Thread t2 = new Thread() {
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
        t2.start();
        System.out.println("Started");

    }
}
