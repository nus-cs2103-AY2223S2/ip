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
    private ArrayList<String> inputs = new ArrayList<>(100);
    private ArrayList<Boolean> isDone = new ArrayList<>(100);
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
            } else {
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
    public void storeInput(String i) {
        this.inputs.add(i);
    }
    public void printList() {
        //this.printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= this.inputs.size(); i++) {
            System.out.println(i + ". " + this.inputs.get(i-1));
        }
        this.printLine();
    }
    public void addTask(String i) {
        //this.printLine();
        System.out.println("Added: " + i);
        this.storeInput(i);
        this.printLine();
    }
    public boolean stringMatch(String input, String given) {
        return input.equalsIgnoreCase(given);
    }
}
