public enum Views {
    LINE_STRING("____________________________________________________________"),
    WELCOME_STRING("Hello! I'm Duke\n      What can I do for grades?"),
    END_STRING("Bye. Hope to see you again soon!"),
    EMPTY_LIST_STRING("Hey, the list is empty!"),
    CLEAR_LIST_STRING("Ive cleared the list!"),
    MARK_DONE_STRING("Nice! I've marked this task as done\n       "),
    UNMARK_DONE_STRING("OK, I've marked this task as not done yet\n       "),
    DELETE_DONE_STRING("Noted. I've removed this task:\n       "),
    TASK_COUNT_1_STRING("Now you have "),
    TASK_COUNT_2_STRING(" tasks in the list."),
    NO_INT_ERR_STRING("Hey, you did not enter any numbers"),
    OUT_RANGE_ERR_STRING("Hey, the number you've entered is not vaild"),
    UNKNOWN_ERR_STRING("Hey, an unknown error happended, oh no"),
    EMPTY_ERR_STRING("Hey, ☹ The description of a task cannot be empty."),
    UNKNOWN_CMD_ERR_STRING("Hey, ☹ I'm sorry, but I don't know what that means :-("),
    MISSING_ARGS_ERR_STRING("Hey, ☹ I'm sorry, but you are missing some arguments");

    private final String englishString;

    Views(String english) {
        this.englishString = english;
    }

    public String eng() {
        return englishString;
    }

}
