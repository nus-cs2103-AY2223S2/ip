package duke;

import java.io.*;
import java.math.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.prefs.*;
import java.util.regex.*;
 import java.util.stream.*;

public class Recur extends Events implements Runnable  {

    //Change to time from - currentTime once it works
    private int mockRemainingTime;

    protected Recur() {

    }

    Recur(String description, String from, String to, int mockRemainingTime) {
        super(description, from, to);
        this.mockRemainingTime = mockRemainingTime;
    }

    int getMockRemainingTime() {
        return mockRemainingTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(mockRemainingTime);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.out.println("Scheduled task");
    }






}
