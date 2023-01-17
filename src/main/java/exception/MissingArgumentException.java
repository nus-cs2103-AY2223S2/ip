package exception;

public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(String err) {
        super(err);
    }

    @Override
    public String toString() {
        return "Oh no! " + super.getMessage();
    }
}
