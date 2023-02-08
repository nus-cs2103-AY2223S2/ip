package dukeexceptions;

public class IllegalCommandException extends DukeException{
    public IllegalCommandException(String errorMessage){
        super(errorMessage);
    }

    @Override
    public String toString(){
        return "Sorry, I do not understand your command." + getMessage();
    }
}
