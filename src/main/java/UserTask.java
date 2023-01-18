/**
 * Entries to be recorded by the bot
 * */
public class UserTask {
    public final String desc;
    public boolean status;

    public UserTask(String args) {
        desc = args;
        status = false;
    }

    @Override
    public String toString() {
        return Resource.statusFmt(status) + ' ' + desc;
    }
}