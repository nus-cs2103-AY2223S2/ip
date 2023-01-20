import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task[] history = new Task[100];
        int count = 0;
        String input = "";

        String greeting = "-----------------------------------------\n" +
                        "Hello! I'm Duke, what can I do for you?\n" +
                        "-----------------------------------------\n";
        String goodbye = "-----------------------------------------\n" +
                         "Bye! Hope to see you again soon!\n" +
                         "-----------------------------------------\n";

        System.out.println(greeting);
        while (!input.equals("bye")) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            String[] tokens = input.split(" ", 2);
            try {
                if (tokens.length == 2) {
                    int index = Integer.parseInt(tokens[1]);
                    if (tokens[0].equals("mark") && index > 0 && index <= 100) {
                        history[index - 1].markDone();
                        System.out.println("-----------------------------------------");
                        System.out.println("I've marked this task as done:");
                        System.out.println(history[index - 1]);
                        System.out.println("-----------------------------------------");
                        continue;
                    } else if (tokens[0].equals("unmark") && index > 0 && index <= 100) {
                        history[index - 1].markUndone();
                        System.out.println("-----------------------------------------");
                        System.out.println("I've unmarked this task as not done yet:");
                        System.out.println(history[index - 1]);
                        System.out.println("-----------------------------------------");
                        continue;
                    }
                }
            } catch (NumberFormatException nfe){}

            if (input.equals("list")) {
                System.out.println("-----------------------------------------");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + history[i]);
                }
                System.out.println("-----------------------------------------");
                continue;
            }

            String[] newTask = input.split(" ", 2);
            if (newTask[0].equals("todo")) {
                history[count] = new Todo(newTask[1]);
                System.out.println("-----------------------------------------\n" +
                        "added: " + history[count] + "\n" +
                        "Now you have " + (count + 1) + " task(s) in the list.\n" +
                        "-----------------------------------------\n");
                count++;
            }
            if (newTask[0].equals("deadline")) {
                String[] taskTime = newTask[1].split("/", 2);
                history[count] = new Deadline(taskTime[0], String.join(",", taskTime[1].split("/")));
                System.out.println("-----------------------------------------\n" +
                        "added: " + history[count] + "\n" +
                        "Now you have " + (count + 1) + " task(s) in the list.\n" +
                        "-----------------------------------------\n");
                count++;
            }
            if (newTask[0].equals("event")) {
                String[] taskTime = newTask[1].split("/", 2);
                history[count] = new Event(taskTime[0], String.join(",", taskTime[1].split("/")));
                System.out.println("-----------------------------------------\n" +
                        "added: " + history[count] + "\n" +
                        "Now you have " + (count + 1) + " task(s) in the list.\n" +
                        "-----------------------------------------\n");
                count++;
            }


        }

        System.out.println(goodbye);

    }

}

