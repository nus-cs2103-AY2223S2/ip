public class DukeException extends Exception{
    public DukeException(String errMsg){
        super(errMsg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
