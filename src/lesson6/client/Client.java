package lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;

    public static void main(String[] args) throws IOException {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        Scanner inFromTerminal;

        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println("Клиент запущен");
            inFromTerminal = new Scanner(System.in);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            try {
                                String msgFromServer = in.readUTF();
                                if (msgFromServer.equals("/end")) {
                                    System.out.println("Сервер отключился");
                                }
                                System.out.println("Сообщение от сервера: " + msgFromServer);
                            } catch (IOException e) {
                                System.out.println("Сеанс связи закончен");
                                inFromTerminal.close();
                                in.close();
                                out.close();
                                socket.close();
                                break;
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msgFromTerminal = inFromTerminal.nextLine();
                            out.writeUTF(msgFromTerminal);
                            if (msgFromTerminal.equals("/end")) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
