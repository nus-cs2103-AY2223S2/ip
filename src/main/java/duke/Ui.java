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
                + "2. deadline [task name] /by dd/MM/yyyy HHmm\n"
                + "3. event [task name] /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\n"
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
        System.exit(0);
        return INDENTATION + "Bye. Hope to see you again soon!";
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
        tasks.get(number).markUndone();

        str.append("Nice! I've marked this task as undone:");
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

        assert tasks.size() == originalSize - 1;

        str.append(" Now you have ").append(tasks.size()).append(" tasks in the list.");
        str.append(System.getProperty("line.separator"));
        return str.toString();
    }
}
