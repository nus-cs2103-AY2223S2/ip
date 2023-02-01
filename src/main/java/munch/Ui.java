package munch;

import java.util.Scanner;

public class Ui {

    public Ui() {}

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void welcomeMessage() {
        System.out.println("Hello! I am Munch! :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________________");
    }

    public static void divider() {
        System.out.println("__________________________________");
    }

    public static void wrongDateFormatMessage() {
        System.out.println("Wrong format for date! [Format: dd/MM/yyyy HHmm]");
    }

    public static void addTaskMessage() {
        System.out.println("I've added this task for you!");
    }

    public static void exitMessage() {
        System.out.println("See ya champ! Enjoy your day!");
    }

    public static void listMessage() {
        System.out.println("Here are the task(s) in your list:");
    }

    public static void deleteMessage() {
        System.out.println("I've deleted this task for you!");
    }


}
