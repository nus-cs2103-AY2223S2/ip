package duke;

public class Ui {
    /**
     * Handles the welcome user interface when the programs start.
     */
    public String welcome() {
        return  "Hello! I'm duke.\nWhat can I do for you? \n \n" +
                "There are 3 different types of activities: \n 1. todo (todo ____)\n 2. " +
                "deadline (deadline ____ /by ____ [yyyy-mm-dd]) \n 3. event (event ____ /from ____ /to ____ \n \n You" +
                " can" +
                " also do this: \n 1. list \n 2. mark _ \n 3. unmark _ \n 4. delete _ \n 5. find ____ \n \nEnjoy " +
                "Using " +
                "~";
    }

    /**
     * Handles to goodbye user interface when programs end.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the user interface if there is not previously saved tasklist.
     */
    public String showLoadingError() {
        return "There is no saved file! Start a new list :)";
    }

    public String help() {
        String userGuide = "How to use?:\n \nThere are 3 different types of activities: \n 1. todo (todo ____)\n 2. " +
                "deadline (deadline ____ /by ____ [yyyy-mm-dd]) \n 3. event (event ____ /from ____ /to ____ \n \n You" +
                " can also do this: \n 1. list \n 2. mark _ \n 3. unmark _ \n 4. delete _ \n 5. find ____ \n \n For " +
                "examples type /example\n ";
        return userGuide;
    }

    public String example() {
        String examples = "Example: (where `work` is the activity you want to add and the `1` corresponds to number " +
                "on the list) \n 1. todo: " + "todo work \n 2. " + "deadline: deadline " + "work " + "/by " + "2023-02-17 \n 3. " +
                "event: event work /from today 3pm /to 6pm \n 4. list: list \n 5. mark: mark 1 \n 6. unmark: unmark 1" +
                "\n 7. delete: delete 1 \n 8. find: find work \n ";
        return examples;
    }
}
