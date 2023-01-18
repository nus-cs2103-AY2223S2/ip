import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke\n"
                + "What can I do for you?");
        String[] taskArray = new String[100];
        int arrayIndex = 0;
        while(true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for(int i = 0; i < arrayIndex; i++) {
                        int listNumber = i + 1;
                        System.out.println(listNumber + ". " + taskArray[i]);
                    }
                    break;
                default:
                    taskArray[arrayIndex] = input;
                    System.out.println("added: " + input);
                    arrayIndex++;
            }
        }
    }
}
