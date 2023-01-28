import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The Duke program implements a 'to-do' list
 * where users can add, mark and delete tasks 
 */

public class Duke {

    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        BYE,
        ERR
    }

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
        protected LocalDate by;
        protected LocalTime when;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        public Deadline(String description, LocalDate by, LocalTime when) {
            super(description);
            this.by = by;
            this.when = when;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + formatter.format(by) + " " + when + ")";
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

    public static class DukeException extends RuntimeException {
        public DukeException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
    }

    public static ArrayList<Task> readSave() {
        try {
            ArrayList<Task> listOfThings = new ArrayList<Task>();
            File saveFile = new File("src/duke.txt");
            Scanner fileReader = new Scanner(saveFile);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                char taskType = data.charAt(4);
                boolean taskDone = (data.charAt(7) == 'X');
                String taskName = "";
                

                switch(taskType) {
                case 'T':
                    taskName = data.substring(10, data.length());
                    Todo newTodo = new Todo(taskName);
                    if (taskDone) {
                        newTodo.markDone();
                    }
                    listOfThings.add(newTodo);
                    break;
                case 'D':
                    taskName = data.substring(10, data.indexOf("(by:") - 1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
                    String byWhen = data.substring(data.indexOf("by:") + 4, data.length() - 7);
                    LocalDate date = LocalDate.parse(byWhen, formatter);
                    String byTime = data.substring(data.length() - 6, data.length() - 1);
                    LocalTime time = LocalTime.parse(byTime);
                    Deadline newDeadline = new Deadline(taskName, date, time);
                    if (taskDone) {
                        newDeadline.markDone();
                    }
                    listOfThings.add(newDeadline);
                    break;
                case 'E':
                    taskName = data.substring(10, data.indexOf("(from:") - 1);
                    String fromWhen = data.substring(data.indexOf("from:") + 6, data.indexOf("to:") - 1);
                    String toWhen = data.substring(data.indexOf("to:") + 4, data.length() - 1);
                    Event newEvent = new Event(taskName, fromWhen, toWhen);
                    if (taskDone) {
                        newEvent.markDone();
                    }
                    listOfThings.add(newEvent);
                    break;
                default:
                    break;
                }
            }
            fileReader.close();
            return listOfThings;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    private static void appendToFile(String filePath, ArrayList<Task> listOfThings) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        for (int i = 0; i < listOfThings.size(); i++) {
           fw.write(i + 1 + ". " + listOfThings.get(i) + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        ArrayList<Task> listOfThings = new ArrayList<Task>();
        try {
            File saveFile = new File("src/duke.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + saveFile.getName());
            } else {
                listOfThings = Duke.readSave();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();
            String firstWord = "";
            if (msg.contains(" ")) {
                firstWord = msg.substring(0, msg.indexOf(" "));
            } else {
                firstWord = msg;
            }
            
            Type cmdType = Type.ERR;

            try { 
                cmdType = Type.valueOf(firstWord.toUpperCase());
            } catch (Exception ex) {
                System.err.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            
            Integer secondInt = 0;
            String byDate = "";
            String byTime = "";
            String fromWhen = "";
            String toWhen = "";

            switch (cmdType) {
            case MARK:
                try {
                    firstWord = msg.substring(0, msg.indexOf(" "));
                    secondInt = Integer
                            .parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
                    Task thisTask = listOfThings.get(secondInt - 1);
                    thisTask.markDone();
                    listOfThings.set(secondInt - 1, thisTask);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(thisTask);
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Please indicate a valid task!");
                }
                break;
            case UNMARK:
                try {
                    firstWord = msg.substring(0, msg.indexOf(" "));
                    secondInt = Integer
                            .parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
                    Task thisTask = listOfThings.get(secondInt - 1);
                    thisTask.markUndone();
                    listOfThings.set(secondInt - 1, thisTask);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(thisTask);
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Please indicate a valid task!");
                }
                break;
            case DELETE:
                try {
                    secondInt = Integer.parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
                    System.out.println("Noted. I've removed this task!");
                    System.out.println(listOfThings.get(secondInt - 1));
                    listOfThings.remove(secondInt - 1);
                    System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Please indicate a valid task to delete!");
                }
                break;
            case TODO:
                try {
                    firstWord = msg.substring(msg.indexOf(" ") + 1, msg.length());
                    Todo newTodo = new Todo(firstWord);
                    listOfThings.add(newTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Whoops! The description of a todo cannot be empty!");
                }
                
                break;
            case DEADLINE:
                try {
                    String[] splitted = msg.split("\\s+");
                    firstWord = splitted[1];
                    byDate = splitted[3];
                    byTime = splitted[4];
                    System.out.println(byDate);
                    System.out.println(byTime);
                    LocalDate d1 = LocalDate.parse(byDate);
                    LocalTime t1 = LocalTime.parse(byTime);
                    Deadline newDeadline = new Deadline(firstWord, d1, t1);
                    listOfThings.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Whoops! Please enter the deadline followed by its due date preceeded by a '/by'." +
                        "The date time format should be yyyy-mm-dd hh:mm");
                }
                break;
            case EVENT:
                try {
                    firstWord = msg.substring(msg.indexOf(" ") + 1, msg.indexOf("/from") - 1);
                    fromWhen = msg.substring(msg.indexOf("/from") + 6, msg.indexOf("/to") - 1);
                    toWhen = msg.substring(msg.indexOf("/to") + 4, msg.length());
                    Event newEvent = new Event(firstWord, fromWhen, toWhen);
                    listOfThings.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
                    try {
                        appendToFile("src/duke.txt", listOfThings);
                    } catch (Exception ex) {
                        System.err.println("Error! There is no save file!");
                    }
                } catch (Exception ex) {
                    System.err.println("Whoops! Please enter the event followed by its /from and /to timings.");
                }
                break;
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                echoScanner.close();
                loop = false;
                break;
            case LIST:
                for (int i = 0; i < listOfThings.size(); i++) {
                    System.out.println(i + 1 + ". " + listOfThings.get(i));
                }
                break;
            }
        }
    }
}
