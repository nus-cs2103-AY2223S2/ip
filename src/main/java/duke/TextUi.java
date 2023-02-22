package duke;

import duke.task.Task;

import java.util.Scanner;
public class TextUi {
    private static final String GREET_MESSAGE =
            "I am Panda, your personal task manager. How may I assist you today?";
    private static final String USER_INSTRUCTION = 
            "Here are what you can do : " + "\n" + '\n'
                    + "TODO  :  Adds a task to task list" + '\n' 
                    + "Example :  TODO (TASK_DESCRIPTION) " + '\n' + '\n'
                    + "LIST  :  List ALL tasks from task list" + '\n'
                    + "Example : LIST" + '\n' + '\n'
                    + "MARK/UNMARK  :  Mark/Unmark a task from the task list" + '\n'
                    + "Example : MARK/UNMARK (Task Number) " + '\n' + '\n'
                    + "EVENT :  Adds an event from and to with specific date and time" + '\n'
                    + "Example : EVENT (EVENT_DESCRIPTION) /(DATE_TIME_FROM) /(DATE_TIME_TO)" + '\n'
                    + "[DATE_TIME = YYYY-MM-DD]" + '\n' + '\n'
                    + "DEADLINE  :  Adds a task with a deadline at specific date and time" + '\n'
                    + "Example : DEADLINE (TASK_DESCRIPTION) /(DATE_TIME_BY) " + '\n'
                    + "[DATE_TIME = YYYY-MM-DD]" + '\n' + '\n'
                    + "DELETE  :  Remove task from task list" + '\n'
                    + "Example :  DELETE (TASK_NUMBER)" +'\n' + '\n'
                    + "BYE  :  Exits the app" + '\n'
                    + "Example : BYE";

    private static final String EXIT_MESSAGE =
            "It's great working with you! " + '\n'
                    + "See you again soon. BYE!!!";
    private static final String ADD_TASK_MESSAGE =
            "Nice! I've added this task to your task list :" ;
    private static final String MARK_TASK_MESSAGE =
            "Well Done!!! I've marked this task as done :";
    private static final String UNMARK_TASK_MESSAGE =
            "Alright. I've marked this task as undone :";
    private static final String DELETE_TASK_MESSAGE =
            "NOTED. I've removed this task from your task list :";
    private static final String FIND_TASK_MESSAGE =
            "Here are some similar tasks:";

    private static final String TOTAL_COUNT_START_MESSAGE =
            "Now you have ";

    private static final String TOTAL_COUNT_END_MESSAGE =
            " tasks in your task list.";

    private static final String TASKLIST_START_MESSAGE =
            "These are the tasks in your task list :";

    private static final String NO_SUCH_FILE_MESSAGE =
            "There is no existing file in your computer.";

    public String showGreetMessage() {
        return GREET_MESSAGE;
    }

    public String readUserCommand() {
        Scanner sc = new Scanner(System.in);
        String userCommand = sc.nextLine();
        return userCommand;
    }

    public String showTotalCountMessage(TaskList taskList) {
        return TOTAL_COUNT_START_MESSAGE
                + taskList.getList().size()
                + TOTAL_COUNT_END_MESSAGE;
    }

    public String showAddTaskMessage(Task task) {
        return ADD_TASK_MESSAGE + '\n'
                + task;
    }

    public String showMarkTaskMessage(Task task) {
        return MARK_TASK_MESSAGE + '\n'
                + task;
    }

    public String showUnmarkTaskMessage(Task task) {
        return UNMARK_TASK_MESSAGE + '\n'
                + task;
    }

    public String showDeleteTaskMessage(Task task) {
        return DELETE_TASK_MESSAGE + '\n'
                + task;
    }

    public String showTaskList(TaskList taskslist) {
        String taskList = TASKLIST_START_MESSAGE + '\n';
        for (Task task : taskslist.getList()) {
            taskList += "" + task + '\n';
        }
        taskList += "" + showTotalCountMessage(taskslist);
        return taskList;
    }

    public String showHelpMessage() {
        return USER_INSTRUCTION;
    }

    public String showFindMessage(TaskList tasksList, String keyword) {
        String foundTasks = FIND_TASK_MESSAGE + '\n';
        for (Task task: tasksList.getList()) {
            if (task.toString().contains(keyword)) {
                foundTasks += "" + task;
            }
        }
        return foundTasks;
    }

    public String showLoadingError() {
        return NO_SUCH_FILE_MESSAGE;
    }

    public String showErrorMessage(String m) {
        return m;
    }

    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

}
