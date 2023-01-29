package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private final PrintStream out;
    private final Parser parser;

    public Ui(InputStream in, PrintStream out) {
        this.sc = new Scanner(in);
        this.out = out;
        parser = new Parser();
    }

    public Ui() {
        this(System.in, System.out);
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm duke.\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("======================================================================");
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void startProgram(String input, TaskList toDo, Storage storage) {
            parser.commandExecute(input, toDo, storage);

    }


}

