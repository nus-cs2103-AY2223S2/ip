public class TaskListEmpty extends DukeException{

    public TaskListEmpty(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "☹ hmm strange the list is missing and so are my cookies?!?";
    }
}
