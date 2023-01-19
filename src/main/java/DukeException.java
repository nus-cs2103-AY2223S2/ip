public class DukeException extends Exception {
    public DukeException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String task) {
        System.out.println("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }

    public DukeException(int index) {
        System.out.println("☹ OOPS!!! The index " + index + " for the list of tasks is out of bounds.");
    }
}
