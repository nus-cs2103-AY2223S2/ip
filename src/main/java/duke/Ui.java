package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void start() {
        System.out.println("Hello! I'm Duke!\n" +
                "What can I do for you?");
        displayLine();    
    }

    public String[] readLine() throws IOException {
        System.out.print("Type your command: ");
        return br.readLine().strip().split(" ",2);
    }

    public void displayLine() {
        System.out.println("_________________________________________\n");
    }

    public void goodbye() {
        displayLine();
        System.out.println("Goodbye!");
    }
}
