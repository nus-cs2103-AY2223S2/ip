import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String MESSAGE_START = "Hello! I'm Duke\n What can I do for you?";
        final String MESSAGE_END= "Goodbye";
        final String MESSAGE_TERMINATE = "bye";
        final String MESSAGE_LIST = "list";
        List<String> tasks = new ArrayList<String>();

        System.out.println("Hello from\n" + logo);
        System.out.println(MESSAGE_START);
        Scanner input = new Scanner(System.in);
        while(true){
           String nextLine = input.nextLine();
            switch(nextLine){
                case MESSAGE_LIST:
                    int i = 0;
                    for(String entry : tasks){
                        i += 1;
                        System.out.println(i + ": " + entry);

                    }

                    break;
                case MESSAGE_TERMINATE:
                    System.out.println(MESSAGE_END);
                    return;
                default:
                    tasks.add(nextLine);
                    System.out.println("added: " + nextLine);

            }

        }
    }
}
