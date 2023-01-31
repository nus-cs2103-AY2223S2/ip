package duke.ui;

import java.util.Scanner;

public class Ui {

    Scanner ui;
    String response;

    public Ui() {
        this.ui = new Scanner(System.in);
    }

    public String nextCommand() {
        return this.ui.nextLine();
    }

    public boolean hasNextCommand() {
        return this.ui.hasNextLine();
    }

    public void response(String reply) {
        this.response = reply;
    }

    public void showResponse() {
        System.out.println(this.response);
    }
}
