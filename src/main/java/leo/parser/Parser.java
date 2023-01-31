package leo.parser;

import java.util.Scanner;

public class Parser {
    private Scanner io;

    public Parser() {
        io = new Scanner(System.in);
    }
    
    public String[] parseRequest() {
        String request = io.nextLine();
        return request.split(" ", 2);
    }

    public static int getTaskID(String taskID) {
        return Integer.parseInt(taskID) - 1;
    }

    public void close() {
        io.close();
    }
}