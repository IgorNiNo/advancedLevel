package lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    private List<ClientHandler> clients;
    private AuthService authService;

    private int PORT = 8189;
    ServerSocket server = null;
    Socket socket = null;

    public Server() {
        clients = new Vector<>();
        authService = new SimpleAuthService();

        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
//                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("%s : %s", sender.getNickname(), msg);
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void privateMsg(ClientHandler sender, String nickDestination, String msg) {
        for (ClientHandler c : clients) {
            if (c.getNickname().equals(nickDestination)) {
                c.sendMsg("ЛС от " + sender.getNickname() + ": " + msg);
//                return;   //не выходим их метода, т.к. может быть запущено несколько клиентов с одинаковым ником
            }
            if (c.getNickname().equals(sender.getNickname())) {
                c.sendMsg("ЛС для " + nickDestination + " от меня: " + msg);
//                return;   //не выходим их метода, т.к. может быть запущено несколько клиентов с одинаковым ником
            }
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
