import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markDone() {
            this.isDone = true;
        }

        public void markUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return ("[" + this.getStatusIcon() + "] " + this.description);
        }
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        ArrayList<Task> listOfThings = new ArrayList<Task>();
        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();
            String firstWord = "";
            Integer secondInt = 0;
            String bye = "bye";
            String showList = "list";
            String mark = "mark";
            String unmark = "unmark";
            String todo = "todo";
            String list = "list";
            String event = "event";
            String byWhen = "";
            String fromWhen = "";
            String toWhen = "";
            if (msg.contains("mark") || msg.contains("unmark")) {
                firstWord = msg.substring(0, msg.indexOf(" "));
                try {
                    secondInt = Integer
                            .parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
                } catch (Exception ex) {

                }
            } else if (msg.contains("todo")) {
                firstWord = msg.substring(msg.indexOf(" ") + 1, msg.length());
                Todo newTodo = new Todo(firstWord);
                listOfThings.add(newTodo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTodo);
                System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
            } else if (msg.contains("deadline")) {
                firstWord = msg.substring(msg.indexOf(" ") + 1, msg.indexOf("/by") - 1);
                byWhen = msg.substring(msg.indexOf("/by") + 4, msg.length());
                Deadline newDeadline = new Deadline(firstWord, byWhen);
                listOfThings.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(newDeadline);
                System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
            } else if (msg.contains("event")) {
                firstWord = msg.substring(msg.indexOf(" ") + 1, msg.indexOf("/from") - 1);
                fromWhen = msg.substring(msg.indexOf("/from") + 6, msg.indexOf("/to") - 1);
                toWhen = msg.substring(msg.indexOf("/to") + 4, msg.length());
                Event newEvent = new Event(firstWord, fromWhen, toWhen);
                listOfThings.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println(newEvent);
                System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
            }
            if (bye.equalsIgnoreCase(msg)) {
                System.out.println("Bye. Hope to see Byou again soon!");
                loop = false;
            } else if (showList.equalsIgnoreCase(msg)) {
                for (int i = 0; i < listOfThings.size(); i++) {
                    System.out.println(i + 1 + ". " + listOfThings.get(i));
                }
            } else if (mark.equalsIgnoreCase(firstWord) && secondInt != 0) {
                Task thisTask = listOfThings.get(secondInt - 1);
                thisTask.markDone();
                listOfThings.set(secondInt - 1, thisTask);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(thisTask);
            } else if (unmark.equalsIgnoreCase(firstWord) && secondInt != 0) {
                Task thisTask = listOfThings.get(secondInt - 1);
                thisTask.markUndone();
                listOfThings.set(secondInt - 1, thisTask);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(thisTask);
            } else {
            }
        }
    }
}
