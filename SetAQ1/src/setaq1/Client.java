package setaq1;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5000);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgin = "", msgout;

            while (!msgin.equals("exit")) {
                msgout = br.readLine();
                dos.writeUTF(msgout);
                dos.flush();

                msgin = dis.readUTF();
                System.out.println("Server: " + msgin);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}