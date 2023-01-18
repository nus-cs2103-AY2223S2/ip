/**
 * Entries to be recorded by the bot
 */
public class UserTask {
    public final String desc;
    public boolean status;

    /**
     * @param args User's input line with command and whitespace after it removed
     */
    public UserTask(String args) {
        desc = args;
        status = false;
    }

    @Override
    public String toString() {
        return Resource.statusFmt(status) + ' ' + desc;
    }
}