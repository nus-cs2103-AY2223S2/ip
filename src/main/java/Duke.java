import java.io.*;
import java.util.*;

public class Duke {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Task> db = new ArrayList<Task>(100);
    
    protected static String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String outlines = "____________________________________________________________";
    protected static String introduction = "Hello! I'm Duke\nWhat can I do for you?";
    protected static String farewell = "Bye. Hope to see you again soon!";    

    public static void main(String[] args) throws IOException {
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
        if (message.equals("list")) { read(); }
        else if (message.startsWith("mark")) { markTask(message); }
        else if (message.startsWith("unmark", 0)) { unmarkTask(message); }
        else { update(message); }
    }
    private static void read() {
        System.out.println(outlines);
        for (int i = 1; i <= db.size(); i++) {
            System.out.println(i + "." + db.get(i-1));
        }
        System.out.println(outlines);
    }

    private static void update(String message) {
        db.add(new Task(message));
        System.out.println(outlines + "\n" + "added: "+ message + "\n" + outlines);
    }

    private static void markTask(String message) {
        int ind = Integer.parseInt(message.split(" ")[1]) - 1;
        db.get(ind).setDone();
    }

    private static void unmarkTask(String message) {
        int ind = Integer.parseInt(message.split(" ")[1]) - 1;
        db.get(ind).setUndone();
    }
}
