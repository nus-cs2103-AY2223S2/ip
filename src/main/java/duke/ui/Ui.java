package duke.ui;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke the Chatbot!\nHow may i help you today?\n");
    }

    public void showLine() {
        System.out.println("--------------------------------------------------------------------");
    }

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadingError(String e) {
        System.out.println(e);
    }
}
