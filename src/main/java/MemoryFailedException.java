public class MemoryFailedException extends DukeException {


    public MemoryFailedException() {

    }

    @Override
    public String toString() {
        return "There was an issue loading up memory. Loading empty task list instead!";
    }
}
