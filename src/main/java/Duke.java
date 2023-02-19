import java.util.Scanner;

public class Duke {
    private TaskList data;
    private Ui ui;

    public Duke() {
        this.data = Storage.populateList();
    }

    public void run() {
        this.ui = new Ui(new Parser(data));
        this.ui.readInput();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings");
        Duke duke = new Duke();
        duke.run();
    }
}
