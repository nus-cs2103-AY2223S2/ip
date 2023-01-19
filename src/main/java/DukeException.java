public class DukeException extends Exception{
    String message;
    public DukeException() {
        this.message = "OOPS!!! Something went wrong." + " Type \"help\" to see the commands.";
    }

    public DukeException(String message) {
        this.message = "OOPS!!! " + message + " Type \"help\" to see the commands.";
    }
}
