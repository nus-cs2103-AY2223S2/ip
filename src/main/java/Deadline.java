public class Deadline extends Task{
    private String deadline;
    private char type = 'D';
    public Deadline(String description, String date){
        super(description);
        this.deadline = date;
    }
    public void setDeadline(String date){
        this.deadline = date;
    }
    public String getDeadline(){
        return this.deadline;
    }
    @Override
    public char getType() {
        return type;
    }
    @Override
    public String toString(){
        return super.toString() + "(by: "+ deadline + ")";
    }
}
