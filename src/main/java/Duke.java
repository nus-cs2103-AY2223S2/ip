import java.util.Scanner;
public class Duke {
    private final static String logo =
                  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private Scanner sc = new Scanner(System.in);
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
            this.echoText(i);
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
        this.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }
    public void printLine() {
        System.out.println("_______________________________________________________");
    }
}
