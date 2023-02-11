package duke.enums;

/**
 * Enum for every spoken line from Duke
 */
public enum Views {
    LINE_STRING("____________________________________________________________"),
    WELCOME_STRING("Hello! I'm Duke\n      What can I do for grades?"),
    END_STRING("Bye. Hope to see you again soon!"),
    EMPTY_LIST_STRING("Hey, the list is empty!"),
    CLEAR_LIST_STRING("Ive cleared the list!"),
    MARK_DONE_STRING("Nice! I've marked this task as done\n       "),
    MARK_MANY_DONE_STRING("Nice! I've marked these task as done:\n      "),
    UNMARK_DONE_STRING("OK, I've marked this task as not done yet\n       "),
    UNMARK_MANY_DONE_STRING("OK, I've marked these task as not done yet:\n      "),
    DELETE_DONE_STRING("Noted. I've removed this task:\n       "),
    DELETE_MANY_DONE_STRING("Noted. I've removed the following tasks:\n      "),
    TASK_COUNT_1_STRING("Now you have "),
    TASK_COUNT_2_STRING(" tasks in the list."),
    LIST_STRING("Here are the tasks in your list:\n      "),
    FOUND_LIST_STRING("Here are the matching tasks in your list:\n      "),
    CANNOT_FIND_STRING("Hey, I can't find what you're searching for"),
    NO_INT_ERR_STRING("Hey, you did not enter any numbers"),
    OUT_RANGE_ERR_STRING("Hey, the number you've entered is not valid"),
    UNKNOWN_ERR_STRING("Hey, an unknown error happened, oh no"),
    EMPTY_ERR_STRING("Hey, ☹ The description of a task cannot be empty."),
    UNKNOWN_CMD_ERR_STRING("Hey, ☹ I'm sorry, but I don't know what that means :-("),
    MISSING_ARGS_ERR_STRING("Hey, ☹ I'm sorry, but you are missing some arguments"),
    LOAD_EXTRA_ERR_STRING("File load has error, ignoring that line of error"),
    DATE_WRONG_ORDER_STRING("Hey, ☹ you seem to have ordered the /to and /from wrongly"),
    DATE_PARSE_ERR_STRING(
            "Hey, ☹ please enter the date in this format YYYY-MM-DDTHH:MM like this: '2023-01-20T18:00'");

    private final String englishString;

    Views(String english) {
        this.englishString = english;
    }

    public String eng() {
        return englishString;
    }

}
