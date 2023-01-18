import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        boolean loop = true;

        String tabSpace = "    ";
        String bracket =  tabSpace + "_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);


        while (loop) {
        String input = inputScanner.nextLine();

        System.out.println(bracket);
        switch(input) {
            case "bye":
                System.out.println(tabSpace + "Bye! See you soon!");
                loop = false;
                break;
            default:
                System.out.println(tabSpace + input);
        }
        System.out.println(bracket);

        }

        inputScanner.close();
    }
}