public class DukeException extends Exception {

    public DukeException() {
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String message) {
        System.out.println("☹ OOPS!!! The description of a " + message + " cannot be empty.");
    }

}
