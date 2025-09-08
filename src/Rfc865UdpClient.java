package src;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //

        InetAddress serverAddr;
        try {
            serverAddr = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            socket.connect(serverAddr, 17);
        } catch (SocketException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        try {
            //
            // 2. Send UDP request to server
            //
            String sendMessage = "Hello, World!";
            byte[] sendData = sendMessage.getBytes();
            DatagramPacket request = new DatagramPacket(sendData, sendData.length);
            socket.send(request);

            //
            // 3. Receive UDP reply from server
            //
            byte[] buf = new byte[512];
            DatagramPacket reply = new DatagramPacket(buf, buf.length);
            socket.receive(reply);
            // Convert the buffer to a string
            String message = new String(reply.getData());
            System.out.println("Result: " + message);
        } catch (IOException e) {
        }
    }
}
