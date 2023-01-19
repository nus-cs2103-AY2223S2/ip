import java.io.*;
import java.util.*;
public class Duke {
    public static void printInListFormat(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.printf("%d. [%s] %s\n", i + 1, currTask.getStatusIcon(),
                    currTask.getDescription());
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Dupe\nWhat can I do for you?");
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            ArrayList<Task> tasks = new ArrayList<>();
            String line = br.readLine();
            while (!line.equals("bye")) {
                if (line.equals("list")) {
                    printInListFormat(tasks);
                } else if (line.split(" ")[0].equals("mark")) {
                    Task toMarkDone = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
                    toMarkDone.markDone();
                    System.out.printf("Nice! I've marked this task as done:\n [%s] %s\n", toMarkDone.getStatusIcon(),
                            toMarkDone.getDescription());
                } else if (line.split(" ")[0].equals("unmark")) {
                    Task toMarkUndone = tasks.get(Integer.parseInt(line.split(" ")[1]) - 1);
                    toMarkUndone.markUndone();
                    System.out.printf("OK, I've marked this task as not done yet:\n [%s] %s\n", toMarkUndone.getStatusIcon(),
                            toMarkUndone.getDescription());
                } else {
                    tasks.add(new Task(line));
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
