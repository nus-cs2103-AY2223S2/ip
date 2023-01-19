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
        public void setId(int id) {
            this.id = id;
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
            return id + ". [D][" + statusIcon + "] " + this.desc + "(" + this.deadline + ")";
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
    public static class DukeExceptions extends Exception {
        public DukeExceptions(String s) {
            super(s);
        }
    }

    public static void main(String[] args) throws DukeExceptions {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            if (input.equals("list")) {
                try { //check if list is empty
                    if(tasks.isEmpty()) {
                        throw new DukeExceptions("List is empty!");
                    }
                } catch(DukeExceptions de) {
                    System.out.println("List is empty!");
                    continue;
                }
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
                try {
                    Task marked = tasks.get(id - 1);
                    marked.setIsDone(true);
                    System.out.println("Good job! This task is now marked done!\n" + marked);
                    continue;
                } catch(Exception e) {
                    System.out.println("No such task found!");
                    continue;
                }
            }
            if(input.startsWith("unmark ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                try {
                    Task unmarked = tasks.get(id - 1);
                    unmarked.setIsDone(false);
                    System.out.println("What a bummer! This task is now unmarked\n" + unmarked);
                    continue;
                } catch(Exception e) {
                    System.out.println("No such task found!");
                    continue;
                }
            }
            if(input.startsWith("todo ")) {
                String td = input.substring(5);
                try {
                    if(td.equals("")) {
                        throw new DukeExceptions("Description cannot be empty!");
                    }
                } catch(DukeExceptions e) {
                    System.out.println("Description cannot be empty!");
                    continue;
                }
                ToDo todo = new ToDo(tasks.size() + 1, td);
                tasks.add(todo);
                continue;
            }
            if(input.startsWith("deadline ")) {
                try { //catching no desc and no deadline
                    if(input.substring(9).equals("")) {
                        throw new DukeExceptions("Input cannot be empty!");
                    }
                } catch(DukeExceptions e) {
                    System.out.println("Input cannot be empty!");
                    continue;
                }
                String[] inp = input.split("/");
                try { //catching no deadline
                    String deadline = inp[1];
                    try { //catching no description
                        String undesc = inp[0];
                        String desc = undesc.substring(9);
                        if(desc.equals("")) {
                            throw new DukeExceptions("Input cannot be empty!");
                        }
                        Deadline dl = new Deadline(tasks.size() + 1, desc, deadline);
                        tasks.add(dl);
                        continue;
                    } catch(DukeExceptions e){
                        System.out.println("Description cannot be empty!");
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("Please input a deadline!");
                    continue;
                }
            }
            if(input.startsWith("event ")) {
                try { //catching no desc and no deadline
                    if(input.substring(6).equals("")) {
                        throw new DukeExceptions("Input cannot be empty!");
                    }
                } catch(DukeExceptions e) {
                    System.out.println("Input cannot be empty!");
                    continue;
                }
                String[] inp = input.split("/");
                try { //Catching for input format error
                    if(inp.length != 3) {
                        throw new DukeExceptions("Format is task, start, end!");
                    }
                } catch(DukeExceptions de) {
                    System.out.println("Format is task, /start, /end!");
                    continue;
                }
                String start = inp[1];
                String end = inp[2];
                String undesc = inp[0];
                String desc = undesc.substring(6);
                try { //catching for empty description
                    if(desc.equals("")) {
                        throw new DukeExceptions("Description cannot be empty!");
                    }
                } catch(DukeExceptions e) {
                    System.out.println("Description cannot be empty!");
                    continue;
                }
                Event ev = new Event(tasks.size() + 1, desc, start, end);
                tasks.add(ev);
                continue;
            }
            if (input.startsWith("delete ")) {
                String[] inp = input.split(" ");
                int id = Integer.parseInt(inp[1]);
                try {
                    Task del = tasks.remove(id - 1);
                    tasks.forEach(x -> x.setId(tasks.indexOf(x) + 1)); //update id of all items after removing
                    continue;
                } catch(Exception e) {
                    System.out.println("No such task found!");
                    continue;
                }
            }
            Task tsk = new Task(tasks.size() + 1,input);
            tasks.add(tsk);
            System.out.println(input);
        }
    }
}
