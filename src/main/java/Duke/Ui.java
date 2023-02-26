package Duke;

import java.util.Scanner;

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    /**
     * greets with welcome message when the program starts
     */
    public static void greet() {
        System.out.println("Hello my name is Thanos, my hobbies are helping people maintain their schedule and " +
                            "destroying galaxies.");
    }

    /**
     * takes in string and gives output.
     * @param str string to be printed
     */
    public static void giveOutput(String str) {
        System.out.println(str);
    }

    /**
     * scans for input
     * @return returns input string
     */
    public static String getInput() {
       return sc.nextLine();
    }



}
