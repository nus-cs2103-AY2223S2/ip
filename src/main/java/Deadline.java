public class Deadline extends Task{
    private String by;
    private String code;

    public Deadline(String msg, String by) {
        super(msg);
        this.by = by;
        this.code = "[D]";
    }
    
    public String getCode(){
        return this.code;
    }

    @Override
    public String toString() {
        return code + super.toString() + " (" + by + ")";
    }
    
}
