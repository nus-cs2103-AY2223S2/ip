/**
 * Entries to be recorded by the bot
 */
public class UserTask {
    public final String desc;
    public boolean status;

    /**
     * @param desc Parsed, trimmed string description of task.
     */
    public UserTask(String desc) throws MeggyException {
        if ("".equals(desc)) // No arguments
            throw new MeggyNoArgException();
        this.desc = desc;
        status = false;
    }

    public static String getTaskTypeLabel(String taskType) {
        return Util.parenthesize(Character.toUpperCase(taskType.charAt(0)));
    }

    public static String formatKeyword(String keyword) {
        return '/' + keyword + ' ';
    }

    @Override
    public String toString() {
        return Resource.statusFmt(status) + ' ' + desc;
    }
}