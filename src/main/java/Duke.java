import java.util.ArrayList;
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
                return "[D]" + super.toString() + " " + description + "(by: " + by + ")";
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
                return "[E]" + super.toString() + " " + description + "(from: " + from + "to: " + to + ")";
            }
        }

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        ArrayList<Task> taskArray = new ArrayList<>();
        int index = 0;

        Scanner scan = new Scanner(System.in);

        String nextLine = scan.nextLine();
        String[] arrNext = nextLine.split(" ", 2);
        String next = arrNext[0];
        try {
            if (arrNext.length <= 1) {
                if (Objects.equals(next, "todo") || Objects.equals(next, "deadline") || Objects.equals(next, "event") || Objects.equals(next, "mark") || Objects.equals(next, "unmark") || Objects.equals(next, "delete")) {
                    if (!Objects.equals(next, "list") && !Objects.equals(next, "bye")) {
                        throw new EmptyDescription(" The description of " + next + " cannot be empty.");
                    }
                }
            } else if (!Objects.equals(next, "todo") && !Objects.equals(next, "deadline") && !Objects.equals(next, "event")) {
                if (!Objects.equals(next, "list") && !Objects.equals(next, "mark") && !Objects.equals(next, "unmark") && !Objects.equals(next, "delete") && !Objects.equals(next, "bye")) {
                    throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (EmptyDescription | WrongTask e2) {
            System.out.println(e2);
            nextLine = scan.nextLine();
            arrNext = nextLine.split(" ", 2);
            next = arrNext[0];
        } finally {
            if (Objects.equals(next, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
        }

        String after = arrNext[1];

        Task input = null;
        switch (next) {
            case "todo":
                input = new Todo(after);
                break;
            case "deadline": {
                String[] split = after.split("/by ");
                input = new Deadline(split[0], split[1]);
                break;
            }
            case "event": {
                String[] split = after.split("/");
                input = new Event(split[0], split[1].substring(5), split[2].substring(3));
                break;
            }
        }

        while (true) {
            assert input != null;
            if (Objects.equals(next, "bye")) break;
            switch (next) {
                case "todo":
                    input = new Todo(after);
                    break;
                case "deadline": {
                    String[] split = after.split("/by ");
                    input = new Deadline(split[0], split[1]);
                    break;
                }
                case "event": {
                    String[] split = after.split("/");
                    input = new Event(split[0], split[1].substring(5), split[2].substring(3));
                    break;
                }
            }

            switch (next) {
                case "mark": {
                    int number = Integer.parseInt(after);
                    try {
                        if (number > taskArray.size()) {
                            throw new OutOfBounds(" There is no such element :-p");
                        }
                    } catch (OutOfBounds e3) {
                        System.out.println(e3);
                        break;
                    }
                    Task toMarkDone = taskArray.get(number - 1);
                    toMarkDone.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + toMarkDone);
                    break;
                }
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskArray.size(); i++) {
                        System.out.println(i + 1 + "." + taskArray.get(i));
                    }
                    break;
                case "unmark": {
                    int number = Integer.parseInt(after);
                    try {
                        if (number > taskArray.size()) {
                            throw new OutOfBounds(" There is no such element :-p");
                        }
                    } catch (OutOfBounds e3) {
                        System.out.println(e3);
                        break;
                    }
                    Task toUnMarkDone = taskArray.get(number - 1);
                    toUnMarkDone.unMarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + toUnMarkDone);
                    break;
                }
                case "delete": {
                    int number = Integer.parseInt(after);
                    try {
                        if (number > taskArray.size()) {
                            throw new OutOfBounds(" There is no such element :-p");
                        }
                    } catch (OutOfBounds e3) {
                        System.out.println(e3);
                        break;
                    }
                    Task toDelete = taskArray.get(number - 1);
                    taskArray.remove(number - 1);
                    index--;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(" " + toDelete);
                    System.out.println("Now you have " + taskArray.size() + " tasks in the list.");
                    break;
                }
                default:
                    taskArray.add(index, input);
                    System.out.println("Got it. I've added this task:\n" + "  " + input);
                    index++;
                    System.out.println("Now you have " + taskArray.size() + " tasks in the list.");
                    break;
            }
            nextLine = scan.nextLine();
            arrNext = nextLine.split(" ", 2);
            next = arrNext[0];
            try {
                if (arrNext.length <= 1) {
                    if (Objects.equals(next, "todo") || Objects.equals(next, "deadline") || Objects.equals(next, "event") || Objects.equals(next, "mark") || Objects.equals(next, "unmark") || Objects.equals(next, "delete")) {
                        if (!Objects.equals(next, "list") && !Objects.equals(next, "bye")) {
                            throw new EmptyDescription(" The description of " + next + " cannot be empty.");
                        }
                    }
                }
            } catch (EmptyDescription e1) {
                System.out.println(e1);
                nextLine = scan.nextLine();
                arrNext = nextLine.split(" ", 2);
                next = arrNext[0];
            }

            try {
                if (!Objects.equals(next, "todo") && !Objects.equals(next, "deadline") && !Objects.equals(next, "event")) {
                    if (!Objects.equals(next, "list") && !Objects.equals(next, "mark") && !Objects.equals(next, "unmark") && !Objects.equals(next, "delete") && !Objects.equals(next, "bye")) {
                        throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (WrongTask e2) {
                System.out.println(e2);
                nextLine = scan.nextLine();
                arrNext = nextLine.split(" ", 2);
                next = arrNext[0];
            }

            if (!Objects.equals(next, "list") && !Objects.equals(next, "bye")) {
                after = arrNext[1];
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

