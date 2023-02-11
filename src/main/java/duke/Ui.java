package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome(){
        System.out.println("Hello! I'm duke.Duke What can I do for you?");
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

//    public void displayLoadingErrorMessage(){
//        System.out.println("oh no Loading Error!");
//    }
    public void showLine() {
        System.out.println("_______");
    }

    public void closeDuke() {
        this.sc.close();
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
