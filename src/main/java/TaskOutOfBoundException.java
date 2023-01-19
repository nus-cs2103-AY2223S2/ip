public class TaskOutOfBoundException extends HomieException {
    public TaskOutOfBoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "    > Nah you tweaking, aint no such task number";
    }
}
