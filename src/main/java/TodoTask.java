/**
 * Users task that has no time whatsoever
 * */
public class TodoTask extends UserTask{
    public final static String label=Util.parenthesize(Character.toUpperCase(Resource.cmdTodo.charAt(0)));
    public TodoTask(String args) {
        super(args);
    }

    @Override
    public String toString() {
        return label+Resource.statusFmt(status) + ' ' + desc;
    }
}
