import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        String separator = "____________________________________________________________";
        String duke = "Duke: ";
        String you = "You: ";
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println(duke);
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println(you);
            String command = sc.nextLine();
            System.out.println(separator);
            System.out.println(duke);
            System.out.println(command);
            System.out.println(separator);
            if (command.equals("bye")) {
                isRunning = false;
            }
        }



    }
}
