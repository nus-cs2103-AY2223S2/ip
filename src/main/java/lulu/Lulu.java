package lulu;

import lulu.command.Parser;
import lulu.command.Command;
import lulu.command.LoadCommand;

import lulu.exception.LuluException;

import java.util.Scanner;

public class Lulu {
    private static String LINE = "____________________________________________________________";
    private static final String NAME = "./data/lulu.txt";
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    public Lulu(String filePath) {
        this.ui = new UI();
        //this.list = list;
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
    }

    public void run() {
        ui.showGreetText();
        if (storage.isSavePresent()) {
            Command c = new LoadCommand();
            c.execute(tasks, ui, storage);
        }

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LuluException e) {
                ui.showLine();
                System.out.println(e);
                ui.showLine();
            } catch (IndexOutOfBoundsException e) {
                ui.showLine();
                ui.showOutOfBounds();
                ui.showLine();
            }
        }
    }
}
