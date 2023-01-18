public class DukeException extends Exception{
    protected String errorMessage;

    public DukeException(String message){
        this.errorMessage = message;
    }
}
