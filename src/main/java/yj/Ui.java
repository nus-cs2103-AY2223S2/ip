package yj;

public class Ui {
    private String message;
    public void print(String message) {
        System.out.println(message);
        this.message += "\n" + message;
    }

    public void clear() {
        this.message = "";
    }

    public String getMessage() {
        return this.message;
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Crapadoodle! I couldn't load. Too bad man.");
    }
}
