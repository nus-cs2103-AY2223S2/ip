package duke;

import duke.Tasks.*;

public class Ui {

    public void sayGreetings() {
        System.out.println("TOP OF THE MORNING TO YOU LADDIES!");
        System.out.println("What can I do for you? :)");
    }

    public void showList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.get(i);
            System.out.printf("%d.%s%n", i + 1, task);
        }
    }

    public void sayAddedTask(Task newTask, TaskList updatedList) {
        System.out.println("Got it, I've added this task:");
        System.out.println(newTask);
        System.out.printf("Now you have %d tasks in the list.%n", updatedList.getSize());
    }

    public void sayDeletedTask(Task deletedTask, TaskList updatedList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.printf("Now you have %d tasks in the list.%n", updatedList.getSize());
    }

    public void sayMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
    }

    public void sayUnmarkedTask(Task unmarkedTask) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    public void showLoadingError(){
        System.out.println(("Unable to load!"));
    }

    public void sayGoodbye() {
        System.out.println("Goodbye!!!! Hope I don't see you again!");
    }
}
