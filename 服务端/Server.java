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
        //创建ServerSocket；
        int port = 8989;
        ServerSocket ss = new ServerSocket(port);
        //每一个连接到服务端的客户端都有一个套接字
        while (true) {
            Socket s = ss.accept();
            online.add(s);
            //读取内容
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
                    //将收到的信息直接输出到控制台
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
//创建之后可以不关闭
                    os.write(message.getBytes());
                }
            }
        }
    }
}