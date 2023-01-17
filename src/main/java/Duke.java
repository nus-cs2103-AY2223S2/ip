import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        boolean continueRunning = true;
        String greeting = "______________________________________\n"
                + "Hey there buddy! I'm Duke\n"
                + "What can I do for you today?\n"
                + "______________________________________\n";
        System.out.print(greeting);
        Scanner scanner = new Scanner(System.in);
        while(continueRunning){
            String userInput = scanner.nextLine();
            String resultString = "";
            if (userInput.equals("bye")){
                resultString = "______________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "______________________________________\n";
                continueRunning = false;
            } else {
                resultString = "______________________________________\n"
                        + userInput + "\n"
                        + "______________________________________\n";
            }
            System.out.print(resultString);
        }
    }
}
