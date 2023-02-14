package duke.enums;

import duke.Duke;

/**
 * Enum for every spoken line from Duke
 */
public enum Views {
    LINE_STRING(
            "____________________________________________________________",
            "____________________________________________________________",
            "____________________________________________________________",
            "____________________________________________________________"),
    WELCOME_STRING(
            "Hello! I'm Duke\n      What can I do for grades?",
            "Ahoy! I'm Duke\n      What be ye needin' fer yer grades?",
            "Greetings! I am Duke\n      How may I assist thee for thy grades?",
            "Heya! Ahm Duke\n      Wut can Ah do for grades?"),
    END_STRING(
            "Bye. Hope to see you again soon!",
            "Fare thee well! Hope to see ye again soon!",
            "Farewell. May we cross paths again soon!",
            "Bye. Hope ta see yu agen soon!"),
    EMPTY_LIST_STRING(
            "Hey, the list is empty!",
            "Arrr, the list be empty!",
            "Verily, the list is devoid of content!",
            "Ey, da list is emp-teh!"),
    CLEAR_LIST_STRING(
            "Ive cleared the list!",
            "I've cleared the list, ye scallywag!",
            "Fear not, I have purged the list!",
            "Ahve cleared da list!"),
    MARK_DONE_STRING(
            "Nice! I've marked this task as done\n       ",
            "Well shiver me timbers! I've marked this task as done\n      ",
            "Well done! I have marked this task as complete\n      ",
            "Nice! Ahve marked dis task as done\n      "),
    MARK_MANY_DONE_STRING(
            "Nice! I've marked these task as done:\n      ",
            "Well shiver me timbers! I've marked these tasks as done:\n      ",
            "Well done! I have marked these tasks as complete:\n      ",
            "Nice! Ahve marked dese tasks as done:\n      "),
    UNMARK_DONE_STRING(
            "OK, I've marked this task as not done yet\n       ",
            "Aye aye, I've marked this task as not done yet\n      ",
            "Fear not, I have marked this task as not yet completed\n      ",
            "OK, Ahve marked dis task as not done yet\n      "),
    UNMARK_MANY_DONE_STRING(
            "OK, I've marked these task as not done yet:\n      ",
            "Aye aye, I've marked these tasks as not done yet:\n      ",
            "Fear not, I have marked these tasks as not yet completed:\n      ",
            "OK, Ahve marked dese tasks as not done yet:\n      "),
    DELETE_DONE_STRING(
            "Noted. I've removed this task:\n       ",
            "Avast. I've removed this task:\n      ",
            "It shall be done. I have removed this task:\n      ",
            "Noted it down. Ahve removed dis task:\n      "),
    DELETE_MANY_DONE_STRING(
            "Noted. I've removed the following tasks:\n      ",
            "Avast. I've removed the following tasks:\n      ",
            "It shall be done. I have removed the following tasks:\n      ",
            "Noted it down. Ahve removed da following tasks:\n      "),
    ALL_COMMAND_STRING(
            "Here are all the commands:",
            "Here be all the commands:",
            "Thou commands be here:",
            "Here yu commands:"),
    TASK_COUNT_1_STRING(
            "Now you have ",
            "Now ye have ",
            "Thou hast ",
            "Now yu have "),
    TASK_COUNT_2_STRING(
            " tasks in the list.",
            " tasks on yer list.",
            " tasks in thy list.",
            " tasks in da list."),
    LIST_STRING(
            "Here are the tasks in your list:\n      ",
            "Here be the tasks on yer list:\n      ",
            "Here are the tasks in thy list:\n      ",
            "Here are da tasks in yur list:\n      "),
    FOUND_LIST_STRING(
            "Here are the matching tasks in your list:\n      ",
            "Here be the matching tasks on yer list:\n      ",
            "Here are the matching tasks in thy list:\n      ",
            "Here are da matching tasks in yur list:\n      "),
    SELECTED_LANG_STRING("You have selected: ",
            "You be selected: ",
            "Thou art selected: ",
            "Ey, yu da selected: "),
    EXPORT_MD_STRING("Exporting tasks to markdown, stored as todo.md",
            "Arr be exporting tasks to markdown, stored as todo.md",
            "Thou exporting tasks to markdown, stored as todo.md",
            "Ey da export tasks to markdown, stored as todo.md"),
    MD_HEADER_STRING("# TODO from DUKE:\n",
            "# TODO from DUKE:\nArr here be your lists:\n",
            "# TODO from DUKE:\nHere be thy lists:\n",
            "# TODO from DUKE:\nEy, here da tasks in yur list:\n"),
    CANNOT_FIND_STRING(
            "Hey, I can't find what you're searching for",
            "Arrr, I can't find what ye be searchin' for",
            "Alack, I cannot find what thou art searching for",
            "Ey, Ah cant find wut yur searching fur"),
    MARKED_ERR_STRING(
            "Hey, the task you tried to mark is already marked",
            "Arrr, the task ye tried to mark be already marked",
            "Alack, the task thou attempted to mark is already marked",
            "Ey, da task yu tried ta mark is already marked"),
    UNMARKED_ERR_STRING(
            "Hey, the task you tried to unmark is already unmarked",
            "Arrr, the task ye tried to unmark be already unmarked",
            "Alack, the task thou attempted to unmark is already unmarked",
            "Ey, da task yu tried ta unmark is already unmarked"),
    NO_INT_ERR_STRING(
            "Hey, you did not enter any numbers",
            "Arrr, ye did not enter any numbers",
            "Alack, thou hast not entered any numbers",
            "Ey, yu did not enter any num-bers"),
    OUT_RANGE_ERR_STRING(
            "Hey, the number you've entered is not valid",
            "Arrr, the number ye've entered be not valid",
            "Alack, the number thou hast entered is not valid",
            "Ey, da number yuve entered is not valid"),
    UNKNOWN_ERR_STRING(
            "Hey, an unknown error happened, oh no",
            "Arrr, an unknown error happened, oh no",
            "Alack, an unknown error has occurred, oh woe",
            "Ey, an unknown error happened, oh no"),
    EMPTY_ERR_STRING(
            "Hey, ☹ The description of a task cannot be empty.",
            "Arrr, ☹ The description of a task cannot be empty.",
            "Alack, ☹ The description of a task cannot be empty.",
            "Ey, ☹ Da description of a task cannot be emp-teh."),
    UNKNOWN_CMD_ERR_STRING(
            "Hey, ☹ I'm sorry, but I don't know what that means :-( type 'help'",
            "Arrr, ☹ I'm sorry, but I don't know what you be means :-( type 'help'",
            "Alack, ☹ I apologize, but I am not familiar with that command :-( type 'help'",
            "Ey, ☹ Ahm sorry, but Ah dont know wut dat means :-( type 'help'"),
    MISSING_ARGS_ERR_STRING(
            "Hey, ☹ I'm sorry, but you are missing some arguments",
            "Arrr, ☹ I'm sorry, but ye be missin' some arguments",
            "Alack, ☹ I apologize, but thou art missing some arguments",
            "Ey, ☹ Ahm sorry, but yu are missing sum arguments"),
    LOAD_EXTRA_ERR_STRING(
            "File load has error, ignoring that line of error",
            "File load has error, ignorin' that line of error",
            "The loading of the file hath errors, ignoring that line",
            "File load has error, ignoring dat line of error"),
    DATE_WRONG_ORDER_STRING(
            "Hey, ☹ you seem to have ordered the /to and /from wrongly",
            "Arrr, ☹ ye seem to have ordered the /to and /from wrongly",
            "Alack, ☹ thou seem to have ordered the /to and /from incorrectly",
            "Ey, ☹ yu seem ta have ordered da /to and /from wrongly"),
    INVALID_LANG_ERR_STRING(
            "Hey, ☹ you seem to have entered an invalid language, available: ",
            "Arrr, ☹ ye seem to have entered an invalid language, available: ",
            "Alack, ☹ thou seem to have entered an invalid language, available: ",
            "Ey, ☹ yu seem ta have da an invalid language, available: "),
    DATE_PARSE_ERR_STRING(
            "Hey, ☹ please enter the date in this format YYYY-MM-DDTHH:MM like this: '2023-01-20T18:00'",
            "Arrr, ☹ please enter the date in this format YYYY-MM-DDTHH:MM like this: '2023-01-20T18:00'",
            "Alack, ☹ please enter the date in this format YYYY-MM-DDTHH:MM like this: '2023-01-20T18:00'",
            "Ey, ☹ please enter da date in dis format YYYY-MM-DDTHH:MM like dis: '2023-01-20T18:00'");

    private final String englishString;
    private final String pirateString;
    private final String shakespeareString;
    private final String lolcatString;

    Views(String english, String pirate, String shakespeare, String lolcat) {
        this.englishString = english;
        this.pirateString = pirate;
        this.shakespeareString = shakespeare;
        this.lolcatString = lolcat;
    }

    /**
     * Return the string of the language set in Duke.java
     *
     * @return
     */
    public String str() {
        switch (Duke.getLang()) {
        case PIRATE:
            return pirateString;
        case SHAKESPEARE:
            return shakespeareString;
        case LOLCAT:
            return lolcatString;
        default:
            return englishString;
        }
    }
}
