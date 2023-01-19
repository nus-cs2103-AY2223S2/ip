/**
 * Entries to be recorded by Meggy
 */
public class UserTask {
    /**
     * Task description.
     */
    public final String desc;
    /**
     * Task completion status.
     */
    public boolean status;

    /**
     * @param desc Non-null. Description string of task with command removed.
     */
    public UserTask(String desc) throws MeggyException {
        if ("".equals(desc)) // No arguments
            throw new MeggyNoArgException();
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
     * @param keyword Non-null. Raw time keyword.
     * @return Command-syntax-marking time keyword.
     */
    public static String formatKeyword(String keyword) {
        return '/' + keyword + ' ';
    }

    @Override
    public String toString() {
        return Util.parenthesize(status ? Resource.doneMk : ' ') + ' ' + desc;
    }
}