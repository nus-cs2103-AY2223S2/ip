import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
        

        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();
            if (input == "bye") {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________");
                break;
            }
            switch (input) {
                default:
                    System.out.println(input);
                    break;

            }
            System.out.println("________________________________");

        }
        reader.close();

    }

}
