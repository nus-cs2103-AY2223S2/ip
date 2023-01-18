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
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.activate();
    }
    public void activate() {
        this.printLine();
        System.out.println(logo);
        this.printLine();
        this.greet();

        String i = this.getInput();
        while(!this.saysBye(i)) {
            if(this.saysList(i)) {
                this.printList();
            } else {
                this.addTask(i);
            }
            i = this.getInput();
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
    public String getInput() {
        return sc.nextLine();
    }
    public boolean saysBye(String s) {
        return s.equalsIgnoreCase("bye");
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
    public boolean saysList(String s) {
        return s.equalsIgnoreCase("list");
    }
    public void printList() {
        //this.printLine();
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
}
