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

            public String toString() {
                return "[" + getStatusIcon() + "]";
            }
        }

        class Todo extends Task {
            public Todo(String description) {
                super(description);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString() + " " + description;
            }
        }

        class Deadline extends Task {
            protected final String by;
            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }
            @Override
            public String toString() {
                return "[D]" + super.toString() + description + "(by: " + by + ")";
            }
        }

        class Event extends Task {
            protected final String from;
            protected final String to;
            public Event(String description, String from, String to) {
                super(description);
                this.from = from;
                this.to = to;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + description + "(from: " + from + "to: " + to + ")";
            }
        }

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Task[] array = new Task[100];
        int index = 0;

        Scanner scan = new Scanner(System.in);
        String next = scan.next();
        String nextLine = scan.nextLine();

        Task input = null;
        if (Objects.equals(next, "todo")) {
            input = new Todo(nextLine.substring(1));
        } else if (Objects.equals(next, "deadline")) {
            String[] split = nextLine.split("/by ");
            input = new Deadline(split[0], split[1]);
        } else if (Objects.equals(next, "event")) {
            String[] split = nextLine.split("/");
            input = new Event(split[0], split[1].substring(5), split[2].substring(3));
        }

        while (true) {
            assert input != null;
            if (Objects.equals(next, "bye")) break;
            if (Objects.equals(next, "todo")) {
                input = new Todo(nextLine.substring(1));
            } else if (Objects.equals(next, "deadline")) {
                String[] split = nextLine.split("/by ");
                input = new Deadline(split[0], split[1]);
            } else if (Objects.equals(next, "event")) {
                String[] split = nextLine.split("/");
                input = new Event(split[0], split[1].substring(5), split[2].substring(3));
            }

            if (Objects.equals(next, "mark")) {
                int number = Integer.parseInt(nextLine.substring(1));
                Task toMarkDone = array[number - 1];
                toMarkDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + toMarkDone);
            } else if (Objects.equals(next, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(i+1 + "." + array[i]);
                }
            } else if (Objects.equals(next, "unmark")) {
                int number = Integer.parseInt(nextLine.substring(1));
                Task toUnMarkDone = array[number - 1];
                toUnMarkDone.unMarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + toUnMarkDone);
            } else {
                array[index] = input;
                System.out.println("Got it. I've added this task:\n" + "  " + input);
                index++;
                System.out.println("Now you have " + index + " tasks in the list.");
            }
            next = scan.next();
            nextLine = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
