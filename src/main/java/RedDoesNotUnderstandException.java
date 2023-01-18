public class RedDoesNotUnderstandException extends RuntimeException{
    public RedDoesNotUnderstandException() {
        super("Unable to comprehend instruction given\n");
    }
}
