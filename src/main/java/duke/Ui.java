package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome(){
        System.out.println("What's up! I'm duuk.What do you want?");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showLine() {
        System.out.println("________________________________________");
    }

    public void closeDuke() {
        this.sc.close();
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
