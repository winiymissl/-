import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
    static ArrayList<Socket> online;

    public static void main(String[] args) throws IOException {
        online = new ArrayList<>();
        //����ServerSocket��
        int port = 8989;
        ServerSocket ss = new ServerSocket(port);
        //ÿһ�����ӵ�����˵Ŀͻ��˶���һ���׽���
        while (true) {
            Socket s = ss.accept();
            online.add(s);
            //��ȡ����
            Receive r = new Receive(s);
            r.start();
        }

    }

    static class Receive extends Thread {
        private Socket socket;

        //
        public Receive(Socket socket) {
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
                    System.out.println(this.getId() + " : " + new String(arr, 0, len));
                    send(new String(arr, 0, len));
                    //���յ�����Ϣֱ�����������̨
                }
                online.remove(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

          public void send(String message) throws IOException {
            Iterator<Socket> it = online.iterator();
            while (it.hasNext()) {
                Socket s = it.next();
                if (!s.equals(socket)) {
                    OutputStream os = s.getOutputStream();
//����֮����Բ��ر�
                    os.write(message.getBytes());
                }
            }
        }
    }
}