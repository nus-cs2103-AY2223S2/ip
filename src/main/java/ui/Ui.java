package ui;

/**
 * Represents the Ui class that handles user interactions
 */
public class Ui {
    private final String hello = "Hello! I'm Duke";
    private final String capabilities = "Some things I can do include:"
            + "\n\t1. create a new todo, using 'todo',"
            + "\n\t2. create a new event, using 'event',"
            + "\n\t3. create a new deadline, using 'deadline',"
            + "\n\t4. list all tasks, using 'list',"
            + "\n\t5. mark a task, using 'mark',"
            + "\n\t6. unmark a task, using 'unmark',"
            + "\n\t7. find task by keyword, using 'find',"
            + "\n\t8. sort task by type and order, using 'sort',"
            + "\n\t9. exit the program, using 'bye'!";
    private final String question = "What can I do for you?";
    private final String extStr = "Bye! Hope to see you again soon!"
            + "\nThis program will exit automatically in 5 seconds.";
    private final String loadedTasks = "I noticed that some tasks were saved from your last session "
            + "and loaded them up for you.\n";

    public String getExtStr() {
        return extStr;
    }

    public String getIntro() {
        String intro = String.format("%s \n %s \n %s", hello, capabilities, question);
        return intro;
    }

    public String foundSomeTasks() {
        return loadedTasks;
    }

    /**
     * Prints formatted response to the console.
     * @param strings strings that will be printed
     */
    public void printResponse(String... strings) {
        String res = "";
        String line = "\t____________________________________________________________\n";
        res += line;
        for (String s : strings) {
            res += String.format("\t %s\n", s);
        }
        res += line;
        System.out.println(res);
    }
}
