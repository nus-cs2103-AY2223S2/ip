package duke;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.showLine();
        System.out.println("Hiii Im\n" + logo);
        System.out.println("What can I do for you hmm?");
        this.showLine();
    }

    public void showLine() {
        String line = "-------------------------------";
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("OHOH the input cannot make it!");
    }

    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();
        String inp;
        inp = br.readLine();
        return inp;
    }

}
