import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String MESSAGE_START = "Hello! I'm Duke\nWhat can I do for you?";

        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_START);
        new Chatbot().readInput();
    }





}
