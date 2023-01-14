import java.util.Scanner;

public class Dude {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "  _____          __     \n" +
                " |  __ \\ __ __   | |  ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        System.out.println("Yo! I'm dude");
        System.out.println("What you want me do for you?\n");

        while(true) {
            String command = sc.next();
            getCommands(command);
            if (command.equals("bye")) {
                break;
            }
        }

    }

    public static void getCommands(String command) {
        switch(command) {
            case "list":
                System.out.println("list");
                break;
            case "blah":
                System.out.println("blah");
                break;
            case "bye":
                System.out.println("Ciaos. See you next time.");
                break;
        }
    }
}
