package ru.otus.sua.L16;

import ru.otus.sua.L16.sts.CallbackHandler;
import ru.otus.sua.L16.sts.SocketTransferServiceClient;
import ru.otus.sua.L16.sts.SocketTransferServiceServer;
import ru.otus.sua.L16.sts.abstractions.Pollable;
import ru.otus.sua.L16.sts.abstractions.SocketTransferService;
import ru.otus.sua.L16.sts.entities.PingMsg;

import java.io.IOException;

public class TestRun {

    public static void main(String[] args) throws InterruptedException, IOException {

        SocketTransferService stsSrv =
                new SocketTransferServiceServer(11121, "STS-SERVER");
        pause();

        SocketTransferService stsClnt =
                new SocketTransferServiceClient(11121, "localhost", "STS-CLIENT");
        pause();

        stsSrv.send(new PingMsg("FromServer"));
        pause();
        System.out.println("check by client: " + stsClnt.poll());
        System.out.println("check by client: " + stsClnt.poll());
        pause();

        stsClnt.send(new PingMsg("FromClient"));
        pause();
        System.out.println("check by server: " + stsSrv.poll());
        System.out.println("check by server: " + stsSrv.poll());
        pause();


        CallbackHandler callback = new CallbackHandler(
                10,1000,
                (m)->System.out.println("by callback: " + m),
                (Pollable) stsSrv);
        callback.start();
        pause();
        for (int i = 0; i < 5; i++) {
            stsClnt.send(new PingMsg("Check callbacking on arrived message FromClient " + i));
        }
        pause();
        pause();
        pause();


        System.out.println("closing:");
        stsClnt.close();
        stsSrv.close();
        callback.close();
        pause();

        SocketTransferService stsClnt2 =
                new SocketTransferServiceClient(13121, "localhost", "NEVER-CLIENT");
        pause();
        pause();
        stsClnt2.close();
        pause();

    }

    private static void pause() throws InterruptedException {
        Thread.sleep(1234);
        System.out.println(".");
    }
}
