package crystal;

import java.util.Scanner;

import crystal.task.Deadline;
import crystal.task.Event;
import crystal.task.Task;
import crystal.task.Todo;


/**
 * Represents the Ui task.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String line = " ____________________________________________________________";


    /**
     * Return the next Line in the file
     * @returns the next line in the file
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns the welcome message.
     * @returns the welcome message.
     *
     */
    public String showWelcome() {
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append(" Hi! I am CRYSTAL.\n How may I be of assistance?\n");
        temp.append(line + "\n");
        return temp.toString();
    }

    /**
     * Prints the error message.
     *
     * @param e Exception
     */
    public void showError(CrystalException e) {
        System.out.println(" ____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println(" ____________________________________________________________");
    }

    /**
     * Prints the error message if the file failed to load.
     */
    public void showLoadingError() {
        System.out.println("Load Error");
    }

    /**
     * Returns the list.
     *
     * @param task tasklist
     * @return the message.
     */
    public String printList(TaskList task) {
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Here are your current tasks:\n");
        for (int i = 0; i < task.size(); i++) {
            temp.append(i + 1 + ". " + task.get(i) + "\n");
        }
        temp.append(line + "\n");
        return temp.toString();

    }

    /**
     * Returns the unmark task message.
     *
     * @param task tasklist.
     * @param num  The task number to be unmarked
     * @return the message.
     */
    public String printUnmark(TaskList task, int num) {
        Task unmarkTask = task.get(num - 1);
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've marked this task as not done: \n");
        unmarkTask.setIsDone(false);
        temp.append(unmarkTask.toString() + "\n");
        temp.append(line + "\n");
        return temp.toString();
    }

    /**
     * Returns the mark task message.
     *
     * @param task tasklist.
     * @param num  The task number to be marked
     * @return the message.
     */
    public String printMark(TaskList task, int num) {
        Task markTask = task.get(num - 1);
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've marked the task as done: \n");
        markTask.setIsDone(true);
        temp.append(markTask.toString() + "\n");
        temp.append(line + "\n");
        return temp.toString();
    }


    /**
     * Returns the todo task message.
     *
     * @param task tasklist.
     * @param td   Todo task
     * @return the message
     */
    public String printTodo(TaskList task, Todo td) {

        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've added this task: \n");
        temp.append(td.toString() + "\n");
        temp.append("Current number of tasks : " + task.size() + "\n");
        temp.append(line + "\n");
        return temp.toString();
    }


    /**
     * Returns the deadline task message.
     *
     * @param task tasklist.
     * @param dl   Deadline task.
     * @return the message
     */
    public String printDeadline(TaskList task, Deadline dl) {

        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've added this task: \n");
        temp.append(dl.toString() + "\n");
        temp.append("Current number of tasks : " + task.size() + "\n");
        temp.append(line + "\n");
        return temp.toString();

    }


    /**
     * Returns the event task message.
     *
     * @param task tasklist.
     * @param evt  Event task.
     * @return the message
     */
    public String printEvent(TaskList task, Event evt) {
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've added this task: \n");
        temp.append(evt.toString() + "\n");
        temp.append("Current number of tasks: " + task.size() + "\n");
        temp.append(line + "\n");
        return temp.toString();

    }

    /**
     * Returns the bye message.
     * @return the message
     */
    public String printBye() {
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append(" Thank You. Please come by again~!\n");
        temp.append(line + "\n");
        return temp.toString();
    }

    /**
     * Returns the delete message.
     *
     * @param task Tasklist
     * @param num  the task number to be deleted
     * @return the message
     */
    public String printDelete(TaskList task, int num) {
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've removed this task: \n");
        Task item = task.get(num - 1);
        task.remove(num - 1);
        temp.append(item.toString() + "\n");
        temp.append("Current number of tasks: " + task.size() + "\n");
        temp.append(line + "\n");
        return temp.toString();
    }

    /**
     * Returns the find message
     * @param task Tasklist
     * @param word the word to be found in the tasklist
     * @return the message
     */
    public String printFind(TaskList task, String word) {

        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Here are the matching tasks in your list: \n");
        int counter = 1;
        for (int i = 0; i < task.size(); i++) {
            if (task.get(i).getDescription().contains(word.trim())) {
                temp.append(counter + ". " + task.get(i) + "\n");
                counter++;
            }
        }
        temp.append(line + "\n");
        return temp.toString();
    }

    /**
     *  Returns the priority message.
     * @param task Tasklist
     * @param itemNum item to be prioritize.
     * @param lvlNum priority level.
     * @return the priority message.
     */


    public String printPriority(TaskList task, int itemNum, int lvlNum) {
        Task markTask = task.get(itemNum - 1);
        markTask.setIsPriority(true);
        markTask.setPriorityNum(lvlNum);
        StringBuilder temp = new StringBuilder();
        temp.append(line + "\n");
        temp.append("Alright, I've marked this task as priority level " + lvlNum + ":" + "\n");
        temp.append(markTask.toString() + "\n");
        temp.append(line + "\n");
        return temp.toString();
    }


}
