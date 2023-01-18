import java.util.Scanner;

public class Alfred {
    public static void main(String[] args) {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        Alfred.printLogo();
        Alfred.printIntro();

        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        while (true) {
            Alfred.echoCommand(command);
            command = sc.next();
            if (command.equals("bye")) {
                Alfred.saysBye();
                System.exit(1);
            }
        }


    }

    private static void echoCommand(String command) {
        Alfred.printLines();
        command = "    " + command;
        System.out.println(command);
        Alfred.printLines();

    }

    private static void saysBye() {
        String command = "Bye. Hope to see you again soon!";
        Alfred.echoCommand(command);
    }

    private static void printLogo() {
        System.out.println(" _____ __     ______ _____ ____ ___ ");
        System.out.println("|  -  |  |   |  ____|  _  |  __| _ \\     ");
        System.out.println("| | | |  |   | |___ | |_|_| |__|| | |  ");
        System.out.println("|  -  |  |___|  ___||  _ \\  |__||_| |");
        System.out.println("|_| |_| ____ |__|   |_| \\_|____|__ /   ");

    }

    private static void printIntro() {
        String intro = "Hello! I'm Alfred :>\n"
                + "How can I help you today?";
        Alfred.printLines();
        System.out.println(intro);
        Alfred.printLines();
    }

    private static void printLines() {
        System.out.println("    ____________________________________________________________");
    }
}
