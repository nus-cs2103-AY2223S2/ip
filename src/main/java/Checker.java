public class Checker {
    public boolean checkEnd(String userInput) {
        if (userInput.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkListRequest(String userInput) {
        if (userInput.equals("list")) {
            return true;
        } else {
            return false;
        }
    }
}
