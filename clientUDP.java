import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clientUDP {
    public static void main(String argv[]) throws IOException {
        String sentence_to_server;
        String sentence_from_server;
        //Tạo socket phía client
        DatagramSocket clientSocket = new DatagramSocket();
        //Tạo địa chỉ IP address bởi tên localhost
        InetAddress IPAddress = InetAddress.getByName("localhost");
        System.out.println("Network Application using UDP protocol");

        while (true) {
            //Tạo dữ liệu cho packet nhận và packet gửi
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.print("Input from client: ");
            //lấy chuỗi kí tự nhận được từ bàn phím
            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));
            sentence_to_server = inFromUser.readLine();

            //tạo dữ liệu gửi với dữ liệu đã nhập từ bàn phím
            sendData = sentence_to_server.getBytes();

            //tạo packet truyền đi
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, 1000);

            //gửi packet đến server bằng phương thức send
            clientSocket.send(sendPacket);

            //tạo packet nhận từ server
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            //nhận packet từ server đưa vào receivePacket
            clientSocket.receive(receivePacket);

            //chuyển receivePacket thành String và đưa kết quả ra màn hình
            receiveData = receivePacket.getData();
            sentence_from_server = new String(receiveData);

            System.out.println("FROM SERVER: " + sentence_from_server);

            break;
        }
        clientSocket.close();
    }
}
