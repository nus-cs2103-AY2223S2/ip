package meggy.task;

import meggy.Resource;
import meggy.Util;
import meggy.exception.MeggyException;
import meggy.exception.MeggyNoArgException;

/** Entries to be recorded by the chatbot. */
public abstract class UserTask {
    /** Task description. */
    public final String desc;
    /** The line (command and extra space removed) that created this task. Stored to avoid repeated regeneration. */
    final String args;
    /** Task completion status. */
    private boolean isDone;

    /**
     * @param desc Non-null. Description string of task with command removed.
     * @param args Non-null. The line (command and extra space removed) that created this task.
     */
    UserTask(String desc, String args) throws MeggyException {
        assert desc != null;
        assert args != null;
        if (desc.isEmpty()) { // No arguments
            throw new MeggyNoArgException();
        }
        this.desc = desc;
        this.args = args;
        isDone = false;
    }

    /**
     * Get ask type label from their names.
     *
     * @param taskType Non-null, non-empty. Name of task type.
     * @return Task-type-specific label.
     */
    public static String getTaskTypeLabel(String taskType) {
        assert taskType != null;
        return Util.parenthesize(Character.toUpperCase(taskType.charAt(0)));
    }

    /**
     * Formats the time keywords used to indicate date-time in user input.
     *
     * @param keyword Non-null. Raw time keyword.
     * @return Command-syntax-marking time keyword.
     */
    public static String formatKeyword(String keyword) {
        assert keyword != null;
        return '/' + keyword + ' ';
    }

    /** Get task completion status. */
    public boolean isDone() {
        return isDone;
    }

    /** Update task completion status. */
    public void setDone(boolean done) {
        isDone = done;
    }

    /** @return Re-create the entry line that would create the task. */
    public abstract String recreateCmd();

    /** @return The string representation of this task in text UI. */
    @Override
    public String toString() {
        return Util.parenthesize(isDone ? Resource.DONE_MK : ' ') + ' ' + desc;
    }
}
