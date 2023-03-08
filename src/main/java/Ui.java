import java.util.Scanner;

public class Ui {
    private Scanner inputScanner;
    private String currInput;

    public Ui() {
        this.inputScanner = new Scanner(System.in);

    }

    public String readCommand() {
        this.currInput = inputScanner.nextLine();
        return currInput;
    }

    public boolean hasNextCommand() {
        return inputScanner.hasNextLine();
    }

    public void showWelcome() {
        System.out.println("Hello Brother\nWelcome to Brother Bot\nWhats up what can I do for you mi amigo");
    }

    // to deal with exceptions
    public void showError(DukeException x) {
        System.out.println(x.getMessage());
    }

    public void showLoadingError() {
        System.out.println("An error occurred while creating the new file: data.txt");
    }


    public void showFarewell() {
        System.out.println("ok see you brother all love no cringe!");
    }
}
