import java.util.Scanner;
import java.util.ArrayList;
import java.lang.RuntimeException;

public class Duke {

    private static class DukeException extends RuntimeException {
        public DukeException(String errMsg, Throwable err) {
            super(errMsg, err);
        }
    }
    private static class Task {
        public String title;
        private Boolean done;

        public Task(String title) {
            this.title = title;
            this.done = false;
        }

        public void mark() {
            this.done = true;
        }

        public void unmark() {
            this.done = false;
        }

        public Boolean isDone() {
            return this.done;
        }

        @Override
        public String toString() {
            String checkBox = this.done ? "[X]" : "[ ]";
            return checkBox + " " + this.title;
        }
    }

    private static class Todo extends Task {
        public Todo(String title) {
            super(title);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {
        private String by;
        public Deadline(String title, String by) {
            super(title);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    private static class Event extends Task {
        private String from;
        private String to;
        public Event(String title, String from, String to) {
            super(title);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        }
    }

    private static ArrayList<Task> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String input = sc.nextLine();
        while(!input.equals("bye")) {

            if (input.equals("list")) {
                printTasks();
            } else {

                if (input.split(" ")[0].equals("mark")) {
                    markTask(Integer.parseInt(input.split(" ")[1]));
                } else if (input.split(" ")[0].equals("unmark")) {
                    unmarkTask(Integer.parseInt(input.split(" ")[1]));
                } else if (input.split(" ")[0].equals("todo")) {
                    if (input.split(" ").length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        addTodo(input.replaceAll("todo", "").trim());
                    }
                } else if (input.split(" ")[0].equals("deadline")) {
                    if (input.split(" ").length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        String[] s = input.split("/by");
                        addDeadline(s[0].split("/by")[0].replaceAll("deadline", "").trim(),
                                s[1].substring(1));
                    }
                } else if (input.split(" ")[0].equals("event")) {
                    if (input.split(" ").length == 1) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        String[] words = input.split("/from");
                        String title = words[0].replaceAll("event", "").trim();
                        String from = words[1].split("/to")[0].trim();
                        String to = words[1].split("/to")[1].trim();
                        addEvent(title, from, to);
                    }
                } else {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("    ____________________________________________________________");
                }
            }
        input = sc.nextLine();
        }

        printMsg("Bye. Hope to see you again soon!");

    }

    private static void addTodo(String title) {
        Todo newTodo = new Todo(title);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTodo);
        System.out.println("     Now you have " + (tasks.size() + 1) + " tasks in the list");
        System.out.println("    ____________________________________________________________");
        tasks.add(newTodo);
    }

    private static void addEvent(String title, String from, String to) {
        Event newEvent = new Event(title, from, to);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newEvent);
        System.out.println("     Now you have " + (tasks.size() + 1) + " tasks in the list");
        System.out.println("    ____________________________________________________________");
        tasks.add(newEvent);
    }

    private static void addDeadline(String title, String by) {
        Deadline newDeadline = new Deadline(title, by);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newDeadline);
        System.out.println("     Now you have " + (tasks.size() + 1) + " tasks in the list");
        System.out.println("    ____________________________________________________________");
        tasks.add(newDeadline);
    }

    private static void printTasks() {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " +  count + "." + task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void markTask(int taskNum) {
        Task selectedTask = tasks.get(taskNum - 1);
        selectedTask.mark();

        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + selectedTask);
        System.out.println("    ____________________________________________________________");
    }

    private static void unmarkTask(int taskNum) {
        Task selectedTask = tasks.get(taskNum - 1);
        selectedTask.unmark();

        System.out.println("    ____________________________________________________________");
        System.out.println("     OK! I've marked this task as not done yet:");
        System.out.println("       " + selectedTask);
        System.out.println("    ____________________________________________________________");
    }

    private static void printMsg(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    ____________________________________________________________");
    }
}
