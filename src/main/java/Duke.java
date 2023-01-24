import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Task> t = new ArrayList<>();
        final String markAsDone = "Nice! I've marked this task as done: ";
        final String unMarkTask = "OK, I've marked this task as not done yet: ";
        final String addedTask = "Got it, I've added this task: ";
        final String freeSpace = "----------------------------------------------";

        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");

        while (true) {
            String type = s.nextLine();
            String[] tokens = type.split("\\s+");
            if (tokens[0].equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(freeSpace);
                break;
            } else if (tokens[0].equalsIgnoreCase("list")) {
                showList(t);
                System.out.println(freeSpace);
            } else if (tokens[0].equalsIgnoreCase("mark")) {
                try {

                    int i = Integer.parseInt(tokens[1]) - 1;
                    if(i >= t.size()) {
                        throw new Exception();
                    }
                    t.get(i).mark();
                    System.out.println(markAsDone);
                    System.out.println(t.get(i).toString());
                    System.out.println(freeSpace);
                } catch(Exception e) {
                    System.out.println("Invalid value, there isn't this much tasks in the list :-( ");
                    System.out.println(freeSpace);
                }

            } else if (tokens[0].equalsIgnoreCase("unmark")) {
                int i = Integer.parseInt(tokens[1]) - 1;
                try {
                    if(i >= t.size()) {
                        throw new Exception();
                    }
                    t.get(i).unmarked();
                    System.out.println(unMarkTask);
                    System.out.println(t.get(i).toString());
                    System.out.println(freeSpace);
                } catch(Exception e) {
                    System.out.println("Invalid value, there isn't this much tasks in the list :-( ");
                    System.out.println(freeSpace);
                }

            } else if (tokens[0].equalsIgnoreCase("todo")) {
                try {
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" ")).trim();
                    Task newTask = new ToDo(des);
                    t.add(newTask);
                    System.out.println(addedTask);
                    System.out.println(newTask);
                    System.out.println("Now you have " + noOfTask(t) + " tasks in the list.");
                    System.out.println(freeSpace);
                } catch(Exception e) {
                    System.out.println("OOPS!!! The descripition of a todo cannot be empty");
                    System.out.println(freeSpace);
                }

            } else if (tokens[0].equalsIgnoreCase("deadline")) {
                try {
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" ")).replace(" ", "");
                    String[] deadline = des.split("/");
                    Deadline dead = new Deadline(deadline[0], deadline[1]);
                    t.add(dead);
                    System.out.println(addedTask);
                    System.out.println(dead);
                    System.out.println("Now you have " + noOfTask(t) + " tasks in the list.");
                    System.out.println(freeSpace);
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty");
                    System.out.println(freeSpace);
                }

            } else if (tokens[0].equalsIgnoreCase("event")) {
                try {
                    if(!type.contains(" ")) {
                        throw new Exception();
                    }
                    String des = type.substring(type.indexOf(" "));
                    String[] events = des.split("/");
                    Event e = new Event(events[0].trim(), events[1].trim(), events[2].trim());
                    t.add(e);
                    System.out.println(addedTask);
                    System.out.println(e);
                    System.out.println("Now you have " + noOfTask(t) + " tasks in the list.");
                    System.out.println(freeSpace);
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of an event cannot be empty");
                    System.out.println(freeSpace);
                }

            } else if(tokens[0].equalsIgnoreCase(("delete"))) {
                try {
                    if (!type.contains(" ")) {
                        throw new DukeException("OPPS!! Please indicate the task index to delete!");
                    }

                    String[] index = type.split(" ");
                    int deleteIndex = Integer.parseInt(index[1]);
                    if (deleteIndex > t.size() || deleteIndex <= -1) {
                        throw new DukeException("OPPS!! The index requested to be deleted does not exist!");
                    } else {
                        System.out.println("Noted: I've removed this task");
                        Task whichTask = t.get(deleteIndex - 1);
                        System.out.println(whichTask);
                        t.remove(deleteIndex -1 );
                    }
                } catch (DukeException e) {
                    System.out.println(e.message);
                }
            } else {
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                System.out.println(freeSpace);
            }
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
        return isDone() + " " + this.details;
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
