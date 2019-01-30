package ru.otus.sua.L16.sts;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.otus.sua.L16.sts.abstractions.Blocks;
import ru.otus.sua.L16.sts.abstractions.Msg;
import ru.otus.sua.L16.sts.abstractions.SocketWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class SocketWorkerImpl implements SocketWorker {

    private static final int WORKERS_COUNT = 2;

    private final BlockingQueue<Msg> output = new LinkedBlockingQueue<>();
    private final BlockingQueue<Msg> input = new LinkedBlockingQueue<>();
    private final ExecutorService executor;
    private final Socket socket;

    SocketWorkerImpl(Socket socket) {
        this.socket = socket;
        this.executor = Executors.newFixedThreadPool(WORKERS_COUNT);
        executor.execute(this::sendMessage);
        executor.execute(this::receiveMessage);
        log.info("Worker for socket \'{}\' started.", socket);
    }

    @Override
    public void send(Msg msg) {
        if (!output.offer(msg)) {
            log.warn("Send Queue is full. Message \'{}\' was lost.");
        }
    }

    @Override
    public Msg poll() {
        return input.poll();
    }

    @Override
    public Msg take() throws InterruptedException {
        return input.take();
    }

    @Override
    public void close() {
        executor.shutdownNow();
        try {
            socket.close();
        } catch (IOException e) {
            log.error("err on close socket: \'{}\'",e.getMessage());
        }
        log.info("Socket worker was shutdown.");
    }

    @Blocks
    private void sendMessage() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            while (socket.isConnected()) {
                Msg msg = output.take(); //blocks
                String json = new Gson().toJson(msg);
                out.println(json);
                out.println();//line with json + an empty line
            }
        } catch (InterruptedException | IOException e) {
            log.error("sendMessage(): \'{}\'", e.getMessage());
        }
    }

    @Blocks
    private void receiveMessage() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = in.readLine()) != null) { //blocks
                stringBuilder.append(inputLine);
                if (inputLine.isEmpty()) { //empty line is the end of the message
                    String json = stringBuilder.toString();
                    Msg msg = getMsgFromJSON(json);
                    input.add(msg);
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException | ParseException e) {
            log.error("receiveMessage(): \'{}\'", e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Msg getMsgFromJSON(String json) throws ParseException, ClassNotFoundException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
        String className = (String) jsonObject.get(Msg.CLASS_NAME_VARIABLE);
        Class<?> msgClass = Class.forName(className);
        return (Msg) new Gson().fromJson(json, msgClass);
    }
}
