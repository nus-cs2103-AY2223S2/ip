
package Duke.Tasks;



import Duke.Ui;
import Duke.Exceptions.DuplicateException;
import  java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> arrayListOfTasks;

    /**
     * The contructor for ToDo Task
     *
     * @param arrayListOfTasks
     *
     */
    public TaskList(ArrayList<Task> arrayListOfTasks) {
        this.arrayListOfTasks = arrayListOfTasks;
    }

    /**
     * method to add task to list of Tasks
     *
     */
    public void addTask(Task t) throws DuplicateException {
        for (Task task : arrayListOfTasks) {
            if(t.equals(task)){
                throw new DuplicateException();
          }
        }
        arrayListOfTasks.add(t);
    }

    /**
     * method to delete task from list of Tasks
     *
     */
    public Task removeTask(int i) {
        return arrayListOfTasks.remove(i - 1);
    }

    /**
     * method to mark task as done in list of Tasks
     *
     */
    public String markDone(int i) {
        System.out.println(Ui.Underline());
        arrayListOfTasks.get(i - 1).markDone();
        return String.format("%s\n" + "\t%s\n" + Ui.Underline(), Ui.markedMessage(),arrayListOfTasks.get(i - 1).toString());
    }

    /**
     * method to unmark task as done in list of Tasks
     *
     */
    public String markNotDone(int i) {
        System.out.println(Ui.Underline());
        arrayListOfTasks.get(i - 1).markNotDone();
        return String.format("%s\n" + "\t%s\n" + Ui.Underline(), Ui.unMarkedMessage(),arrayListOfTasks.get(i - 1).toString());
    }

    /**
     * getter method to get task  in list of Tasks
     *
     */
    public Task get(int i) {
        return arrayListOfTasks.get(i - 1);
    }

    /**
     * method to get size of list of tasks
     *
     */
    public int size() {
        return arrayListOfTasks.size();
    }


}
