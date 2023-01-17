public class EmptyDescriptionException extends DuduException{
    private String type;
    public EmptyDescriptionException(String type, String msg) {
        super(msg);
        this.type = type;
    }
    @Override
    public String toString() {
        return "OPPS!!! The description of a " + type + " cannot be empty.";
    }
}
