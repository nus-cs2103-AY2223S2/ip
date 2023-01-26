package duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeExceptions {
        Ui.intro();
        Storage.createDataDir();
        TaskList tasks = new TaskList(Storage.load());
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hey! This is duke.Duke at your service!");
        while(myObj.hasNext()) {
            String input = myObj.nextLine();
            tasks.handleInput(input);
        }
    }
}
