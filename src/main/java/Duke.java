import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args)
    {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        ArrayList<String> userInputs = new ArrayList<String>();
        while (!userInput.equals("bye"))
        {
            if (userInput.equals("list"))
            {
                for (int i = 1; i <= userInputs.size(); i++)
                {
                    System.out.println(i + ". " + userInputs.get(i - 1));
                }
            }
            else
            {
                userInputs.add(userInput);
                System.out.println("added: " + userInput);
            }
            userInput = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
