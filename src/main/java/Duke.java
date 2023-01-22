import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String greeting = "Hello! I'm Alpha Beast What can I do for you?";
        greeting(greeting);
        //memory store = new memory();
        TaskManager manager = new TaskManager();


        loop:
        while (true){
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            String without_key = input.replace(tokens[0], "");

            switch (tokens[0]) {
                case "bye":
                    echo(input);
                    break loop;

                case "list":
                    manager.displayAll();
                    break;

                case "mark":
                    manager.mark(Integer.parseInt(tokens[1]) - 1);
                    break;

                case "unmark":
                    manager.unmark(Integer.parseInt(tokens[1]) - 1);
                    break;


                case "todo": ToDo todo = new ToDo(without_key, false);
                        manager.add(todo);
                    break;

                case "deadline":
                        Deadlines deadlines = new Deadlines(without_key, false);
                        manager.add(deadlines);
                    break;

                case "event":
                    Events events = new Events(without_key, false);
                        manager.add(events);
                    break;

                case "delete": manager.delete(Integer.parseInt(tokens[1]) - 1);
                break;

                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

            }


        }
    }

    static void greeting(String message) {
        System.out.println(message);
    }
    static void echo(String input) {
        if (input.equals("bye"))
            System.out.println("Bye. Hope to see you again soon!\n");
        else
            System.out.println(input);
    }
}

class memory {

    static int slots = 0;
    ArrayList<String> store;
    ArrayList<Boolean> checkBox;
    int size;

    memory() {
        store = new ArrayList<>(100);
        checkBox = new ArrayList<>(100);
        size = 100;
        slots++;
    }

    memory(int size) {
        store = new ArrayList<>(size);
        checkBox = new ArrayList<>(size);
        slots++;
    }

    void write(String input) {
        store.add(input);
        checkBox.add(false);
        System.out.println("added: " + input);

    }

    void read() {
        for (int x = 0; x < store.size(); x++)
            System.out.println(x + 1 + ". " + store.get(x));

    }

    void readBoth() {
        for (int x = 0; x < store.size(); x++) {
            if (checkBox.get(x))
                System.out.println(x + 1 + ". " + "[X] " + store.get(x));
            else
                System.out.println(x + 1 + ". " + "[] " + store.get(x));
        }
    }

    void mark(int number) {
        checkBox.set(number, true);
        System.out.println("Nice! I've marked this task as done:\n" +
                "[X] " + store.get(number));
    }

    void unmark(int number) {
        checkBox.set(number, false);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "[] " + store.get(number));
    }


}

abstract class Task {
    static final String add = "Got it. I've added this task:\n";
    static final String mark = "Nice! I've marked this task as done:\n";
    static final String unmark = "OK, I've marked this task as not done yet:\n";
    static final String displaylist = "Here are the tasks in your list\n";
    static final String delete = "Noted. I've removed this task:\n";
    String task_name;
    String message_add;
    String message_marked;
    String message_unmarked;
    String message_display;
    String message_delete;
    boolean done;

    Task(String name, boolean done) {
        this.task_name = name;
        this.done = done;
        this.message_add = "";
        this.message_marked = "";
        this.message_unmarked = "";
        this.message_delete = "";
        //this.message_display = done ? "[X] " : "[ ] ";
    }

    abstract void add();

    abstract void marked();

    abstract void unmarked();

    abstract void display();

    abstract void delete();
}

class ToDo extends Task {
    ToDo(String name, boolean done) {
        super(name, done);
    }

    @Override
    void add() {
        message_add = Task.add + "  [T][ ] " + task_name;
    }

    @Override
    void display() {
        if (done) {
            message_display = "[T][X]" + task_name;
        } else {
            message_display = "[T][ ]" + task_name;
        }

    }

    @Override
    void delete() {
        if(done)
            message_delete = Task.delete + "  [T][X] " + task_name;
        else
            message_delete = Task.delete + "  [T][ ] " + task_name;
    }

    @Override
    void marked() {
        message_marked = Task.mark + "  [T][X] " + task_name;
        done = true;
    }

    @Override
    void unmarked() {
        message_unmarked = Task.unmark + " [T][ ] " + task_name;
        done = false;
    }

}

class Deadlines extends Task {

    String endDate;

    Deadlines(String name, boolean done) {
        super(name, done);
        extract();
    }

    void extract() {
        try {
            String[] tokens = task_name.split("/");
            String[] date = tokens[1].split(" ");
            task_name = tokens[0];
            StringBuilder temp = new StringBuilder("(" + date[0] + ": ");
            if (date.length > 3) {
                for (int x = 1; x < date.length; x++) {
                    temp.append(date[x]).append(" ");
                }
                temp.append(")");
            }
            if (date.length > 3) {
                endDate = temp.toString();
            } else {
                endDate = date.length == 2 ? "(" + date[0] + ": " + date[1] + ")"
                        : "(" + date[0] + ": " + date[1] + " " + date[2] + ")";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }

    }


    @Override
    void add() {
        message_add = Task.add + "  [D][ ] " + task_name + endDate;
    }

    @Override
    void display() {
        if (done)
            message_display = "[D][X] " + task_name + endDate;
        else
            message_display = "[D][ ] " + task_name + endDate;
    }

    @Override
    void delete() {
        if(done)
            message_delete = Task.delete + "  [D][X] " + task_name + endDate;
        else
            message_delete = Task.delete + "  [D][ ] " + task_name + endDate;
    }

    @Override
    void marked() {
        message_marked = Task.mark + "  [D][X] " + task_name + endDate;
        done = true;
    }

    @Override
    void unmarked() {
        message_unmarked = Task.unmark + "  [D][ ] " + task_name + endDate;
        done = false;
    }
}

class Events extends Task {

    String start;
    String end;

    Events(String name, boolean done) {
        super(name, done);
        extract();
    }

    void extract() {
        try {
            String[] tokens = task_name.split("/");
            String[] startdate = tokens[1].split(" ");
            String[] enddate = tokens[2].split(" ");
            task_name = tokens[0];
            start = startdate.length == 3 ? "(" + startdate[0] + ": " + startdate[1] + " " + startdate[2] + " "
                    : "(" + startdate[0] + ": " + startdate[1] + " " + startdate[2] + " " + startdate[3] + " ";
            end = enddate[0] + ": " + enddate[1] + ")";
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }
    }


    @Override
    void add() {
        message_add = Task.add + "  [E][ ] " + task_name + start + end;
    }

    @Override
    void display() {
        if (done)
            message_display = "[E][X] " + task_name + start + end;
        else
            message_display = "[E][ ] " + task_name + start + end;
    }

    @Override
    void delete() {
        if(done)
            message_delete = Task.delete + "  [E][X] " + task_name + start + end;
        else
            message_delete = Task.delete + "  [D][ ] " + task_name + start + end;
    }

    @Override
    void marked() {
        message_marked = Task.mark + "  [E][X] " + task_name + start + end;
        done = true;
    }

    @Override
    void unmarked() {
        message_unmarked = Task.unmark + "  [E][ ] " + task_name + start + end;
        done = false;
    }
}

class TaskManager {
    ArrayList<Task> ListOfTasks;

    TaskManager() {
        //default size
        ListOfTasks = new ArrayList<>(100);
    }

    void add(Task input) {
        try{
        if(input.task_name.equals("") || input.task_name.equals(" ")){
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        } else {
        ListOfTasks.add(input);
        input.add();
        System.out.println(input.message_add + "\n Now you have " + ListOfTasks.size() + " tasks in the list");}

        }
        catch (DukeException e){
            if(input instanceof ToDo )
            System.out.println("OOPS!!! The description of a todo cannot be empty.\n");
            else if(input instanceof Deadlines)
                System.out.println("OOPS!!! The description of a deadline cannot be empty.\n");
            else
                System.out.println("OOPS!!! The description of a event cannot be empty.\n");
        }
    }

    void displayAll() {
        System.out.println(Task.displaylist);
        for (int x = 0; x < ListOfTasks.size(); x++) {
            ListOfTasks.get(x).display();
            System.out.println(x + 1 + ". " + ListOfTasks.get(x).message_display);
        }
    }

    void mark(int index) {
        try{
        Task temp = ListOfTasks.get(index);
        temp.marked();
        System.out.println(temp.message_marked);}
        catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }

    void unmark(int index) {
        try {
            Task temp = ListOfTasks.get(index);
            temp.unmarked();
            System.out.println(temp.message_unmarked);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }
    void delete(int index){
        try {
            Task temp = ListOfTasks.get(index);
            temp.delete();
            ListOfTasks.remove(index);
            System.out.println(temp.message_add + "\n Now you have " + ListOfTasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Index");
        }
    }

}

class DukeException extends Exception {
    DukeException(String errorMessage) {
        super(errorMessage);
    }
}