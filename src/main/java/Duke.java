import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<Task> taskArrayList = new ArrayList<>();
        final String markAsDone = "Nice! I've marked this task as done:";
        final String unMarkTask = "OK, I've marked this task as not done yet:";
        final String addedTask = "Got it, I've added this task:";

        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");

        readData(taskArrayList);
        while (true) {
            //File class retrieve
            String type = s.nextLine();
            String[] tokens = type.split("\\s+");
            if (tokens[0].equalsIgnoreCase("bye")) {
                //File class load
                saveData(taskArrayList);
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (tokens[0].equalsIgnoreCase("list")) {
                showList(taskArrayList);
            } else if (tokens[0].equalsIgnoreCase("mark")) {
                try {

                    int i = Integer.parseInt(tokens[1]) - 1;
                    if(i >= taskArrayList.size()) {
                        throw new Exception();
                    }
                    taskArrayList.get(i).mark();
                    saveData(taskArrayList);
                    System.out.println(markAsDone);
                    System.out.println(taskArrayList.get(i).toString());
                } catch(Exception e) {
                    System.out.println("Invalid value, there isn't this much tasks in the list :-( ");
                }

            } else if (tokens[0].equalsIgnoreCase("unmark")) {
                int i = Integer.parseInt(tokens[1]) - 1;
                try {
                    if(i >= taskArrayList.size()) {
                        throw new Exception();
                    }
                    taskArrayList.get(i).unmarked();
                    saveData(taskArrayList);
                    System.out.println(unMarkTask);
                    System.out.println(taskArrayList.get(i).toString());
                } catch(Exception e) {
                    System.out.println("Invalid value, there isn't this much tasks in the list :-( ");
                }

            } else if (tokens[0].equalsIgnoreCase("todo")) {
                try {
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" ")).trim();
                    Task newTask = new ToDo(des);
                    taskArrayList.add(newTask);
                    realTimeSave(taskArrayList);
                    System.out.println(addedTask);
                    System.out.println(newTask);
                    System.out.println("Now you have " + noOfTask(taskArrayList) + " task(s) in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty");
                }

            } else if (tokens[0].equalsIgnoreCase("deadline")) {
                try {
                    DateStringConverter converter = new DateStringConverter();
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" ")).trim();
                    String[] deadline = des.split("/by");
                    String[] timeExists = deadline[1].trim().split(" ");
                    if(timeExists.length > 1) {
                        String dateInString = timeExists[0];
                        String timeInString = timeExists[1];
                        LocalDate date = converter.convertDateInput(dateInString);
                        LocalTime time = converter.convertTimeInput(timeInString);
                        Deadline dead = new Deadline(deadline[0].trim(), date, time);
                        taskArrayList.add(dead);
                        realTimeSave(taskArrayList);
                        System.out.println(dead);
                    } else {
                        String dateInString = deadline[1].trim();
                        LocalDate date = converter.convertDateInput(dateInString);
                        Deadline dead = new Deadline(deadline[0].trim(), date);
                        taskArrayList.add(dead);
                        System.out.println(dead);
                    }
                    realTimeSave(taskArrayList);
                    System.out.println(addedTask);
                    System.out.println("Now you have " + noOfTask(taskArrayList) + " task(s) in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty and please enter in this date format (YYYY-MM-DD OR YYYY/MM/DD) or " +
                            "Day of the week (E.g. Monday, Tuesday, Wednesday).\n" +
                            "If time is provided, please key in this format 1200 after entering the date");
                }

            } else if (tokens[0].equalsIgnoreCase("event")) {
                try {
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" "));
                    String[] events = des.split("/");
                    Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
                    taskArrayList.add(e);
                    realTimeSave(taskArrayList);
                    System.out.println(addedTask);
                    System.out.println(e);
                    System.out.println("Now you have " + noOfTask(taskArrayList) + " task(s) in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty");
                }

            } else if(tokens[0].equalsIgnoreCase(("delete"))) {
                try {
                    if (!type.contains(" ")) {
                        throw new DukeException("OOPS!! Please indicate the task index to delete!");
                    }

                    String[] index = type.split(" ");
                    if(index[1].equalsIgnoreCase("all")) {
                        deleteAll(taskArrayList);
                        System.out.println("Noted: I've removed all tasks");
                    } else {
                        int deleteIndex = Integer.parseInt(index[1]);
                        if (deleteIndex > taskArrayList.size() || deleteIndex <= -1) {
                            throw new DukeException("OOPS!! The index requested to be deleted does not exist!");
                        } else {
                            System.out.println("Noted: I've removed this task");
                            Task whichTask = taskArrayList.get(deleteIndex - 1);
                            System.out.println(whichTask);
                            taskArrayList.remove(deleteIndex - 1);
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e.message);
                }
            } else if(type.contains("by")) {
                try {
                    String[] index = type.split("/");
                    DateStringConverter converter = new DateStringConverter();
                    LocalDate deadline = converter.convertDateInput(index[1].trim());
                    ArrayList<Deadline> deadlineTasks = checkDeadlineTask(taskArrayList, deadline);
                    System.out.println("Here is the list before this deadline: " + deadline);
                    for (int i = 0; i < deadlineTasks.size(); i++) {
                        System.out.println(deadlineTasks.get(i));
                    }
                } catch(Exception e) {
                    System.out.println("I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD");
                }

            }else {
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /* Help to create a file if does not exists, if exits read the data */
    public static void readData(ArrayList<Task> tasks) {
        try {
            File dukeTxt = new File("duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                FileReader fw = new FileReader(dukeTxt);
                BufferedReader dukeRead = new BufferedReader(fw);
                String line = dukeRead.readLine();
                while(line != null) {
                    if(line.contains("[D]")) {
                        tasks.add(new Deadline(line.replace("[D]", "")));
                    } else if(line.contains("[T]")) {
                        tasks.add(new ToDo(line.replace("[T]", "")));
                    } else if(line.contains("[E]")) {
                        tasks.add(new Event(line.replace("[E]", "")));
                    } else {
                        tasks.add(new Task(line));
                    }
                    line = dukeRead.readLine();
                }
                dukeRead.close();
            }
        }catch (IOException e) {
            System.out.println("Oh no!! It can't be read");
        }
    }

    public static void saveData(ArrayList<Task> tasks) {
        try {
            File dukeTxt = new File("duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            } else {
                dukeTxt.delete();
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            for (int i = 0; i < tasks.size(); i++) {
                dukeWrite.write(tasks.get(i).toString());
                dukeWrite.newLine();
            }//Read from the file
            dukeWrite.close();
        }catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public static void realTimeSave(ArrayList<Task> tasks) {
        try {
            File dukeTxt = new File("duke.txt");
            if (!dukeTxt.exists()) {
                dukeTxt.createNewFile();
            }
            FileWriter fw = new FileWriter(dukeTxt, true);
            BufferedWriter dukeWrite = new BufferedWriter(fw);
            int i = tasks.size() - 1;
            dukeWrite.write(tasks.get(i).toString());
            dukeWrite.newLine();
            dukeWrite.close();
        }catch (IOException e) {
            System.out.println("Oh no!!");
        }
    }

    public static void showList(ArrayList<Task> tasks) {
        for(int i=0; i < tasks.size(); i++) {
            int no = i + 1;
            System.out.println(no + "." + tasks.get(i));
        }
    }

    public static int noOfTask(ArrayList<Task> tasks) {
        return tasks.size();
    }

    public static void deleteAll(ArrayList<Task> tasks) throws IOException {
        tasks.clear();
        File dukeTxt = new File("duke.txt");
        dukeTxt.delete();
        dukeTxt.createNewFile();
    }

    public static ArrayList<Deadline> checkDeadlineTask(ArrayList<Task> tasks, LocalDate date) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline singleTask = ((Deadline) tasks.get(i));
                String[] s = singleTask.toString().split(":");
                DateStringConverter converter = new DateStringConverter();
                LocalDate dueDate = converter.convertDateInput(s[1].replace(")", "").trim());
                if (dueDate.isBefore(date)) {
                    deadlineTasks.add(singleTask);
                }
            }
        }
        return deadlineTasks;
    }
}

class DukeException extends Exception {
    String message;
    public DukeException(String message) {
        this.message = message;
    }
}

class Task {
    private String details;
    private boolean status;

    public Task(String details) {
        this.details = details;
        this.status = false;
    }

    public String isDone() {
        if(status) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public void mark() {
        status = true;
    }

    public void unmarked() {
        status = false;
    }

    @Override
    public String toString() {
        if(status && (details.contains("[ ]") || details.contains("[X]"))) {
            return details.replace("[ ]", "[X]");
        } else if(!status && (details.contains("[ ]") || details.contains("[X]"))){
            return details.replace("[X]", "[ ]");
        } else if(!details.contains("[ ]") || !details.contains("[X]")) {
            return isDone() + " " + this.details;
        } else {
            return this.details;
        }
    }
}

class ToDo extends Task {
    String icon = "[T]";

    public ToDo(String details) {
        super(details);

    }

    @Override
    public String toString() {
        return icon + super.toString();
    }
}

class Deadline extends Task {
    String icon = "[D]";
    LocalDate date;
    LocalTime time;

    public Deadline(String details, LocalDate date, LocalTime time) {
        super(details);
        this.date = date;
        this.time = time;
    }

    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    public Deadline(String details) {
        super(details);
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        if(time != null) {
            return icon + super.toString() + "(by: " + date + " " + time + ")";
        } else if(date != null) {
            return icon + super.toString() + "(by: " + date + ")";
        } else {
            return icon + super.toString();
        }
    }
}

class Event extends Task {
    String icon = "[E]";
    String from;
    String to;

    public Event(String details, String from, String to) {
        super(details);
        this.from = from.replace("from", "");
        this.to = to.replace("to", "");
    }

    public Event(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return icon + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}

class DateStringConverter {
    private final String[] DAY_OF_THE_WEEK = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

    public LocalDate convertDateInput(String input) {
        input.replace("/", "-");
        for(int i=0; i< DAY_OF_THE_WEEK.length; i++) {
            if(input.equalsIgnoreCase(DAY_OF_THE_WEEK[i])) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.with(dayOfWeek);
                return nextDate;
            }
        }
        return LocalDate.parse(input);
    }

    public LocalTime convertTimeInput(String input) {
        String hour = input.substring(0, 2);
        String mins = input.substring(2, 4);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(mins));
        return time;
    }
}