import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        class Task {
            protected final String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "X" : " "); //mark done task with X
            }

            public void markAsDone() {
                this.isDone = true;
            }

            public void unMarkAsDone() {
                this.isDone = false;
            }
        }
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Task[] array = new Task[100];

        Scanner scan = new Scanner(System.in);
        Task input = new Task(scan.nextLine());
        int index = 0;

        while (!Objects.equals(input.description, "bye")) {
            if (Objects.equals(input.description.substring(0,4), "mark")) {
                int number = Integer.parseInt(input.description.substring(5));
                Task toMarkDone = array[number-1];
                toMarkDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + toMarkDone.getStatusIcon() + "] " + toMarkDone.description);
            } else if (Objects.equals(input.description, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + ".[" + array[i].getStatusIcon() + "] " + array[i].description);
                }
            } else if (Objects.equals(input.description.substring(0,6), "unmark")) {
                int number = Integer.parseInt(input.description.substring(7));
                Task toUnMarkDone = array[number - 1];
                toUnMarkDone.unMarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [" + toUnMarkDone.getStatusIcon() + "] " + toUnMarkDone.description);
            } else {
                array[index] = input;
                System.out.println("added: " + input.description);
                index++;
            }
            input = new Task(scan.nextLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
