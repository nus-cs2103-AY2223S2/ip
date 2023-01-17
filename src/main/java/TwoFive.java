import java.util.Scanner;

public class TwoFive {
    public static void main(String[] args) {
        String logo = "  _______            ______ _\n"
                    + " |__   __|          |  ____(_)\n"
                    + "    | |_      _____ | |__   ___   _____\n"
                    + "    | \\ \\ /\\ / / _ \\|  __| | \\ \\ / / _ \\\n"
                    + "    | |\\ V  V / (_) | |    | |\\ V /  __/\n"
                    + "    |_| \\_/\\_/ \\___/|_|    |_| \\_/ \\___|\n";

                System.out.println("Hello from\n" + logo);

        String divider = "____________________________________________________________";

        //Greets users
        System.out.println(divider);
        System.out.println("In case you're still not clear, I'm TwoFive!");
        System.out.println("I'm your personal assistant chatbot");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        //Reads input from user
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();

        //Exits when user types bye
        while (!input.equals("bye")) {
            //Echos input from user
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
            input = inputScanner.nextLine();
        }
        System.out.println(divider);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
