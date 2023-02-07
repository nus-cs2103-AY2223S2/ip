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
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "mark X" where X is an integer, or false otherwise.
     */
    public boolean checkMarkRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("mark") && terms.length == 2) {
            boolean isNumber = true;
            for (int i = 0; i < terms[1].length(); i++) {
                if (!Character.isDigit(terms[1].charAt(i))) {
                    isNumber = false;
                }
            }
            return isNumber;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user wishes to delete a task.
     * @param userInput the input keyed in by the user.
     * @return true if the user keyed in "delete X" where X is an integer, or false otherwise.
     */
    public boolean checkDeleteRequest(String userInput) {
        String[] terms = userInput.split(" ");
        if (terms[0].equals("delete") && terms.length == 2) {
            boolean isNumber = true;
            for (int i = 0; i < terms[1].length(); i++) {
                if (!Character.isDigit(terms[1].charAt(i))) {
                    isNumber = false;
                }
            }
            return isNumber;
        } else {
            return false;
        }
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
