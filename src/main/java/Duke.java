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
        while (!this.stringMatch(i,"bye")) {
            if (this.stringMatch(i, "list")) {
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
                this.addTask(i);
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
            System.out.println(i + ". " + t.getStatusBox() +
                    t.getDescription());
        }
        this.printLine();
    }
    public void addTask(String i) {
        //this.printLine();
        Task t = new Task(i);
        System.out.println("Added: " + t.getDescription());
        this.storeTask(t);
        this.printLine();
    }
    public boolean stringMatch(String input, String given) {
        return input.equalsIgnoreCase(given);
    }
    public void getMarkDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" + "  " +
                t.getStatusBox() + t.getDescription());
        this.printLine();
    }
    public void getUnmarkDoneMessage(Task t) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + "  " +
                t.getStatusBox() + t.getDescription());
        this.printLine();
    }
}
