import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private final static String logo =
                  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }
    public void activate() {
        this.printLine();
        System.out.println(logo);
        this.printLine();
        this.greet();

        String i = sc.nextLine();
        while (!i.equalsIgnoreCase("bye")) {
            if (i.equalsIgnoreCase("list")) {
                this.printList();
            } else if(i.toLowerCase().startsWith("mark")) {
                int num = Integer.parseInt(i.split(" ")[1]);
                Task t = this.tasks.get(num-1);
                t.markDone();
                getMarkDoneMessage(t);
            } else if(i.toLowerCase().startsWith("unmark")) {
                int num = Integer.parseInt(i.split(" ")[1]);
                Task t = this.tasks.get(num-1);
                t.unmarkDone();
                getUnmarkDoneMessage(t);
            }
            else {
                if(i.toLowerCase().startsWith("todo")) {
                    this.addToDo(i);
                }
                if(i.toLowerCase().startsWith("deadline")) {
                    this.addDeadline(i);
                }
                if(i.toLowerCase().startsWith("event")) {
                    this.addEvent(i);
                }
            }
            i = sc.nextLine();
        }
        this.terminate();
    }
    public void greet() {
        System.out.println("Hello! I'm Duke." + "\nWhat can I do for you?");
    }
    public void echoText(String s) {
        this.printLine();
        System.out.println(s);
        this.printLine();
    }
    public void terminate() {
        //this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
        sc.close();
    }
    public void printLine() {
        System.out.println("_______________________________________________________");
    }
    public void storeTask(Task t) {
        this.tasks.add(t);
    }
    public void printList() {
        //this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= this.tasks.size(); i++) {
            Task t = this.tasks.get(i-1);
            System.out.println(i + ". " + t.toString());
        }
        this.printLine();
    }
    public void addToDo(String i) {
        //this.printLine();
        ToDo t = new ToDo(i.replace("todo ", ""));
        this.storeTask(t);
        this.addTaskMessage(t);
    }
    public void addDeadline(String i) {
        String[] contents = i.split(" /by ");
        Deadline d = new Deadline(contents[0].replace("deadline ", ""), contents[1]);
        this.storeTask(d);
        this.addTaskMessage(d);
    }
    public void addEvent(String i) {
        String[] contents = i.split(" /from ");
        String[] fromTo = contents[1].split(" /to ");
        Event e = new Event(contents[0].replace("event ", ""), fromTo[0], fromTo[1]);
        this.storeTask(e);
        this.addTaskMessage(e);
    }
    public boolean stringMatch(String input, String given) {
        return input.equalsIgnoreCase(given);
    }
    public void getMarkDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " + t.toString());
        this.printLine();
    }
    public void addTaskMessage(Task t) {
        System.out.println("Got it. I've added this task:\n  " + t.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        this.printLine();
    }
    public void getUnmarkDoneMessage(Task t) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + "  " + t.toString());
        this.printLine();
    }
}
