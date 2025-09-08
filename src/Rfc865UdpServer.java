package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Rfc865UdpServer {

    private final static String[] responses = {
            "Hello, World!",
            "How are you?",
            "I'm fine, thank you!",
            "What's your name?",
            "My name is John Doe.",
    };

    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(17);
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
                buf = responses[(int) (Math.random() * responses.length)].getBytes();
                DatagramPacket reply = new DatagramPacket(buf, buf.length);
                reply.setAddress(request.getAddress());
                reply.setPort(request.getPort());
                socket.send(reply);
            } catch (IOException e) {
            }
        }
    }
}
