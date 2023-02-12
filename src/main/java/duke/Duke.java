package duke;

import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) throws Exception {

        Storage database = new Storage("./data", "duke.txt");
        TaskList tasklist = new TaskList(database.load());
        Ui ui = new Ui();

        ui.showLogo();
        ui.showLine();
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            tasklist.parser(userInput);
            ui.showLine();
            database.save(tasklist);
            userInput = myObj.nextLine();
        }
        database.save(tasklist);
        ui.showBye();
    }
}
