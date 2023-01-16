import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        List<String> lst = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Im Zhizhou's Chatbot");
        System.out.println("What can I do for you?");
        String output = "";
        while (!(output = userInput.next()).equals("bye")) {
            if (output.equals("list")) {
                int count = 1;
                for (String s : lst) {
                    System.out.println("   " + count + ". " + s);
                    count++;
                }
            } else {
                lst.add(output);
                System.out.println("   added: " + output);
            }
        }
        System.out.println("   Bye. Hope to see you again soon!");
    }
}
