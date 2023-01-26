import java.io.*;
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
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" ")).trim();
                    String[] deadline = des.split("/by");
                    Deadline dead = new Deadline(deadline[0].trim(), deadline[1].trim());
                    taskArrayList.add(dead);
                    realTimeSave(taskArrayList);
                    System.out.println(addedTask);
                    System.out.println(dead);
                    System.out.println("Now you have " + noOfTask(taskArrayList) + " task(s) in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty");
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
                    int deleteIndex = Integer.parseInt(index[1]);
                    if (deleteIndex > taskArrayList.size() || deleteIndex <= -1) {
                        throw new DukeException("OOPS!! The index requested to be deleted does not exist!");
                    } else {
                        System.out.println("Noted: I've removed this task");
                        Task whichTask = taskArrayList.get(deleteIndex - 1);
                        System.out.println(whichTask);
                        taskArrayList.remove(deleteIndex -1 );
                    }
                } catch (DukeException e) {
                    System.out.println(e.message);
                }
            } else {
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
                    tasks.add(new Task(line));
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

    public String getDetails() {
        return details;
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
    String due;
    public Deadline(String details, String due) {
        super(details);
        this.due = due;
    }

    @Override
    public String toString() {
        return icon + super.toString() + "(by: " + due + ")";
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

    @Override
    public String toString() {
        return icon + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
