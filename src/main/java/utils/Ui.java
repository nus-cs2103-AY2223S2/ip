package utils;

import java.util.Scanner;

public class Ui {
    private String welcomeString = "Hello, I'm a tree. How may I be of service?";
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(welcomeString);
    }

    public String readCommand() {
        return sc.nextLine();
    }


}

