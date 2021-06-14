package pl.patrykp.testserver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleServer {

    JFrame o;
    ServerSocket socket;

    JTextField masterServer;
    JTextField accessToken;
    JTextField address;
    JTextField port;
    JTextField currentPlayers;
    JTextField maxPlayers;
    JTextField fakePing;

    JLabel state;

    ArrayList<Client> clients;
    Thread currentThread;


    public SimpleServer(){
        o = new JFrame();
        o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container root = o.getContentPane();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));


        masterServer = new JTextField("http://localhost:8080/api/servers/online");
        accessToken = new JTextField("");
        address = new JTextField("localhost");
        port = new JTextField("" + (int)Math.floor(Math.random() * 64510+1025));
        currentPlayers = new JTextField("0");
        maxPlayers = new JTextField("255");
        fakePing = new JTextField("" + (int)Math.floor(Math.random() * 200));

        state = new JLabel("State: Off");

        JButton start = new JButton("start");
        JButton stop = new JButton("stop");

        start.addActionListener((e)->start());
        stop.addActionListener((e)->stop());

        clients = new ArrayList<Client>();

        root.add(new JLabel("Master server: "));
        root.add(masterServer);

        root.add(new JLabel("access token: "));
        root.add(accessToken);

        root.add(new JLabel("address: "));
        root.add(address);

        root.add(new JLabel("port: "));
        root.add(port);

        root.add(new JLabel("current Players: "));
        root.add(currentPlayers);

        root.add(new JLabel("max players: "));
        root.add(maxPlayers);

        root.add(new JLabel("fake ping: "));
        root.add(fakePing);

        root.add(state);

        root.add(start);

        root.add(stop);

        o.pack();
        o.setLocationRelativeTo(null);
        o.setVisible(true);
    }

    public void removeClient(Client client){
        clients.remove(client);
    }

    public int getMaxPlayers(){
        return Integer.parseInt(maxPlayers.getText());
    }

    public int getCurrPlayers(){
        return Integer.parseInt(currentPlayers.getText());
    }

    public int getFakePing(){
        return Integer.parseInt(fakePing.getText());
    }

    public int getPort() {
        return Integer.parseInt(port.getText());
    }

    void start(){
        try {
            if(socket != null) {
                return;
            }

            socket = new ServerSocket(getPort());
            currentThread = new Thread(()->{
                try {
                    while (socket != null) {
                        Socket clientSocket = socket.accept();
                        clients.add(new Client(this, clientSocket));
                    }
                }catch (IOException er){
                    er.printStackTrace();
                    stop();
                }
            });
            currentThread.start();

            String payloadForMaster = "{" +
                    "\"accessToken\": \"{{ACCESS_TOKEN}}\"," +
                    "\"maxPlayers\": {{MAX_PLAYERS}}," +
                    "\"address\": \"{{ADDRESS}}\"," +
                    "\"port\": {{PORT}}" +
                    "}";

            HashMap<String, Object> keys = new HashMap<>();
            keys.put("ACCESS_TOKEN", accessToken.getText());
            keys.put("MAX_PLAYERS", getMaxPlayers());
            keys.put("ADDRESS", address.getText());
            keys.put("PORT", getPort());


            String[] keysArr = keys.keySet().toArray(new String[0]);
            for(String key : keysArr){
                payloadForMaster = payloadForMaster.replace("{{"+key+"}}", keys.get(key).toString());
            }

            byte[] payloadBytes = payloadForMaster.getBytes(StandardCharsets.UTF_8);

            URL url = new URL(masterServer.getText());
            HttpURLConnection maserConnection = (HttpURLConnection) url.openConnection();

            maserConnection.setDoOutput(true);
            maserConnection.setRequestMethod("POST");
            maserConnection.setRequestProperty("Content-Type", "application/json");
            maserConnection.setRequestProperty("Content-Length", ""+  payloadBytes.length);
            maserConnection.setUseCaches( false );
            maserConnection.setReadTimeout(1000);
            maserConnection.setConnectTimeout(5000);
            System.out.println("Master server request:");
            System.out.println("#######################");
            System.out.println(payloadForMaster);
            System.out.println("#######################");
            maserConnection.getOutputStream().write(payloadBytes);


            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(maserConnection.getInputStream()));
            }
            catch (IOException er){
                br = new BufferedReader(new InputStreamReader(maserConnection.getErrorStream()));
            }
            System.out.println("Master server response:");
            System.out.println("#######################");
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("#######################");

            state.setText("State: ON " + socket.getLocalPort());

        }catch (IOException er){
            er.printStackTrace();
        }
    }

    void stop() {
        if (socket == null)
            return;
        try {
            socket.close();
        }catch (IOException er){
            er.printStackTrace();
        }

        state.setText("State: OFF");
        socket = null;
        currentThread.stop();
        currentThread = null;
    }


    public static void main(String[] args) {
        new SimpleServer();
    }
}
