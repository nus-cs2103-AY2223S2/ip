package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Response class that handles what to display after each command
 * is executed.
 */
public class Response {

    private Scanner scanner;
    private String horizontalLine = "************************";
    private TaskList taskList;

    private String textResponse;

    public Response(TaskList taskList) {
        scanner = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Displays the start screen text of Duke
     */
    public void showIntro() {
        textResponse =  "\nHELLO! I'M DUKE!" + "\n" +
                "HOW CAN I HELP?" + "\n" + "NOTE! USE THIS FORMAT FOR DATES: dd/MM/yyyy HH:mm";
    }

    /**
     * Displays the acknowledgement of adding a task
     * and also shows the number of tasks created
     */
    public void showAddTask(Task task) {
        textResponse = "OK. I'VE ADDED THIS TASK:" + "\n" +
                " [" + task.getIcon() + "][ ] " + task + "\n" + showCount();
    }

    /**
     * Displays the acknowledgement of deleting a task
     */
    public void showDeleteTask(int index) {
        textResponse = "OK! I'VE DELETED THIS TASK: " + "\n" +
                "" + (index + 1) + ". " +
                "[" + taskList.getTask(index).getIcon() + "]" +
                "[" + taskList.getTask(index).getStatusIcon() + "] " +
                taskList.getTask(index);
    }

    /**
     * Displays the acknowledgement of marking a task as done
     */
    public void showMark(int index) {
        textResponse = "I'VE MARKED THIS TASK AS DONE: " + "\n" +
                "[X] " + taskList.getTask(index);
    }

    /**
     * Displays the acknowledgement of marking a task as not done
     */
    public void showUnmark(int index) {
        textResponse = "I'VE MARKED THIS TASK AS UNDONE: " + "\n" +
                "[] " + taskList.getTask(index);
    }

    /**
     * Displays the list of tasks stored in Duke
     */
    public void showList(TaskList taskList) {
        String tempList = "";
        for (int i = 0; i < taskList.getCount(); i++) {
            tempList += "" + (i+1) + ". " +
                    "[" + taskList.getTask(i).getIcon() + "]" +
                    "[" + taskList.getTask(i).getStatusIcon() + "] " +
                    taskList.getTask(i) + "\n";
        }
        textResponse = "HERE ARE YOUR TASKS!" + "\n" + tempList;

    }

    /**
     * Displays the list of tasks given in the input (for find feature)
     *
     * @param taskList The task list to show
     */
    public void showFindList(ArrayList<Task> taskList) {
        String tempList = "";
        for (int i = 0; i < taskList.size(); i++) {
            tempList += "" + (i+1) + ". " +
                    "[" + taskList.get(i).getIcon() + "]" +
                    "[" + taskList.get(i).getStatusIcon() + "] " +
                    taskList.get(i) + "\n";
        }
        textResponse = "HERE ARE THE MATCHING TASKS!" + "\n" + tempList;
    }

    /**
     * Displays the total number of tasks
     */
    public String showCount() {
        String plural = "";
        int n = taskList.getCount();
        if (n > 1) {
            plural = "S";
        }
        return "NOW YOU HAVE " + n + " TASK" + plural + " IN THE LIST!";
    }

    /**
     * Displays any error the user may trigger while using Duke
     *
     * @param errorMsg The error message to show the user
     */
    public void showUnknown(String errorMsg) {
        textResponse = ":( SORRY! " + errorMsg;
    }

    /**
     * Displays the farewell text when the user requests to
     * close the program
     */
    public void showBye() {
        textResponse = "BYE!";
    }

    public String getTextResponse() {
        return textResponse;
    }

}
