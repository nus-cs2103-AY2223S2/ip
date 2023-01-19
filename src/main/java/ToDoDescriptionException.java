public class ToDoDescriptionException extends Exception{

    public ToDoDescriptionException(String message) {
        super(message);
    }

    public String toString(){
        return getMessage();
    }

}
