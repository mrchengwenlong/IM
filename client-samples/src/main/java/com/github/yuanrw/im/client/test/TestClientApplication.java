package com.github.yuanrw.im.client.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2019-05-15
 * Time: 20:57
 *
 * @author yrw
 */
public class TestClientApplication {

    public static void main(String[] args) {
        List<TestClient> testClientList = new ArrayList<>();
        String[] usernameForTest = {
            "Adela", "Alice", "Bella", "Cynthia",
        };

        //login all user
        for (int i = 0; i < 4; i++) {
            testClientList.add(new TestClient("127.0.0.1", 9081,
                "http://127.0.0.1:8082", usernameForTest[i], "123abc"));
        }

        //print test result every 5 seconds
        ScheduledExecutorService printExecutor = Executors.newScheduledThreadPool(1);

        doInExecutor(printExecutor, 4, () -> {
            System.out.println("\n\n");
            System.out.println(String.format("sentMsg: %d, readMsg: %d, hasSentAck: %d, " +
                    "hasDeliveredAck: %d, hasReadAck: %d, hasException: %d",
                TestClient.sendMsg.get(), TestClient.readMsg.get(), TestClient.hasSentAck.get(),
                TestClient.hasDeliveredAck.get(), TestClient.hasReadAck.get(), TestClient.hasException.get()));
            System.out.println("\n\n");
        });


        //start simulate send
        ScheduledExecutorService clientExecutor = Executors.newScheduledThreadPool(20);

        testClientList.forEach(testClient -> doInExecutor(clientExecutor, 3, testClient::randomSendTest));
    }

    private static void doInExecutor(ScheduledExecutorService executorService, int period, Runnable doFunction) {
        executorService.scheduleAtFixedRate(doFunction, 0, period, TimeUnit.SECONDS);
    }
}
