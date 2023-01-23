import java.util.Scanner;

import kira.exception.KiraException;
import kira.storage.SaveLoad;
import kira.storage.TaskList;
import kira.task.Task;
import kira.ui.Parser;
import kira.ui.Ui;

public class KiraBot {

    private final Ui UI = new Ui();
    private TaskList taskList;
    private final String FILEPATH = "./store.csv";

    private void run() {
        UI.start();

        try {
            this.taskList = new TaskList(SaveLoad.load(FILEPATH));
        } catch (KiraException e) {
            UI.errMsg(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            listenForCommand();
            UI.end();
        }
    }

    private void listenForCommand() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            try {
                Parser commandString = new Parser(sc.nextLine());
                switch(commandString.command) {
                case BYE:
                    isActive = false;
                    break;
                case DEADLINE:
                case EVENT:
                case TODO:
                    Task task = commandString.parseOutputTask();
                    this.taskList.store(task);
                    UI.storeTaskMsg(task, this.taskList.getTotal());
                    break;
                case DELETE:
                    UI.deleteMsg(this.taskList.delete(commandString.getIndex()));
                    break;
                case LIST:
                    UI.listMsg(this.taskList.getList());
                    break;
                case TODAY:
                    UI.todayMsg(this.taskList.findToday());
                    break;
                case MARK:
                    UI.markMsg(this.taskList.mark(commandString.getIndex()));
                    break;
                case UNMARK:
                    UI.unmarkMsg(this.taskList.mark(commandString.getIndex()));
                    break;
                }
                SaveLoad.save(taskList.getList(), FILEPATH);
            } catch (KiraException e) {
                UI.errMsg(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        KiraBot bot = new KiraBot();
        bot.run();
    }
}
