import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

        public void changeNum() {
            this.num -=1;
        }
        public String save() {
            return this.toString();
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
        public String save() {
            int i = 0;
            if (this.isDone== true) {
                i = 1;
            }
            return String.format("T|%d| %s", i, this.description);
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
        public String save() {
            int i = 0;
            if (this.isDone== true) {
                i = 1;
            }
            return String.format("D|%d| %s | %s", i, this.description, this.deadline);
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
        public String save() {
            int i = 0;
            if (this.isDone== true) {
                i = 1;
            }
            return String.format("E|%d| %s | %s|%s", i, this.description, this.from, this.to );
        }
        @Override
        public String toString() {
            return String.format("%d. [E][%s] %s(%s%s)", this.num, this.getStatusIcon(), this.description, this.from, this.to);
        }

    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String currentD = Paths.get("").toAbsolutePath().toString();;
        Path dirPath = Paths.get(currentD, "data");
        Path filePath = Paths.get(currentD, "data", "JaneList.txt");
        //check if data directory exists, and if it doesnt create one
        try {
            if (Files.notExists(dirPath)){
                Files.createDirectory(dirPath);
            }
        } catch (IOException err) {
            System.out.println("Unable to create directory");
            err.printStackTrace();
        //Check if the janelist exist inside the folder we have made and if it doesnt, create, if not read it
        } try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            } else {
                List<String> lines = Files.readAllLines(filePath);
                for (String s:lines) {
                    //to separate each portion of the task eg D | taskname | deadline to easily see which type of task and deadline
                    String[] line = s.split("\\|");
                    int i = Integer.parseInt(line[1]);
                    boolean b = (i==1);
                    if (line[0].equals("T")) {
                        Todo T = new Todo(tasks.size() + 1, line[2]);
                        T.changeState(b);
                        tasks.add(T);
                    } else if (line[0].equals("D")) {
                        Deadline D = new Deadline(tasks.size() + 1, line[2], line[3]);
                        D.changeState(b);
                        tasks.add(D);
                    } else if (line[0].equals("E")) {
                        Event E = new Event(tasks.size()+1, line[2], line[3], line[4]);
                        E.changeState(b);
                        tasks.add(E);
                    }

                }


            }

        } catch (IOException err) {
            System.out.println("Unable to create File");
            err.printStackTrace();
        }
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNext()) {
            String output = in.nextLine();
            if (output.equals("bye")) {
                List<String> currentList = new ArrayList<>();
                for (Task t : tasks) {
                    currentList.add(t.save());
                }
                try {
                    Files.write(filePath, currentList);
                }
                catch (IOException err)  {
                    System.out.println("cannot save list");
                    err.printStackTrace();
                }
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
            else if (output.startsWith("delete")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                if (num < count+1) {
                    System.out.println("Noted. I've removed this task:");
                    Task n = tasks.get(num-1);
                    System.out.println(n.toString());
                    for (int j = num; j <count;j++) {
                        Task t = tasks.get(j);
                        t.changeNum();
                    }
                    count -=1;
                    tasks.remove(n);
                    System.out.println("You now have " + count + " tasks");
                } else {
                    System.out.println("Number out of index");
                }
            }
            else if (!output.equals("list") ) {
                System.out.println("Im sorry I don't understand what you mean :((");
                continue;
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(tasks.get(i).toString());
                }
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

}