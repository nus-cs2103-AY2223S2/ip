import exceptions.DukeException;

/**
 * Parses user input into usable terms for Duke.
 */
public class Parser {

    public enum TASKTYPE {
        INDEX, TODO, DEADLINE, EVENT
    }

    public String[] parse(String userInput) throws DukeException {
        String[] userInputArr = userInput.split(" ", 2);
        switch (userInputArr[0]) {
            case "list":
                break;
        }
        throw new DukeException.Unimplemented();
    }

    public int extractIndexParams(String userIn) throws DukeException {
        String[] userInSplit = userIn.split(" ",3);
        if (userInSplit.length < 2) {
            throw new DukeException.Missing.Parameter(userInSplit[0]);
        }
        try {
            int ind = Integer.parseInt(userInSplit[1]);
            return ind - 1; // Count starting from 0
        } catch (NumberFormatException e) {
            throw new DukeException.Invalid.Input(String.format("%s is not an integer", userInSplit[1]));
        }
    }

}
