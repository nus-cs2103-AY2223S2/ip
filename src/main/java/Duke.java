import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class Task {
        protected int id;
        protected String desc;
        protected boolean isDone;

        public Task(int id, String description) {
            this.id = id;
            this.desc = description;
            this.isDone = false;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void setIsDone(boolean status) {
            this.isDone = status;
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return id + ". [" + statusIcon + "] " + this.desc;
        }
    }

    public static class ToDo extends Task {

        public ToDo(int id, String description) {
            super(id, description);
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return this.id + ". [T][" + statusIcon + "] " + this.desc;
        }
    }

    public static class Deadline extends Task {
        protected String deadline;

        public Deadline(int id, String description, String deadline) {
            super(id, description);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return id + ". [D][" + statusIcon + "] " + this.desc + " (" + this.deadline + ")";
        }
    }

    public static class Event extends Task {
        protected String start;
        protected String end;

        public Event(int id, String description, String start, String end) {
            super(id, description);
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return id + ". [E][" + statusIcon + "] " + this.desc + "(" + this.start + this.end + ")";
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            if (input.equals("list")) {
                tasks.forEach(x -> {
                    System.out.println(x);
                });
                continue;
            }
            if (input.equals("bye")) {
                System.out.println("See you again, thanks for visiting!");
                break;
            }
            if(input.startsWith("mark ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                if(id > tasks.size()){
                    System.out.println("No such task found!");
                    continue;
                }
                Task marked = tasks.get(id - 1);
                marked.setIsDone(true);
                System.out.println("Good job! This task is now marked done!\n" + marked);
                continue;
            }
            if(input.startsWith("unmark ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                if(id > tasks.size()){
                    System.out.println("No such task found!");
                    continue;
                }
                Task unmarked = tasks.get(id - 1);
                unmarked.setIsDone(false);
                System.out.println("What a bummer! This task is now unmarked\n" + unmarked);
                continue;
            }
            if(input.startsWith("todo ")) {
                String td = input.substring(5);
                ToDo todo = new ToDo(tasks.size() + 1, td);
                tasks.add(todo);
                continue;
            }
            if(input.startsWith("deadline ")) {
                String[] inp = input.split("/");
                String deadline = inp[1];
                String undesc = inp[0];
                String desc = undesc.substring(9);
                Deadline dl = new Deadline(tasks.size() + 1, desc, deadline);
                tasks.add(dl);
                continue;
            }
            if(input.startsWith("event ")) {
                String[] inp = input.split("/");
                String start = inp[1];
                String end = inp[2];
                String undesc = inp[0];
                String desc = undesc.substring(6);
                Event ev = new Event(tasks.size() + 1, desc, start, end);
                tasks.add(ev);
                continue;
            }
            Task tsk = new Task(tasks.size() + 1,input);
            tasks.add(tsk);
            System.out.println(input);
        }
    }
}
