import java.io.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Dupe\nWhat can I do for you?");
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (!line.equals("bye")) {
                System.out.println(line);
                line = br.readLine();
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised");
        }
    }
}
