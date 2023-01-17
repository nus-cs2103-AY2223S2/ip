import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<Task> tasksList = new ArrayList<Task>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                int taskNumber = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : tasksList) {
                    System.out.println(Integer.toString(taskNumber) + ". " + t);
                    taskNumber++;
                }
            } else if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasksList.get(taskNumber).markAsDone();
            } else if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasksList.get(taskNumber).markAsNotDone();
            } else if (command.equals("todo")) {
                String inputWithoutCommand = input.substring(5);

                ToDo toDo = new ToDo(inputWithoutCommand);
                tasksList.add(toDo);

                System.out.println("Got it. I've added this task:");
                System.out.println(toDo);
                System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            } else if (command.equals("deadline")) {
                int indexOfByString = input.indexOf("/by ");
                int indexOfDate = indexOfByString + 4;

                String inputWithoutCommand = input.substring(9, indexOfByString - 1);

                String date = input.substring(indexOfDate, input.length() - 1);

                Deadline deadline = new Deadline(input.substring(9, indexOfByString), input.substring(indexOfDate));
                tasksList.add(deadline);

                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            } else if (command.equals("event")) {
                // TODO
                String inputWithoutCommand = input.substring(6);

                int indexOfFromSubstring = inputWithoutCommand.indexOf("/from ");
                int indexOfToSubstring = inputWithoutCommand.indexOf("/to ");

                String description = inputWithoutCommand.substring(0, indexOfFromSubstring);
                String startTime = inputWithoutCommand.substring(indexOfFromSubstring + 6, indexOfToSubstring);
                String endTime = inputWithoutCommand.substring(indexOfToSubstring + 4);

                Event event = new Event(description, startTime, endTime);
                tasksList.add(event);

                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            }
        }
    }

    public static class Task {
        protected  String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]"); // mark done task with X
        }

        @Override
        public String toString() {
            return getStatusIcon() + " " + this.description;
        }

        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this);
        }

        public void markAsNotDone() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this);
        }
    }

    public static class ToDo extends Task {
        private final String taskType = "[T]";

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return taskType + super.toString();
        }
    }

    public static class Deadline extends Task {
        private final String taskType = "[D]";
        private String byString;

        public Deadline(String description, String by) {
            super(description);
            this.byString = by;
        }

        @Override
        public String toString() {
            return taskType + super.toString() + " (by: " + this.byString + ")";
        }
    }

    public static class Event extends Task{
        private final String taskType = "[E]";
        private String startTime;
        private String endTime;

        public Event(String description, String startTime, String endTime) {
            super(description);
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return taskType + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
        }
    }
}
