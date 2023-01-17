import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Duke {
    private static int counter;
    private static HashMap<Integer, Task> toDoList;


    public static void main(String[] args)  {
        boolean terminate = false;
        counter = 0;
        toDoList = new HashMap<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try {
            while (!terminate) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();

                // Once detect "bye", print and terminate
                if (input.equals("bye")) {
                    terminate = true;
                    System.out.println("Bye. Hope to see you again soon!");

                // If input is "list", prints the toDoList
                } else if (input.equals("list")) {
                    print_list();

                // Else handles the commands
                } else {
                    toDoList.put(++counter, new Task(input));
                    System.out.print("added: ");
                    System.out.println(input);

                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void print_list() {
        for (Integer key: toDoList.keySet()) {
            System.out.println(key + ": " + toDoList.get(key));
        }
    }
}
