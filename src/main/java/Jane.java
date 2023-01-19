import java.util.Scanner;
import java.util.ArrayList;

public class Jane {
    public static class Task {
        protected String description;
        protected boolean isDone;
        protected int num;
        public Task(int num, String description) {
            this.num = num;
            this.description = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void changeState(boolean stat) {
            this.isDone = stat;
        }
        @Override
        public String toString() {
            return String.format("%d. [%s] %s", this.num, this.getStatusIcon(), this.description);
        }
    }

    public static class Todo extends Task{
        public Todo(int num, String description) {
            super(num, description);
        }
        @Override
        public String toString() {
            return String.format("%d. [T][%s] %s", this.num, this.getStatusIcon(), this.description);
        }
    }
    public static class Deadline extends Task{
        protected String deadline;
        public Deadline(int num, String description, String deadline) {
            super(num, description);
            this.deadline = deadline;
        }
        @Override
        public String toString() {
            return String.format("%d. [D][%s] %s(%s)", this.num, this.getStatusIcon(), this.description, this.deadline);
        }

    }
    public static class Event extends Task{
        protected String from;
        protected String to;
        public Event(int num, String description, String from, String to) {
            super(num, description);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return String.format("%d. [E][%s] %s(%s %s)", this.num, this.getStatusIcon(), this.description, this.from, this.to);
        }

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        int count = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (in.hasNext()) {
            String output = in.nextLine();
            if (output.equals("bye")) {
                break;
            }
            else if(output.startsWith("mark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num < count+1) {
                    System.out.println("Nice! I've marked this task as done");
                    Task n = tasks.get(num-1);
                    n.changeState(true);
                    System.out.println(n.toString());
                } else {
                    System.out.println("Number out of index");
                }

            }
            else if (output.equals("todo")||output.equals("deadline") ||output.equals("event")){
                System.out.println("Please specify the task to be done :(((");
                continue;
            }
            else if (output.startsWith("todo")) {
                String des = output.substring(5);
                count+=1;
                Todo todo = new Todo(count, des);
                tasks.add(todo);
                System.out.println(todo.toString());
            }
            else if (output.startsWith("deadline")) {
                String des = output.substring(9);
                String[] s = des.split("/");
                if (s.length == 1) {
                    System.out.println("Please specify when the deadline is :(((");
                    continue;
                }
                count+=1;
                Deadline d = new Deadline(count, s[0], s[1]);
                tasks.add(d);
                System.out.println(d.toString());
            }
            else if(output.startsWith("event")) {
                String des = output.substring(6);
                String[] s = des.split("/");
                if (s.length == 1) {
                    System.out.println("Please specify when the event is :(((");
                    continue;
                }
                count+=1;
                Event e = new Event(count, s[0], s[1], s[2]);
                tasks.add(e);
                System.out.println(e.toString());
            }
            else if(output.startsWith("unmark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num < count + 1) {
                    System.out.println("OK, I've marked this task as not done yet");
                    Task n = tasks.get(num-1);
                    n.changeState(false);
                    System.out.println(n.toString());
                } else {
                    System.out.println("Number out of index");
                }
            }
            else if (!output.equals("list") ) {
                System.out.println("Im sorry I don't understand what you mean :((");
                continue;
            } else {
                for (int i = 0; i < count; i++) {
                    System.out.println(tasks.get(i).toString());
                }
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

}