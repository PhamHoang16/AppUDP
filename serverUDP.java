import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
public class serverUDP {

    public static void main(String args[]) throws Exception {
        //Tạo socket phía server với số hiệu cổng 1000
        DatagramSocket serverSocket = new DatagramSocket(1000);
        String sentence_from_client;
        String sentence_to_client;
        System.out.println("Server is running");
        while(true) {
            //tạo biến receiveData để chứa dữ liệu từ packet đến
            byte[] receiveData = new byte[1024];
            //tạo sendData để chứa dữ liệu gửi vào packet gửi đi
            byte[] sendData  = new byte[2048];
//
            //tạo biến receivePacket để nhận packet từ client
            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);
            //nhận packet qua phương thức receive()
            serverSocket.receive(receivePacket);
            //Chuyển dữ liệu vừa nhận về dạng String
            sentence_from_client = new String(receivePacket.getData());

            //Lấy địa chỉ IP của client
            InetAddress IPAddress = receivePacket.getAddress();
            //Lấy số hiệu cổng client
            int port = receivePacket.getPort();
            //Xử lý chuỗi vừa nhận được thành Uppercase
            sentence_to_client = sentence_from_client.toUpperCase();

            //tạo gói tin để gửi đi client
            sendData = sentence_to_client.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            //Gửi gói tin đi
            serverSocket.send(sendPacket);
        }
    }
}
