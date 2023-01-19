import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static int exit = 0;

    public static class Task {
        int done;
        String msg;
        public Task(String msg) {
            this.msg = msg;
            this.done = 0;
        }

        @Override
        public String toString() {
            if (this.done==0)  return "[ ] " + this.msg;
            else               return "[X] " + this.msg;
        }
    }

    public static class Todo extends Task {
        public Todo(String msg) {
            super(msg);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Deadline extends Task {
        protected String by;
        public Deadline(String msg, String by) {
            super(msg);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by:" + by + ")";
        }
    }
    public static class Event extends Task {
        protected String from;
        protected String to;
        public Event(String msg, String from, String to) {
            super(msg);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from:" + from + "to:" + to + ")";
        }
    }
    static List<Task> task_list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        while (exit != 1) {
            Scanner sc = new Scanner(System.in);
            String info = sc.nextLine();

            if (info.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                exit = 1;
            } else if (info.equals("list")) {
                showList();
            } else {
                try {
                    String[] segments = info.split(" ", 2);
                    String first = segments[0];
                    switch (first) {
                        case "mark":
                            int n = Integer.parseInt(segments[1]) - 1;
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("[X] "+ task_list.get(n).msg);
                            task_list.get(n).done = 1;
                            break;
                        case "unmark":
                            int num = Integer.parseInt(segments[1]) - 1;
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("[ ] "+ task_list.get(num).msg);
                            task_list.get(num).done = 0;
                            break;
                        case "todo":
                            String action = info.split(" ", 2)[1];
                            Task t = new Todo(action);
                            task_list.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(t);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            String msg = segments[1].split("/by",2)[0];
                            String by = segments[1].split("/by",2)[1];
                            Task d = new Deadline(msg, by);
                            task_list.add(d);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(d);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                            break;
                        case "event":
                            String event = segments[1].split("/from",2)[0];
                            String from = segments[1].split("/from",2)[1].split("/to")[0];
                            String to = segments[1].split("/from",2)[1].split("/to")[1];
                            Task e = new Event(event, from, to);
                            task_list.add(e);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(e);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                            break;
                        default:
                            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Arguments not enough.");
                }
            }
        }
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (Task tk : task_list) {
            System.out.println( (task_list.indexOf(tk)+1) + "." + tk.toString());
        }
    }
}
