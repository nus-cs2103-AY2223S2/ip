package seedu.duke;

import seedu.duke.tasks.*;

public class Ui {

    /**
     * prints out greetings for user to see
     */
    public void sayGreetings() {
        System.out.println("TOP OF THE MORNING TO YOU LADDIES!");
        System.out.println("What can I do for you? :)");
    }

    /**
     * prints out current list of Tasks for user to see
     * @param taskList current Task List
     */
    public void showList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }

    /**
     * prints out line after successfully adding a Task for user to see
     * @param newTask Task that has been added
     * @param updatedList updated Task List
     */
    public void sayAddedTask(Task newTask, TaskList updatedList) {
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.%n", updatedList.getSize());
    }

    /**
     * prints out line after successfully deleting a Task for user to see
     * @param deletedTask Task that has been deleted
     * @param updatedList updated Task List
     */
    public void sayDeletedTask(Task deletedTask, TaskList updatedList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.printf("Now you have %d tasks in the list.%n", updatedList.getSize());
    }

    /**
     * prints out line after successfully marking a Task for user to see
     * @param markedTask Task that has been marked
     */
    public void sayMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
    }

    /**
     * prints out line after successfully unmarking a Task for user to see
     * @param unmarkedTask Task that has been unmarked
     */
    public void sayUnmarkedTask(Task unmarkedTask) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    /**
     * prints out line if there is an error loading for user to see
     */
    public void showLoadingError(){
        System.out.println(("Unable to load!"));
    }

    /**
     * prints out goodbye line for user to see
     */
    public void sayGoodbye() {
        System.out.println("Goodbye!!!! Hope I don't see you again!");
    }
}
