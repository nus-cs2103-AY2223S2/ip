package duke;

public class Recur /*extends Events*/ implements Runnable  {

    //Change to time from - currentTime once it works
    private String description;
    private String from;
    private String to;
    private int mockRemainingTime;

    protected Recur() {

    }

    Recur(String description, String from, String to, int mockRemainingTime) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.mockRemainingTime = mockRemainingTime;
    }

    Integer getMockRemainingTime() {
        return mockRemainingTime;
    }

    String getDescription() {
        return this.description;
    }

    String getFrom() {
        return this.from;
    }

    String getTo() {
        return this.to;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(mockRemainingTime);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return "recur";
    }

}
