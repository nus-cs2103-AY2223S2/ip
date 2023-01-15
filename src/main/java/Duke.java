import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
            ArrayList<String> listOfTasks = new ArrayList<String>(100);
            final String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            final String separator = "\t____________________________________________________________";
            Scanner sc = new Scanner(System.in);
            System.out.println(separator
                    + "\n\t Hello! I'm Duke\n"
                    + "\t What can I do for you?\n"
                    + separator);
            boolean isExit = true;
        while (isExit) {
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    System.out.println(separator
                            + "\n\t Bye. Hope to see you again soon!\n"
                            + separator);
                    isExit = false;
                    break;

                case "list":
                    int i = 1;
                    System.out.println(separator);
                    for(String tasks : listOfTasks) {
                        System.out.println("\t"
                                + i
                                + ". "
                                + tasks
                                );
                        i++;
                    }
                    System.out.println(separator);
                    continue;
                default:
                    System.out.println(separator
                            + "\n\t"
                            + "added: "
                            + command
                            + "\n"
                            + separator);
                    listOfTasks.add(command);
            }

        }
    }
}
