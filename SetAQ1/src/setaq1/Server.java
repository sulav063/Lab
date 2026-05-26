package setaq1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin = "", msgout;

            while (!msgin.equals("exit")) {
                msgin = dis.readUTF();
                System.out.println("Client: " + msgin);

                msgout = br.readLine();
                dos.writeUTF(msgout);
                dos.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}