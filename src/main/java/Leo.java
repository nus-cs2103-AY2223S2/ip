import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

public class Leo {
    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Argentina, I'm Leo!\n" + "What can I do for you?");
        new Leo().start();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine(); // reads in command fed by user

        while (!cmd.equals("bye")) {
            printPaddedText(cmd);
            cmd = sc.nextLine();
        }
        printGoodbye();
        sc.close();
    }

    private static void printPaddedText(String cmd) {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("\t" + cmd);
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private static void printGoodbye() {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("It was nice talking, see you soon!");
    }
}
