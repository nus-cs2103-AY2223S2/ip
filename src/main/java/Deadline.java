public class Deadline extends Task {
    String deadline;

    public Deadline(String data, String deadline) {
        super(data);
        this.deadline = deadline;
    }

    @Override
    public String saveFormat() {
        StringBuilder temp = new StringBuilder("D\",\"" + super.saveFormat());
        temp.append("\",\"" + deadline);
        return temp.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("[D]");
        ret.append(super.toString());
        ret.append("(by: " + deadline + ")");
        return ret.toString();
    }
}
