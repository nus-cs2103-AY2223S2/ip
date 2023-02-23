package Duke;

import java.util.Scanner;

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    public static void greet() {
        System.out.println("Hello my name is Thanos, my hobbies are helping people maintain their schedule and " +
                            "destroying galaxies.");
    }

    public static void giveOutput(String str) {
        System.out.println(str);
    }

    public static String getInput() {
       return sc.nextLine();
    }



}
