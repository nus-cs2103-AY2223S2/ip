package Meggy.Task;

import Meggy.Exception.MeggyException;
import Meggy.Exception.MeggyNoArgException;
import Meggy.Resource;
import Meggy.Util;

/** Entries to be recorded by the chatbot. */
public abstract class UserTask {
    /** Task description. */
    public final String desc;
    /** Task completion status. */
    public boolean status;

    /** @param desc Non-null. Description string of task with command removed. */
    public UserTask(String desc) throws MeggyException {
        if ("".equals(desc)) { // No arguments
            throw new MeggyNoArgException();
        }
        this.desc = desc;
        status = false;
    }

    /**
     * Get ask type label from their names.
     *
     * @param taskType Non-null, non-empty. Name of task type.
     * @return Task-type-specific label.
     */
    public static String getTaskTypeLabel(String taskType) {
        return Util.parenthesize(Character.toUpperCase(taskType.charAt(0)));
    }

    /**
     * Formats the time keywords used to indicate date-time in user input.
     * @param keyword Non-null. Raw time keyword.
     * @return Command-syntax-marking time keyword.
     */
    public static String formatKeyword(String keyword) {
        return '/' + keyword + ' ';
    }

    /**
     * @return The string representation of this task in data file format. Currently: re-create the command that would
     * add the task.
     */
    public abstract String encode();

    /** @return The string representation of this task in text UI. */
    @Override
    public String toString() {
        return Util.parenthesize(status ? Resource.DONE_MK : ' ') + ' ' + desc;
    }
}