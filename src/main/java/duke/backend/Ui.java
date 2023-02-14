package duke.backend;

import duke.tasks.Task;
import javafx.util.Pair;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;


class Ui {
    public static final String DIVIDER = "____________________________________________________________\n";

    private Scanner sc;
    private Parser parser;
    private TaskList taskManager;
    private boolean isEnded;

    public Ui(TaskList taskManager) {
        this.parser = new Parser(this, taskManager);
        this.taskManager = taskManager;
        this.sc = new Scanner(System.in);
        this.isEnded = false;
    }

    public void welcome() {
        //  Intro text
        System.out.println(DIVIDER + "Hello! I'm Duke");
        System.out.println("What can I do for you?\n" + DIVIDER);
    }
    public boolean readInput() throws IOException {
        //  Read next command
        if (sc.hasNext()) {
            String instr = sc.nextLine();
            parser.parse(instr);
            return isEnded;
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

    public void find(String searchKey) {
        ArrayList<Task> currentTasks = taskManager.getWholeList();
        ArrayList<Pair<Integer, Task>> searchResults = new ArrayList<>();
        for (int i = 0; i < currentTasks.size(); i++) {
            Task curr = currentTasks.get(i);
            if (curr.getDescription().contains(searchKey)) {
                searchResults.add(new Pair<Integer, Task>(i + 1, curr));
            }
        }
        System.out.println(DIVIDER);
        if (searchResults.size() != 0){
            System.out.println("I've found these matching tasks:\n");
            for (Pair p : searchResults) {
                System.out.println(p.getKey() + ". " + p.getValue());
            }
        } else {
            System.out.println("Sorry, I did not find any tasks matching your search keyword :-( .");
        }
        System.out.println(DIVIDER);
    }

    public void bye() {
        this.isEnded = true;
        taskManager.closeAndSave();
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }
}