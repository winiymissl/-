import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * @Description
 * @Author winiymissl
 * @Date 2023/6/28
 */
public class Client1 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        InetAddress inet = InetAddress.getByName("192.168.2.132s");
        int port = 8989;
        Socket s = new Socket(inet, port);
        ClientReceive cr = new ClientReceive(s);
        cr.start();
        OutputStream os = s.getOutputStream();
        while (true) {
            String temp = in.nextLine();
            os.write(temp.getBytes());
            if (temp.equals("bye")) {
                break;
            }
        }
        os.close();
        s.close();
    }
    static class ClientReceive extends Thread {
        private Socket socket;
        public ClientReceive(Socket socket) {
            super();
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                byte[] arr = new byte[1024];
                int len;
                while ((len = is.read(arr)) != -1) {
                    System.out.println("other : " + new String(arr, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}