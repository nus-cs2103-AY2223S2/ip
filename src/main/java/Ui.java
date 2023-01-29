public class Ui {

    public void displayBye() {
        System.out.println("Adios!");
    }

    public void displayTasks(TaskList taskList) {
        if (taskList.getSize() == 0) {
            System.out.println("You have no tasks in your list!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getAtIndex(i);
            System.out.println(i + 1 + "." + task);
        }
    }
    
}
