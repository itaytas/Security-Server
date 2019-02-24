package com.itaytas.securityServer.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.err.println("The time is now " + dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedRate = 3000)
    public void reportHelloWorld() {
        System.err.println("HelloWorld, The time is now " + dateFormat.format(new Date()));
    }
}