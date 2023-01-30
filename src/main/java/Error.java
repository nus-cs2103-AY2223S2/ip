public class Error {
    public static void raiseEmptyDescriptionError(String type) {
        System.out.println("Error! The description of a " + type + " cannot be empty.");
    }

    public static void raiseWrongCommandError() {
        System.out.println("Error! Invalid Command!");
    }

    public static void raiseMissingDeadlineError() {
        System.out.println("Error! Missing Deadline!");
    }

    public static void raiseIncorrectFormatError() {
        System.out.println("Error! The format of the command is incorrect!");
    }

    public static void raiseMissingEventTimeError() {
        System.out.println("Error! Missing event timings!");
    }

    public static void raiseInvalidTaskNumberError(int size) {
        System.out.println("Error! Invalid task number! DukeyList only has " + size + " number of tasks.");
    }

    public static void raiseIndexError() {
        System.out.println("Error! Task index invalid.");
    }

    public static void raiseMissingIndexError() {
        System.out.println("Error! Task index missing.");
    }

}
