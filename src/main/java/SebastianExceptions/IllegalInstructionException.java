package SebastianExceptions;

public class IllegalInstructionException extends Exception{
    public IllegalInstructionException() {
        super("Apologies, I'm afraid I don't understand your instruction");
    }
}
