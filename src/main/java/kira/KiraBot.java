package kira;

import java.util.Scanner;

import kira.command.Command;
import kira.exception.KiraException;
import kira.storage.SaveLoad;
import kira.storage.TaskList;
import kira.ui.Parser;
import kira.ui.Ui;

/**
 * KiraBot is a CLI software that helps you manage a list
 * of task.
 *
 * @author  Eric Goh
 * @version 0.1
 * @since   2023-01-26
 */
public class KiraBot {

    private static final Ui UI = new Ui();
    private static final String FILEPATH = "./store.csv";
    private TaskList taskList;

    private void run() {
        UI.startMsg();

        try {
            this.taskList = new TaskList(SaveLoad.load(FILEPATH));
        } catch (KiraException e) {
            UI.errMsg(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            listenForCommand();
        }
    }

    private void listenForCommand() {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            try {
                Command command = Parser.parse(scanner.nextLine());
                isActive = command.execute(UI, taskList);
                SaveLoad.save(taskList.getList(), FILEPATH);
            } catch (KiraException e) {
                UI.errMsg(e.getMessage());
            }
        }
        scanner.close();
    }

    public String setup() {

        try {
            this.taskList = new TaskList(SaveLoad.load(FILEPATH));
        } catch (KiraException e) {
            UI.errMsg(e.getMessage());
            this.taskList = new TaskList();
        }

        return UI.getMessage();
    }

    /**
     * Returns welcome message for the GUI
     *
     * @return
     */
    public String startMsg() {
        UI.startMsg();
        return UI.getMessage();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(UI, taskList);
            SaveLoad.save(taskList.getList(), FILEPATH);
        } catch (KiraException e) {
            UI.errMsg(e.getMessage());
        }
        return UI.getMessage();
    }

    /**
     * Starts the software.
     *
     * @param args
     */
    public static void main(String[] args) {
        KiraBot bot = new KiraBot();
        bot.run();
    }
}
