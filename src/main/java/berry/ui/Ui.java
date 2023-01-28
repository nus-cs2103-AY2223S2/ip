package berry.ui;

import berry.task.Task;
import berry.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    public static void showLine() {
        System.out.println("✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦");
    }

    public static String readCommand() {
        return sc.nextLine();
    }

    public static void showWelcome() {
        showLine();
        String logo =
                " _______  _______  ______    ______    __   __ \n" +
                        "|  _    ||       ||    _ |  |    _ |  |  | |  |\n" +
                        "| |_|   ||    ___||   | ||  |   | ||  |  |_|  |\n" +
                        "|       ||   |___ |   |_||_ |   |_||_ |       |\n" +
                        "|  _   | |    ___||    __  ||    __  ||_     _|\n" +
                        "| |_|   ||   |___ |   |  | ||   |  | |  |   |  \n" +
                        "|_______||_______||___|  |_||___|  |_|  |___|  ";
        System.out.println(logo + "\nHey there! I'm Berry the Bunny~ ૮ ˶ᵔ ᵕ ᵔ˶ ა\n"
                + "What are you looking to plan today?");
        showLine();
    }

    public static void showGoodbye() {
        System.out.println("Bye! I hope Berry was helpful to you ૮꒰˶• ༝ •˶꒱ა");
    }

    public static void showError(String message) {
        System.out.println(message);
    }

    public static void showListOfTasks(TaskList tasks) {
        ArrayList<Task> listOfTasks = tasks.getList();
        System.out.println("Here's what I have for you:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + ". " + listOfTasks.get(i).toString());
        }
    }

    public static void showUnmark() {
       System.out.println("Okay! I'll set the task as not done. ૮₍ ˃ ⤙ ˂ ₎ა");
    }

    public static void showMark() {
        System.out.println("Alright~ I'll set the task as done! ૮₍ ˶ᵔ ᵕ ᵔ˶ ₎ა");
    }

    public static void showAdd() {
        System.out.println("Done and ready to go~ I've added this task for ya!");
    }

    public static void showDelete() {
        System.out.println("Here you go! I've deleted this task for ya.");
    }

    public static void showLoadingError() {
        System.out.println("I can't seem to load the file ૮ ˶> _ <˶ ა");
    }
}
