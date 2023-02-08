package duke;

public class Recur extends Events implements Runnable  {

    //Change to time from - currentTime once it works
    private int mockRemainingTime;

    protected Recur() {

    }

    Recur(String description, String from, String to, int mockRemainingTime) {
        super(description, from, to);
        this.mockRemainingTime = mockRemainingTime;
    }

    Integer getMockRemainingTime() {
        return mockRemainingTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(mockRemainingTime);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.out.println("Scheduled task");
    }

}
