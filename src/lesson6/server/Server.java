package lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int PORT = 8189;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;
        Scanner inFromTerminal;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен, ожидаем подключения...");

            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            inFromTerminal = new Scanner(System.in);

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msgFromClient = in.readUTF();

                            if (msgFromClient.equals("/end")) {
                                System.out.println("Клиент отключился");
                                break;
                            }
                            System.out.println("Сообщение от клиента: " + msgFromClient);
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
                            if (msgFromTerminal.equals("/end")) {
                                break;
                            }
                            out.writeUTF(msgFromTerminal);
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
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
