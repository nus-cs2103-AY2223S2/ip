package duke.ui;

import java.util.Scanner;

public class Ui {
    private String welcomeMessage = "Hello from\n" + 
    " ____        _        \n" +
    "|  _ \\ _   _| | _____ \n" +
    "| | | | | | | |/ / _ \\\n" +
    "| |_| | |_| |   <  __/\n" +
    "|____/ \\__,_|_|\\_\\___|\n";

    public void welcome() {
        System.out.println(welcomeMessage);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void showLoadingError() {
        this.printToFormat("Sorry, default tasks could not be loaded, starting a fresh task list");
    }
    
    public void printToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }
}
