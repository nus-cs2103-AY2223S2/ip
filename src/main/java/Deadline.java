public class Deadline  extends  Task{
    protected String doneBy;

    public Deadline(String description, String doneBy){
        super(description);
        this.doneBy = doneBy;
    }
    @Override
    public String toString(){
        return String.format("[D]%s (by: %s)", super.toString(), this.doneBy);
    }
    @Override
    public String changeFormat() {

        return String.format("T %s " + "| " + this.doneBy, super.changeFormat());

    }
}
