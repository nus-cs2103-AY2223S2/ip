package duke.ui;

import java.util.Scanner;

public class Ui {
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadingError(){
        System.out.println("can't load tasks");
    }

    public void showLine(){
        System.out.println("_______");
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello, I am Duke. \nWhat can I do for you?");
    }

    public void farewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}

