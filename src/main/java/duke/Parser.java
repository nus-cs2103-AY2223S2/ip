package duke;

public class Parser {
    /**
     * Checks if the user wants to terminate the chatbot.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "bye" or false otherwise.
     */
    public boolean checkEnd(String userInput) {
        if (userInput.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wants to see his/her list of tasks.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "list" or false otherwise.
     */
    public boolean checkListRequest(String userInput) {
        if (userInput.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wants to mark his/her task.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "mark X" where X is an integer, or false otherwise.
     */
    public boolean checkMarkRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("mark") && terms.length == 2) {
            return checkNumber(terms[1]);
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wishes to delete a task.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "delete X" where X is an integer, or false otherwise.
     */
    public boolean checkDeleteRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("delete") && terms.length == 2) {
            return checkNumber(terms[1]);
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wishes to increase a task's priority level.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "increase X" where X is an integer, or false otherwise.
     */
    public boolean checkIncreaseRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("increase") && terms.length == 2) {
            return checkNumber(terms[1]);
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wishes to decrease a task's priority level.
     *
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "decrease X" where X is an integer, or false otherwise.
     */
    public boolean checkDecreaseRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("decrease") && terms.length == 2) {
            return checkNumber(terms[1]);
        } else {
            return false;
        }
    }

    public boolean checkSort(String userInput) {
        return userInput.equals("sort");
    }

    public boolean checkNumber(String term) {
        boolean isNumber = true;
        for (int i = 0; i < term.length(); i++) {
            if (!Character.isDigit(term.charAt(i))) {
                isNumber = false;
            }
        }
        return isNumber;
    }

    public boolean checkFindRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("find") && terms.length == 2) {
            return true;
        } else {
            return false;
        }
    }
}
