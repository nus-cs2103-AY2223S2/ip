package seedu.duke;

import java.util.Scanner;

public class Ui {

    public Ui() {};

    Scanner sc = new Scanner(System.in);

    public String getNextCommand() {
        return sc.nextLine();
    }
}
