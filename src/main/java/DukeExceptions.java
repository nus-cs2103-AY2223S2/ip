public class DukeExceptions {
    static void checkEmptyDescription(String command[]) throws EmptyDescriptionException {
        if(command.length == 1 || (command[0].equals("deadline") && command[1].equals("/by")) || (command[0].equals("event") && (command[1].equals("/from") || command[1].equals("/to"))))  {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + command[0] + " cannot be empty");
        }
    }

    static void checkPastData(boolean fileExists) throws PastDataDoesNotExistException {
        if(!fileExists) {
            throw new PastDataDoesNotExistException("☹ OOPS!!! Past data does not exist!");
        }
    }

    static void checkCommand(String command[]) throws DontKnowWhatThatMeansException {
        String[] commandArray = {"mark", "unmark", "todo", "delete", "deadline", "event", "list", "bye"};
        boolean check = false;
        for(int i = 0; i < commandArray.length; i++) {
            if(command[0].equals(commandArray[i])) {
                check = true;
            }
        }
        if(check == false) {
            throw new DontKnowWhatThatMeansException("☹ OOPS!!! Don't know what that means");
        }
    }
}
