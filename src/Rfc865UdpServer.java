package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Rfc865UdpServer {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(30017);
        } catch (SocketException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                String receiveMessage = "Hello, World!";
                byte[] receiveData = receiveMessage.getBytes();
                DatagramPacket request = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(request);
                //
                // 3. Send UDP reply to client
                //
                byte[] buf = new byte[512];
                buf = "Hello, World!".getBytes();
                DatagramPacket reply = new DatagramPacket(buf, buf.length);
                reply.setAddress(request.getAddress());
                reply.setPort(request.getPort());
                socket.send(reply);

                // Close the socket
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
