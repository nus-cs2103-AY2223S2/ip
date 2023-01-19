public class DukeExceptions {
    static void checkEmptyDescription(String command[]) throws EmptyDescriptionException {
        if(command.length == 1) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command[0] + " cannot be empty");
        }
    }
}
