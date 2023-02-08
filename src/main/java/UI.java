import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UI {
    public static final String DIVIDER = "____________________________________________________________\n";

    private Scanner sc;
    private Parser parser;
    private TaskList taskManager;
    private boolean isEnded;

    public UI(TaskList taskManager) {
        this.parser = new Parser(this, taskManager);
        this.taskManager = taskManager;
        this.sc = new Scanner(System.in);
        this.isEnded = false;
    }

    public void welcome() {
        //        Intro text
        System.out.println(DIVIDER + "Hello! I'm Duke");
        System.out.println("What can I do for you?\n" + DIVIDER);
    }
    public boolean readInput() throws IOException {

//        Read next command
        if (sc.hasNext()) {
            String instr = sc.nextLine();
            parser.parse(instr);
//            if (next == null) {     //Either LIST, BYE, DELETE, MARK/UNMARK commands
//                return isEnded;
//            } else {
//                tasks.add(next);
//                return false;           //Return true iff command is BYE
//            }
            return false;
        }
        return true;
    }

    public void list() {
        ArrayList<Task> tasks = taskManager.getWholeList();
        System.out.println(DIVIDER + "Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
        System.out.println(DIVIDER);
    }

    public void bye() {
        this.isEnded = true;
        taskManager.closeAndSave();
//        saveManager.saveTasks(tasks);
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }
}