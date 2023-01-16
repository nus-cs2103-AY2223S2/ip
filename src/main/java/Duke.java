import java.io.*;

public class Duke {
    private static String outlines = "____________________________________________________________";
    private static String introduction = "Hello! I'm Duke\nWhat can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(outlines + "\n" + introduction + "\n" + outlines);

        String input = br.readLine();
        while (!checkEndConvo(input)) {
            System.out.println(outlines + "\n" + input + "\n" + outlines);
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
}
