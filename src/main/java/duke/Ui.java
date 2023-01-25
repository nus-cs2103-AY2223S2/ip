package duke;

import java.util.Scanner;

public class Ui {
    Scanner inputReader = new Scanner(System.in);
    String wrapTop = "________________________________\n";
    String wrapBottom = "\n________________________________";

    public String readInput() {
        return inputReader.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showMessage("Hello! I'm Duke What can I do for you?");
    }

    public void showMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
