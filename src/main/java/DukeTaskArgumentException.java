public class DukeTaskArgumentException extends DukeException{
    @Override
    public String toString() {
        return String.format("%s Task index specified is invalid", super.toString());
    }
}
