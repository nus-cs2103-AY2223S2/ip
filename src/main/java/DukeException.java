public class DukeException extends RuntimeException {
<<<<<<< HEAD
=======

    public static final DukeException DATETIME_FORMAT = new DukeException("Only datetime format of 2023-01-01 is " + "accepted");

>>>>>>> branch-Level-8
    public DukeException(String message) {
        super(message);
    }
}
