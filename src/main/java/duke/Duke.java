package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        ui.takeCommands(this.tasks);
        storage.saveData(this.tasks);
    }
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") +"/data/duke.txt").run();
//
    }

    // handleCommand takes in command String s, current tasks, and current number of tasks
    // updates task array and returns taskCounter

}

