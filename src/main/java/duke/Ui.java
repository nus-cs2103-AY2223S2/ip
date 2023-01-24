package duke;

import java.util.Scanner;
public class Ui {
    static final String line="____________________________________________________________";

    public void hello() {
        System.out.println(line);
        System.out.println("Hello! I'm T-Rex. Roarrrrrrrrrrrrrr!");
        System.out.println("What do you need from me?");
        System.out.println(line);
        System.out.println();
    }

    public String readCMD() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printLine() {
        System.out.println(line);
    }
}
