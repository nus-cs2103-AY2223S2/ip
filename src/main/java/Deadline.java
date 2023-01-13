public class Deadline extends Task{
    private String date;

    public Deadline(String date, String name){
        super(name);
        this.date = date;
    }

    @Override
    public String toString(){
        String s = "[ D ]" + super.toString() + String.format("(%s)", date);
        return s;
    }
}
