import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.lang.Exception;

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
            return String.format("%d. [%s](%s)", this.num, this.getStatusIcon(), this.description);
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
            return String.format("%d. [T][%s]%s", this.num, this.getStatusIcon(), this.description);
        }
    }
    public static class Deadline extends Task{
        protected LocalDateTime deadline;
        public Deadline(int num, String description, LocalDateTime deadline) {
            super(num, description);
            this.deadline = deadline;
        }
        @Override
        public String save() {
            int i = 0;
            if (this.isDone== true) {
                i = 1;
            }
            return String.format("D|%d| %s |%s", i, this.description, this.deadline);
        }
        @Override
        public String toString() {
            String parsed = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return String.format("%d. [D][%s] %s(%s)", this.num, this.getStatusIcon(), this.description, parsed);
        }

    }
    public static class Event extends Task{
        protected LocalDateTime from;
        protected LocalDateTime to;
        public Event(int num, String description, LocalDateTime from, LocalDateTime to) {
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
            return String.format("E|%d| %s |%s|%s", i, this.description, this.from, this.to );
        }
        @Override
        public String toString() {
            String first = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(from);
            String end = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(to);
            return String.format("%d. [E][%s] %s(from %s to %s)", this.num, this.getStatusIcon(), this.description, first, end);
        }

    }

    public static class JaneException extends Exception {
        public JaneException(String err) {
            super(err);
        }
    }

    public static class Ui {
        public static void start() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        }

    }

    public static class Storage {
        private static final String currentD = Paths.get("").toAbsolutePath().toString();
        private static final Path dirPath = Paths.get(currentD, "data");
        private static final Path filePath = Paths.get(currentD, "data", "JaneList.txt");

        public static void createDir() {
            try {
                if (Files.notExists(dirPath)) {
                    Files.createDirectory(dirPath);
                }
            } catch (IOException err) {
                System.out.println("Unable to create directory");
                err.printStackTrace();
            }

        }

        public static ArrayList<Task> loadList() {
            if (Files.notExists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException err) {
                    System.out.println("unable to create list");
                    err.printStackTrace();
                }
            }
            List<String> lines = null;
            try {
                lines = Files.readAllLines(filePath);
            } catch (IOException err) {
                System.out.println("cannot read the list");
                assert lines != null;
            }
            ArrayList<Task> tasks = new ArrayList<Task>();
            for (String s : lines) {
                //to separate each portion of the task eg D | taskname | deadline to easily see which type of task and deadline
                String[] line = s.split("\\|");
                int i = Integer.parseInt(line[1]);
                boolean b = (i == 1);
                if (line[0].equals("T")) {
                    Todo T = new Todo(tasks.size() + 1, line[2]);
                    T.changeState(b);
                    tasks.add(T);
                } else if (line[0].equals("D")) {
                    Deadline D = new Deadline(tasks.size() + 1, line[2], LocalDateTime.parse(line[3]));
                    D.changeState(b);
                    tasks.add(D);
                } else if (line[0].equals("E")) {
                    Event E = new Event(tasks.size() + 1, line[2], LocalDateTime.parse(line[3]), LocalDateTime.parse(line[4]));
                    E.changeState(b);
                    tasks.add(E);
                }

            }
            return tasks;
        }

        public static void updateList(ArrayList<Task> tasks) {
            ArrayList<String> list = new ArrayList<String>();
            for (Task t : tasks) {
                list.add(t.save());
            }
            try {
                Files.write(filePath, list);
            } catch (IOException err) {
                System.out.println("cannot save list");
                err.printStackTrace();
            }
        }
    }

    public static class Parser {
        public static Todo parserT(String output, int count) {
            String des = output.substring(5);
            Todo todo = new Todo(count+1, des);
            return todo;
        }

        public static Deadline parserD(String output, int count) {
            String des = output.substring(9);
            String[] s = des.split("/");
            try {
                if (s.length == 1) {
                    throw new JaneException("Please specify when the deadline is :(((");
                }
            } catch(JaneException err) {
                System.out.println("Please specify when the deadline is :(((");

            }
            Deadline d = new Deadline(count +1, s[0], LocalDateTime.parse(s[1].substring(3)));
            return d;
        }

        public static Event parserE(String output, int count) {
            String des = output.substring(6);
            String[] s = des.split("/");
            try {
                if (s.length == 1) {
                    throw new JaneException("Please specify when the event is :(((");
                }
            } catch (JaneException err){
                System.out.println("Please specify when event is");

            }
            String[] start = s[1].substring(5).split(" ");
            LocalDateTime startE = LocalDateTime.parse(String.format("%sT%s", start[0], start[1]));
            //here i am assuming an event only lasts 1 day since the day it starts is the day it ends
            LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", start[0], s[2]));
            Event e = new Event(count +1, s[0], startE, end);
            return e;
        }

    }



    public static class TaskList {
        protected static ArrayList<Task> tasks;

        TaskList(ArrayList<Task> tasks) {
            this.tasks = tasks;
        }

        public static void useCommand(String output) {
            if (output.equals("bye")) {
                List<String> currentList = new ArrayList<>();
                for (Task t : tasks) {
                    currentList.add(t.save());
                }
                try {
                    Storage.updateList(tasks);
                } catch (Exception err) {
                    System.out.println("cannot save list");
                    err.printStackTrace();
                }

            } else if (output.startsWith("mark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("Nice! I've marked this task as done");
                    Task n = tasks.get(num - 1);
                    n.changeState(true);
                    System.out.println(n.toString());
                } catch (Exception err) {
                    err.printStackTrace();
                    System.out.println("Number out of index");
                }

            } else if (output.equals("todo") || output.equals("deadline") || output.equals("event")) {
                System.out.println("Please specify the task to be done :(((");
            } else if (output.startsWith("todo")) {
                Todo todo = Parser.parserT(output, tasks.size());
                tasks.add(todo);
                System.out.println(todo.toString());
            } else if (output.startsWith("deadline")) {
                Deadline d = Parser.parserD(output,tasks.size() +1);
                tasks.add(d);
                System.out.println(d.toString());
            } else if (output.startsWith("event")) {
                String des = output.substring(6);
                String[] s = des.split("/");
                if (s.length == 1) {
                    System.out.println("Please specify when the event is :(((");

                }
                String[] start = s[1].substring(5).split(" ");
                LocalDateTime startE = LocalDateTime.parse(String.format("%sT%s", start[0], start[1]));
                //here i am assuming an event only lasts 1 day since the day it starts is the day it ends
                LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", start[0], s[2]));
                Event e = new Event(tasks.size() +1, s[0], startE, end);
                tasks.add(e);
                System.out.println(e.toString());
            } else if (output.startsWith("unmark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("OK, I've marked this task as not done yet");
                    Task n = tasks.get(num - 1);
                    n.changeState(false);
                    System.out.println(n.toString());
                } catch (Exception err) {
                    System.out.println("Number out of index");
                    err.printStackTrace();
                }
            } else if (output.startsWith("delete")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("Noted. I've removed this task:");
                    Task n = tasks.get(num - 1);
                    System.out.println(n.toString());
                    for (int j = num; j < tasks.size(); j++) {
                        Task t = tasks.get(j);
                        t.changeNum();
                    }

                    tasks.remove(n);
                    System.out.println("You now have " + tasks.size() + " tasks");
                } catch (Exception err) {
                    System.out.println("Number out of index");
                    err.printStackTrace();
                }
            } else if (!output.equals("list")) {
                System.out.println("Im sorry I don't understand what you mean :((");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(tasks.get(i).toString());
                }
            }
        }
    }


    public static void main(String[] args) throws JaneException {
        Ui.start();
        Storage.createDir();
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList(Storage.loadList());
        while (in.hasNext()) {
            String output = in.nextLine();
            tasks.useCommand(output);
        }
    }
}