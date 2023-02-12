package tunabot;

import tunabot.task.Task;

/**
 * Class to handle User Interface
 */
public class Ui {
    private static final String LINE = "------------------------------\n";
    public Ui() {
    }

    /**
     * Returns list of given TaskList
     * @param tasks given TaskList
     * @return list of tasks
     */
    public static String list(TaskList tasks) {
        StringBuilder list = new StringBuilder("BLUB! There are " + tasks.size() + " task(s)!\n");
        for (int i = 1; i <= tasks.size(); i++) {
            list.append("    ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return list.toString();
    }

    public static String mark(Task task) {
        return "    Blub! i have marked this as done!\n" + task;
    }

    public static String unmark(Task task) {
        return "    Blub! i have marked this as not done!\n" + task;
    }

    /**
     * Returns number of tasks and deleted given task
     * @param task Task to be deleted
     * @param size Number of tasks after deletion
     * @return String output
     */
    public static String delete(Task task, int size) {
        String delete = "    Blub! i have deleted this task!\n";
        delete += "    Blub! You have " + size + " tasks now!";
        return delete;
    }

    /**
     * Returns number of tasks and added given task
     * @param newTask Task to add
     * @param size Number of tasks after add
     * @return String output
     */
    public static String add(Task newTask, int size) {
        String add = "    Blub! added: \n" + newTask + "\n";
        add += "    Blub! You have " + size + " tasks now!";
        return add;
    }


    public void saveFileProblem() {
        System.out.println("BLUB! Problem with save file!");
    }
    
    public void line() {
        System.out.println(LINE);
    }
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error when date time format is wrong.
     */
    public void printDateTimeFormatError() {
        System.out.println("BLUB! Please use the format dd/mm/yy-hhmm "
                + "with time in 24H format! eg. 29/12/23-1854");
    }
}
