package exceptions;

public class InvalidRequestException extends ArrayIndexOutOfBoundsException{
    public InvalidRequestException(String err){
        super(err);
    }
}
