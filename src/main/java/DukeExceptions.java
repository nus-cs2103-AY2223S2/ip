public class DukeExceptions extends Exception{
    private String error;
    public DukeExceptions(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
