import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpClient {
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    public UdpClient( String serverIp, int serverPort) throws SocketException {
        socket = new DatagramSocket();
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("->");
            String request = scanner.nextLine();
            if (request.equals("exit")){
                break;
            }
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),
                    request.getBytes().length, InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String  response = new String(responsePacket.getData(),
                    0,responsePacket.getLength()).trim();
            System.out.println(response);


        }



    }


    public static void main(String[] args) throws IOException {
        UdpClient udpClient = new UdpClient("127.0.0.1",9090);
        udpClient.start();
    }


}
