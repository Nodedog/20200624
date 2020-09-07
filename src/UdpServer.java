import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
    private DatagramSocket socket = null;

    public UdpServer( int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        while (true){
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0,requestPacket.getLength()).trim();
            String response =process(request);
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),
                    response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(responsePacket);

        }


    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UdpServer udpServer = new UdpServer(9090);
        udpServer.start();
    }

}
