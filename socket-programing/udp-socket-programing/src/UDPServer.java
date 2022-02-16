import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9876);
        DatagramSocket serverSocket = new DatagramSocket(socketAddress);
        System.out.println(serverSocket.getLocalAddress() + ":" + serverSocket.getLocalPort());
        byte[] recvData = new byte[1024];
        byte[] sendData = null;
        while (true) {
            DatagramPacket recvPacket = new DatagramPacket(recvData, recvData.length);
            serverSocket.receive(recvPacket);
            String sentence = new String(recvPacket.getData(), recvPacket.getOffset(), recvPacket.getLength(), StandardCharsets.UTF_8);
            InetAddress IPAddress = recvPacket.getAddress();
            int port = recvPacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
