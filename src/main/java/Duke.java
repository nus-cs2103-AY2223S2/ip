import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        
        while (sc.hasNext()) {
            String output = sc.nextLine();
            String lineBreak = "***---***---***---***---***---***---***";
            if (output.equals("bye")) {
                System.out.println(lineBreak + "\n" + "    GoodBye, have a nice day!");
                break;
            } else {
                System.out.println(lineBreak + "\n" + "    " + output);
            }
        }
    }
}
