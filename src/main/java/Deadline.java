public class Deadline extends Task{
    private String date;

    public Deadline(String name, boolean done, String date){
        super(name, done);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String summary(){
        String s = "D___";
        String d = this.getDone()? " ✓" : " X";
        return s + d + "___" + this.getTask() + "___" + this.getDate();
    }

    @Override
    public String toString(){
        String s = "[ D ]" + super.toString() + String.format("(%s)", date);
        return s;
    }
}
