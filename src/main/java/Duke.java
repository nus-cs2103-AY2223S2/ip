import java.util.Scanner;

public class Duke {
    public static Scanner instr = new Scanner(System.in);
    public static void Greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n(To terminate write 'bye')");
    }
    public static void Echo(String str) {
        if(str.equals("bye")){
            Exit();
        }
        else {
            System.out.println(str);
        }
    }
    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        while (instr.hasNextLine()) {
            String str = instr.nextLine();
            Echo(str);
        }

    }

}
