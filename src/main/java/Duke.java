import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] storeCommands = new String[100];
        int numElem = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "How may I be of service to you? \nEnter your command:");
        String commandToEcho = sc.nextLine();
        while (!commandToEcho.equals("bye")) {
            if(commandToEcho.equals("list")) {
                for (int i = 0; i < numElem; i++) {
                    System.out.println(String.format("%d. %s",i+1,storeCommands[i]));
                }
            }
            else {
                storeCommands[numElem] = commandToEcho;
                numElem++;
                System.out.println("added: " + commandToEcho);
            }
            commandToEcho = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");




    }
}
