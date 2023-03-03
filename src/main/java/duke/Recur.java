package duke;

/**
 * Represents a recurring event that has a description, a start time, an end time, and a mock remaining time.
 * Implements the Runnable interface and provides a run method that sleeps for the mock remaining time.
 */
public class Recur implements Runnable {

    private String description;
    private String from;
    private String to;
    private int mockRemainingTime;

    protected Recur() {

    }
    /**
     * Constructor for Recur class
     * @param description description of the recurring event
     * @param from start time of the recurring event
     * @param to end time of the recurring event
     * @param mockRemainingTime a mock remaining time for the recurring event
     */
    Recur(String description, String from, String to, int mockRemainingTime) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.mockRemainingTime = mockRemainingTime;
    }

    /**
     * Gets the mock remaining time for the recurring event
     * @return mock remaining time as an integer
     */
    Integer getMockRemainingTime() {
        return mockRemainingTime;
    }

    /**
     * Gets the description of the recurring event
     * @return description of the recurring event as a string
     */
    String getDescription() {
        return this.description;
    }

    /**
     * Gets the start time of the recurring event
     * @return start time of the recurring event as a string
     */
    String getFrom() {
        return this.from;
    }

    /**
     * Gets the end time of the recurring event
     * @return end time of the recurring event as a string
     */
    String getTo() {
        return this.to;
    }

    /**
     * Sleeps for the mock remaining time.
     */
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
