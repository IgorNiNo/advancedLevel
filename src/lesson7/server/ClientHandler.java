package lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String nickname;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //цикл аутентификации
                    while (true) {
                        String str = in.readUTF();

                        if (str.startsWith("/auth ")) {
                            String[] token = str.split("\\s");
                            String newNick = server
                                    .getAuthService()
                                    .getNicknameByLoginAndPassword(token[1], token[2]);

                            if (newNick != null) {
                                nickname = newNick;
                                sendMsg("/authok1 " + nickname);    //почему первый раз не печатает?
                                sendMsg("/authok2 " + nickname);
                                server.subscribe(this);
                                System.out.println("Клиент " + nickname + " подключился");
                                break;
                            } else {
                                sendMsg("Неверный логин / пароль");
                            }
                        }
                    }

                    //цикл работы
                    while (true) {
                        String str = in.readUTF();

                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }

                        if (str.startsWith("/w ")) {
                            String[] privMsg = str.split(" ", 3);
                            server.privateMsg(this, privMsg[1], privMsg[2]);
                        } else {
                            server.broadcastMsg(this, str);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Клиент " + nickname + " отключился");
                    server.unsubscribe(this);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }
}
