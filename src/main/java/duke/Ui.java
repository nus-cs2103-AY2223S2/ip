package duke;

import task.Task;

import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private static Scanner sc = new Scanner(System.in);


    public static String readCommand() {
        return sc.nextLine();
    }
    public void printBye() {
        System.out.println(DIVIDER + "Ah..... okkkk nehmind. GO. BYE. :)\n" + DIVIDER);
    }

    public static void printDefault(Task task) {
        System.out.println(DIVIDER + "Aite letsgetit you added:\n" + task.toString()  +
                "currently you have " + task.numberTask() + " tasks\n" + DIVIDER);
    }
    public static void printDelete(Task task) {
        System.out.println(DIVIDER + "Alright, deleted task:\n" + task
                + "\n" + task.numberTask() + " tasks left!\n" + DIVIDER);
    }
    public static void printDivider() {
        System.out.println(DIVIDER);
    }
    public static void printInit() {
        System.out.println(DIVIDER + "Welcome hooman!\nCome add some tasks today!\n" + DIVIDER);
    }

    public static void printListEmpty() {
        System.out.println(DIVIDER + "List is empty.......\n" + DIVIDER);
    }

    public static void printListPrompt() {
        System.out.println(DIVIDER + "Hurry up and finish these tasks:");
    }

    public static void printMark(Task task) {
        System.out.println(DIVIDER + "Congrats this has been d:\n"
            + task + "\n down, Leskooo!\n" + DIVIDER);
    }

    public static void printTodoError() {
        System.out.println("What do u need to do ah? u never write.");
    }
    public static void printUnmark(Task task) {
        System.out.println(DIVIDER + "Alright, new task:\n" + task
                + "\nWe can do dis!\n" + DIVIDER);
    }
    public static void printWelcomeBack() {
        String welcome = "Welcome back hooman!\n" +
                "Wat u want to do today?\n";
        System.out.println(DIVIDER + welcome + DIVIDER);
    }

    public void printWrongCommand() {
        System.out.println(DIVIDER + "I have no idea what is going on, try again?\n" + DIVIDER);
    }

    public static void printWrongNumber() {
        System.out.println("Number entered out of range, type the number again");
    }

}
