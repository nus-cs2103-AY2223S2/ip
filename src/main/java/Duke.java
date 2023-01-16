import java.io.*;
import java.util.*;

public class Duke {
    private static String outlines = "____________________________________________________________";
    private static String introduction = "Hello! I'm Duke\nWhat can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<String> db = new ArrayList<String>(100);

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(outlines + "\n" + introduction + "\n" + outlines);

        String input = br.readLine();
        while (!checkEndConvo(input)) {
            handleMessage(input);
            input = br.readLine();
        }

        br.close();
    }

    private static boolean checkEndConvo(String message) {
        if (message.equals("bye")) {
            System.out.println(outlines + "\n" + farewell + "\n" + outlines);
            return true;
        }
        return false;
    }

    private static void handleMessage(String message) {
        switch (message) {
            case "list":
                read();
                break;
            default:
                update(message);

        }
    }
    private static void read() {
        System.out.println(outlines);
        for (int i = 1; i <= db.size(); i++) {
            System.out.println(i + ". " + db.get(i-1));
        }
        System.out.println(outlines);
    }

    private static void update(String message) {
        db.add(message);
        System.out.println(outlines + "\n" + "added: "+ message + "\n" + outlines);
    }
}
