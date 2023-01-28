package Ava;


import Ava.Commands.AvaCommand;
import Ava.Exceptions.AvaException;

import java.util.Scanner;
public class Ava {
    public static enum TASK_TYPE {
        TODO,
        DEADLINE,
        EVENT
    };
    private static TaskList tasks = new TaskList();
    private static Storage store = new Storage();
    private static Parser parser = new Parser();

    public Ava() {
        Ui.displayIntro();
        Scanner myObj = new Scanner(System.in);
        boolean running = true;

        try {
            this.tasks.retreiveStorage(store);
        } catch (AvaException e){
            // Trouble Retrieving the storage
            Ui.displayOutput(e.getMessage());
            running = false;
        }

        while (running) {
            try {
                Ui.ask();
                String input = myObj.nextLine();
                AvaCommand c = Ava.parser.parse(input);
                running = c.run(Ava.tasks,Ava.store);
                if (running) {
                    String output = c.output(Ui.getFormatSpace());
                    Ui.displayOutput(output);
                }
            } catch (AvaException e) {
                Ui.displayOutput(e.getMessage());
            }
        }
        Ui.displayExit();
    }
    public static void main(String[] args) {
        Ava a = new Ava();
    }
}
