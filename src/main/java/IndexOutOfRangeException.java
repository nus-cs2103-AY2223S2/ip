public class IndexOutOfRangeException extends DukeException {
    public IndexOutOfRangeException () {
        super("Oh no! I cannot find a task with that task number.\n" +
                "You can check them again by asking me to 'list'");
    }
}
