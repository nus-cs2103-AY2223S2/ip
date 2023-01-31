package duke.ui;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void startUp() {
        System.out.println("Whats good its CHADGpt");
        System.out.println("Call me for???");
        System.out.println("now i will tell u how to enter stuff so u better listen");
        System.out.println("eg1 : todo stuff");
        System.out.println("eg2 : deadline <name>/<time in YYYY-MM-DD");
        System.out.println("eg3 : event <name>/<starttime>/<endtime>\n");
    }

    public void close() {
        sc.close();
    }

    public String readLine() {
        return sc.nextLine();
    }


}
