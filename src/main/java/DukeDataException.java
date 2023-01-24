public class DukeDataException extends DukeException{
    @Override
    public String toString() {
        return String.format("\t%s Error in data read..", super.toString());
    }
}