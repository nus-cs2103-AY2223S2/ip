import java.io.*;
import java.util.*;
public class Duke {
    public static void printInListFormat(ArrayList<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, texts.get(i));
        }
    }
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
            ArrayList<String> text = new ArrayList<>();
            String line = br.readLine();
            while (!line.equals("bye")) {
                if (line.equals("list")) {
                    printInListFormat(text);
                }
                else {
                    text.add(line);
                    System.out.printf("added: %s\n", line);
                }
                line = br.readLine();
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
        catch (IOException ioe) {
            System.out.println("IO Exception raised");
        }
    }
}
