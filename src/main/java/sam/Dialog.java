package sam;

/**
 * The list of Sam's dialogs.
 */
public enum Dialog {
    GREETING("Hello, I am Sam!"),
    BYE("Goodbye!"),
    LIST("Here is your list:"),
    LIST_EMPTY("Your list is empty!"),
    MARK("Great! I'll check the task:"),
    UNMARK("Okay, I'll uncheck the task:"),
    ADD("Gotcha, I'll add the task to your list:"),
    ADD_COUNT("Now you have %d tasks in the list"),
    DELETE("Ok, I'll remove the task from your list:"),
    FIND("I found %d matching tasks:"),
    FIND_EMPTY("None of your tasks match!"),
    EDIT("Okay, I've updated the following task:"),
    CLONE("Okay, I've cloned the following task:"),
    INVALID_DATE("Please write dates as 'd/M/yyyy'!"),
    INVALID_INT("Oops, I was expecting an integer!"),
    INVALID_TASK("Oops, that task does not exist!"),
    UNKNOWN_COMMAND("Sorry, I don't know what that means"),
    MISSING_TASK("Oops, you forgot to specify a task!"),
    MISSING_TASK_TITLE("Oops, you forgot a title for your task!"),
    MISSING_TASK_ARG("Oops, you're missing an argument!"),
    MISSING_TASK_VALUE("Oops, an argument is missing a value!"),
    LOAD_FAILED("Oh no, there was a problem loading your list!"),
    SAVE_FAILED("Oh no, there was a problem saving your list!");

    private String dialog;

    private Dialog(String dialog) {
        this.dialog = dialog;
    }

    public String getDialog() {
        return dialog;
    }
}