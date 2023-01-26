package cbot.io;

/**
 * A command type accepted by Cbot.
 */
public enum Command {
    //NAME      (str,           hasText,    save),
    /** Ends the Cbot session. */
    BYE         ("bye",         false),

    /** Displays the current list of tasks. */
    LIST        ("list",        false),

    /** Marks the stipulated task as done. */
    MARK        ("mark ",       true,       true),

    /** Catches MARK calls with no input. */
    MARK_BAD    ("mark",        false),

    /** Marks the stipulated task as not done. */
    UNMARK      ("unmark ",     true,       true),

    /** Catches UNMARK calls with no input. */
    UNMARK_BAD  ("unmark",      false),

    /** Deletes the stipulated task from the list. */
    DELETE      ("delete ",     true,       true),

    /** Catches DELETE calls with no input. */
    DELETE_BAD  ("delete",      false),

    /** Adds a To-Do task to the list. */
    TODO        ("todo ",       true,       true),

    /** Catches TODO calls with no input. */
    TODO_BAD    ("todo",        false),

    /** Adds a Deadline task to the list. */
    DEADLINE    ("deadline ",   true,       true),

    /** Catches DEADLINE calls with no input. */
    DEADLINE_BAD("deadline",    false),

    /** Adds an Event task to the list. */
    EVENT       ("event ",      true,       true),

    /** Catches EVENT calls with no input. */
    EVENT_BAD   ("event",       false),

    /** Sorts the list by date and description. */
    SORT        ("sort",        false,      true),

    /** Displays the tasks that fall before the given date. */
    BEFORE      ("before ",     true),

    /** Catches BEFORE calls with no input. */
    BEFORE_BAD  ("before",      false),

    /** Displays the tasks that fall after the given date. */
    AFTER       ("after ",      true),

    /** Catches AFTER calls with no input. */
    AFTER_BAD   ("after",       false),

    /** Displays the tasks that match the given filter. */
    FILTER      ("filter ",     true),

    /** Catches FILTER calls with no input. */
    FILTER_BAD  ("filter",      false),
    FIND        ("find ",       true),
    FIND_BAD    ("find",        false),

    /** Catches all other inputs. */
    NONE        ("",            false);
    
    private final String str;
    private final boolean hasText;
    private final boolean needSave;

    /**
     * Constructs a Command type with the given keyword.
     * Specifies whether the Command expects additional input after the keyword,
     * and whether the file needs to be saved after the Command is run.
     *
     * @param str The command keyword.
     * @param hasText Whether more text after the keyword is expected.
     * @param save Whether the file needs to be saved after the command is run.
     */
    Command(String str, boolean hasText, boolean save) {
        this.str = str;
        this.hasText = hasText;
        this.needSave = save;
    }

    /**
     * Constructs a Command type with the given keyword, where the Command does not update the file.
     * Allows to specify whether the Command expects additional input after the keyword.
     *
     * @param str The command keyword.
     * @param hasText Whether more text after the keyword is expected.
     */
    Command(String str, boolean hasText) {
        this(str, hasText, false);
    }

    /**
     * Returns true if the Command updates the list of tasks, thus calling for it to be saved.
     *
     * @return Whether the Command warrants a file save.
     */
    boolean needSave() {
        return this.needSave;
    }

    /**
     * Returns the length of the keyword, including any whitespace after.
     *
     * @return The length of the Command keyword.
     */
    int getLen() {
        return this.str.length();
    }

    /**
     * Returns true if the start of the given String matches the Command keyword.
     * e.g. "mark 1" matches the keyword "mark " in MARK.
     *
     * @param input The text to check.
     * @return Whether the text means to call this Command.
     * @see Parser
     */
    boolean match(String input) {
        if (this.hasText && input.length() > getLen()) {
            return input.substring(0, getLen()).toLowerCase().equals(this.str);
        } else {
            return input.toLowerCase().equals(this.str);
        }
    }

    /**
     * Extracts the body of the input. That is, the text that comes after the Command keyword.
     *
     * @param input The full input.
     * @return The 'body' of the input.
     */
    String getText(String input) {
        if (this.hasText) {
            return input.substring(getLen()).trim();
        }
        
        return "";
    }

    /**
     * Returns true if the Command expects body text but receives none.
     * e.g. "mark  " with no task number specified.
     *
     * @param text The body text to check.
     * @return Whether there is missing body text, given the Command type.
     */
    boolean missingText(String text) {
        return (this.hasText && text.length() == 0);
    }
}