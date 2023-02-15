package duke;

/**
 * Represents all methods such as showLogo, showWelcome...
 */
public class Ui {
    private static final String INDENTATION = " ";

    /**
     * Shows welcome
     * @return greeting
     */
    public static String showWelcome() {
        return INDENTATION + "Hello! I'm Duke"
                + System.getProperty("line.separator")
                + INDENTATION + "What can I do for you?"
                + System.getProperty("line.separator");
    }

    /**
     * Shows user guide
     * @return user guide
     */
    public static String showUserGuide() {
        String userGuide = "If you want to communicate with me, please follow the instructions below: \n"
                + "1. todo [task name]\n"
                + "2. deadline [task name] /by yyyy-MM-dd HHmm\n"
                + "3. event [task name] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm\n"
                + "4. mark [task index]\n"
                + "5. unmark [task index]\n"
                + "6. list\n"
                + "7. delete [task index]\n"
                + "8. find [task name]\n"
                + "9. sort todo/deadline/event\n"
                + "10. bye\n"
                + "Let's start!";
        return userGuide;
    }

    /**
     * Exits the program
     * @return exit message
     */
    public static String exit() {
        return INDENTATION + "Bye. Hope to see you again soon!";
    }

    /**
     * Shows loading error
     * @return error message
     */
    public String showLoadingError() {
        return INDENTATION + "Loading error! please try again"
                + System.getProperty("line.separator");
    }


    /**
     * Shows list details
     * @param task task
     */
    public static String showList(TaskList task) {
        assert task == null : "Invalid task";
        StringBuilder str = new StringBuilder();

        str.append(INDENTATION + "Here are the tasks in your list: ");
        str.append(System.getProperty("line.separator"));

        for (int i = 0; i < task.size(); i++) {
            str.append(INDENTATION).append(i + 1).append(". ").append(task.get(i).toString());
            str.append(System.getProperty("line.separator"));
        }

        str.append(INDENTATION).append("There are ").append(task.size()).append(" tasks right now!");
        str.append(System.getProperty("line.separator"));
        return str.toString();
    }

    /**
     * Shows that task is marked as done
     * @param num  index for the task
     * @param tasks task
     * @return mark as done task
     */
    public static String done(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int number = Integer.parseInt(num) - 1;
        //tasks.get(number).isDone = true;
        tasks.get(number).markDone();


        str.append("Nice! I've marked this task as done:");
        str.append(System.getProperty("line.separator"));
        str.append(INDENTATION).append(num).append(". ").append(tasks.get(number).toString());
        str.append(System.getProperty("line.separator"));


        return str.toString();
    }

    /**
     * Shows that task is marked as not done
     * @param num index for the task
     * @param tasks task
     * @return mark as undone task
     */
    public static String undone(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int number = Integer.parseInt(num) - 1;
        //tasks.get(number).isDone = false;
        tasks.get(number).markUndone();

        str.append("Nice! I've marked this task as done:");
        str.append(System.getProperty("line.separator"));
        str.append(INDENTATION).append(num).append(". ").append(tasks.get(number).toString());
        str.append(System.getProperty("line.separator"));
        return str.toString();
    }

    /**
     * Delete the tasks
     * @param num index for the task
     * @param tasks task
     * @return deleting message
     */
    public static String delete(String num, TaskList tasks) {
        assert num == null : "Invalid num";
        assert tasks == null : "Invalid tasks";

        StringBuilder str = new StringBuilder();
        int index = Integer.parseInt(num) - 1;
        int originalSize = tasks.size();
        str.append(INDENTATION + "Noted. I've removed this task:");
        str.append(System.getProperty("line.separator"));
        str.append(INDENTATION).append(tasks.get(index).toString());
        str.append(System.getProperty("line.separator"));

        tasks.remove(index);
        //Task.taskNum--;
        //tasks.size();
        assert tasks.size() == originalSize - 1;

        //Task.taskNum
        str.append(" Now you have ").append(tasks.size()).append(" tasks in the list.");
        str.append(System.getProperty("line.separator"));
        return str.toString();
    }
}

