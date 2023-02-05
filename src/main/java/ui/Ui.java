package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Ui() {

    }

    public String getCommand() throws IOException{
        String str = br.readLine();
        return str;
    }

    public void showStartupMessage() {
        System.out.println("Hello! I'm Willy\nWhat can I do for you?");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

}
