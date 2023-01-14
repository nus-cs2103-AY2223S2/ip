import java.util.Scanner;

public class Dude {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        getIntro();

        while(true) {
            String command = sc.next();
            getCommands(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void getIntro() {
        String logo = "  _____          __     \n" +
                " |  __ \\ __ __   | |  ___  \n" +
                " | |  | | | | |/ _` |/ _ \\\n" +
                " | |__| | |_| | (_| |  __/\n" +
                " |_____/ \\__,_|\\__,_|\\___|\n";

        System.out.println(logo);
        System.out.println("Yo! I'm dude");
        System.out.println("What you want me do for you?\n");
    }

    public static void getCommands(String command) {
        switch(command) {
            case "list":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tlist");
                System.out.println(" ____________________________________________________________\n");
                break;
            case "blah":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tblah");
                System.out.println(" ____________________________________________________________\n");
                break;
            case "bye":
                System.out.println(" ____________________________________________________________");
                System.out.println("\tCiaos! See you next time.");
                System.out.println(" ____________________________________________________________\n");
                break;
        }
    }
}
