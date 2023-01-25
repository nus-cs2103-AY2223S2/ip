import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

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
        public String toFile() {
            return "T|" + this.desc;
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
        public String toFile() {
            return "TD|" + this.isDone + "|" + this.desc;
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            return this.id + ". [T][" + statusIcon + "] " + this.desc;
        }
    }

    public static class Deadline extends Task {
        protected LocalDate deadline;

        public Deadline(int id, String description, LocalDate deadline) {
            super(id, description);
            this.deadline = deadline;
        }

        public String toFile() {
            return "D|" + this.isDone + "|" + this.desc + "|" + this.deadline;
        }

        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            String parsedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return id + ". [D][" + statusIcon + "] " + this.desc + "(" + parsedDeadline + ")";
        }
    }

    public static class Event extends Task {
        protected LocalDateTime start;
        protected LocalDateTime end;

        public Event(int id, String description, LocalDateTime start, LocalDateTime end) {
            super(id, description);
            this.start = start;
            this.end = end;
        }

        public String toFile() {
            return "E|" + this.isDone + "|" + this.desc + "|" + this.start + "|" + this.end;
        }
        @Override
        public String toString() {
            String statusIcon = this.getStatusIcon();
            String startDnT = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(start);
            String endDnT = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(end);
            return id + ". [E][" + statusIcon + "] " + this.desc + "(from: " + startDnT + " to: " + endDnT + ")";
        }
    }
    public static class DukeExceptions extends Exception {
        public DukeExceptions(String s) {
            super(s);
        }
    }

    public static void main(String[] args) throws DukeExceptions {
        String userDir = System.getProperty("user.dir");
        Path dataDirPath = Paths.get(userDir, "data");
        Path dataFilePath = Paths.get(userDir, "data", "Duke.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        //Create the data dir if it doesn't exist
        try {
            if(!Files.exists(dataDirPath)) {
                Files.createDirectory(dataDirPath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to create directory");
            e.printStackTrace();
        }
        //Create Duke.txt inside data dir if it doesn't exist
        //if it exists, read from it
        try {
            if(!Files.exists(dataFilePath)) {
                Files.createFile(dataFilePath);
            } else {
                List<String> data = Files.readAllLines(dataFilePath);
                for(String x: data) {
                    String[] inp = x.split("\\|");
                    String type = inp[0];
                    if(type.equals("T")) {
                        Task tsk = new Task(tasks.size() + 1, inp[1]);
                        tsk.setIsDone(Boolean.parseBoolean(inp[1]));
                        tasks.add(tsk);
                    } else if(type.equals("TD")) {
                        ToDo td = new ToDo(tasks.size() + 1, inp[2]);
                        td.setIsDone(Boolean.parseBoolean(inp[1]));
                        tasks.add(td);
                    } else if(type.equals("D")) {
                        Deadline d = new Deadline(tasks.size() + 1, inp[2], LocalDate.parse(inp[3]));
                        d.setIsDone(Boolean.parseBoolean(inp[1]));
                        tasks.add(d);
                    } else if(type.equals("E")) {
                        Event e = new Event(tasks.size() + 1, inp[2], LocalDateTime.parse(inp[3]),
                                    LocalDateTime.parse(inp[4]));
                        e.setIsDone(Boolean.parseBoolean(inp[1]));
                        tasks.add(e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to create Duke.txt");
            e.printStackTrace();
        }
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
                ArrayList<String> writing = new ArrayList<>();
                for(int i = 0; i < tasks.size(); i++) {
                    writing.add(tasks.get(i).toFile());
                }
                try {
                    Files.write(dataFilePath, writing);
                } catch (IOException e) {
                    System.out.println("Error while writing to file!");
                }
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
                    String deadline = inp[1].substring(3);
                    LocalDate date = LocalDate.parse(deadline);
                    try { //catching no description
                        String undesc = inp[0];
                        String desc = undesc.substring(9);
                        if(desc.equals("")) {
                            throw new DukeExceptions("Input cannot be empty!");
                        }
                        Deadline dl = new Deadline(tasks.size() + 1, desc, date);
                        tasks.add(dl);
                        continue;
                    } catch(DukeExceptions e){
                        System.out.println("Description cannot be empty!");
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println(e);
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
                String start = inp[1].substring(5);
                String[] DnT = start.split(" "); //[2000-10-23,10:15]
                String startDate = DnT[0];
                String startTime = DnT[1];
                LocalDateTime startDnT = LocalDateTime.parse(String.join("T", startDate, startTime));
                String end = inp[2].substring(3);
                String[] DateAndTime = end.split(" ");
                String endDate = DateAndTime[0];
                String endTime = DateAndTime[1];
                LocalDateTime endDnT = LocalDateTime.parse(String.join("T", endDate, endTime));
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
                Event ev = new Event(tasks.size() + 1, desc, startDnT, endDnT);
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
