package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~";
    private final Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showLoadingError() {
        // error in reading the file
        print("error");
    }
    public void print(String toBePrinted) {
        System.out.println(toBePrinted);
    }
    public void showError(String errorMessage) {
        print(errorMsg(errorMessage));
    }
    public String errorMsg(String error) {
        return String.format("☹ OOPS!!!\n%s :-(", error);
    }
    /**
     * Used in Level-1 to echo
     */
    public void echo(String cmd) {
        System.out.println(cmd);
    }
    public String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }
    public void greet() {
        print(String.format("Hello I am:\n%sWhat can I do for you?", ownName()));
    }
    public void showLine() {
        System.out.println(LINE);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }
    public void closeUi() {
        this.sc.close();
    }
}
