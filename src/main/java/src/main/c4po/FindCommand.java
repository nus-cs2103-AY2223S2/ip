package src.main.c4po;

import java.util.ArrayList;

public class FindCommand extends Command {

    private boolean isExit = false;

    private final ArrayList<String> keywordsSplit;

    /**
     * Instance of executable FindCommand. Takes an arraylist string of keywords,
     * instantiates a find command to find all tasks with those keywords
     */
    public FindCommand(ArrayList<String> keywords) {
        this.keywordsSplit = keywords;
    }

    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     *
     * @param tasks   are the list of tasks
     * @param ui      is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        Ui.showTasksFound();
        ArrayList<Task> tasksFound = new ArrayList<>();

        for ( String keyword: keywordsSplit ) {
            //Search for tasks
            Ui.print("keyword(s) typed: " + keyword);
            ArrayList<Task> foundTasks = tasks.findTask(keyword);
            if (!(foundTasks == null || foundTasks.isEmpty())) {
                tasksFound.addAll(foundTasks);
            }
        }
        if (tasksFound.isEmpty()) {
            Ui.showNoTasksFound();
        } else {
            TaskList tempTaskList = new TaskList(tasksFound);
            tempTaskList.printList(false);
        }

    }

    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     *
     * @param tasks          are the list of tasks
     * @param ui             is the instance of UI
     * @param storage        the instance of Storage which holds and writes to the data file
     * @param isStringOutput specifing this will allow users to access the String output method
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean isStringOutput) throws BotException {
        StringBuilder findTaskResponse = new StringBuilder();

        findTaskResponse.append(Ui.showTasksFound(true));
        ArrayList<Task> tasksFound = new ArrayList<>();

        for ( String keyword: keywordsSplit ) {
            //Search for tasks
            findTaskResponse.append("keyword(s) typed: ").append(keyword).append("\n");
            ArrayList<Task> foundTasks = tasks.findTask(keyword);
            if (!(foundTasks == null || foundTasks.isEmpty())) {
                tasksFound.addAll(foundTasks);
            }
        }
        if (tasksFound.isEmpty()) {
            Ui.showNoTasksFound(true);
        } else {
            TaskList tempTaskList = new TaskList(tasksFound);
            findTaskResponse.append(tempTaskList.printList(false, true));
        }

        return findTaskResponse.toString();
    }

    /**
     * Returns whether a command should cause bot to end interaction
     *
     * @return boolean to be used in the main loop
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
