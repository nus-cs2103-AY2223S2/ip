package jarvis.duration;

import java.util.LinkedList;
import java.util.List;

/**
 * Container class representing duration.
 */
public class Duration {
    private int days = 0;
    private int hours = 0;
    private int minutes = 0;

    public Duration setDays(int days) {
        this.days = Math.max(0, days);
        return this;
    }

    public Duration setHours(int hours) {
        this.hours = Math.max(0, hours);
        return this;
    }

    public Duration setMinutes(int minutes) {
        this.minutes = Math.max(0, minutes);
        return this;
    }

    public boolean isEmpty() {
        return days == 0
                && hours == 0
                && minutes == 0;
    }

    /**
     * Serializes this duration into a string.
     *
     * @return Serialized string.
     */
    public String serialize() {
        String[] parts = {
                String.valueOf(days),
                String.valueOf(hours),
                String.valueOf(minutes)
        };
        return String.join(" ", parts);
    }

    /**
     * Deserializes a string into a Duration object.
     *
     * @param str Serialized string.
     * @return Deserialized Duration object.
     */
    public static Duration deserialize(String str) {
        Duration duration = new Duration();
        if (str == null) {
            return duration;
        }
        String[] parts = str.split("\\s+");
        switch (parts.length) {
        case 3:
            duration.setMinutes(Integer.parseInt(parts[2]));
            // Fallthrough
        case 2:
            duration.setHours(Integer.parseInt(parts[1]));
            // Fallthrough
        case 1:
            duration.setDays(Integer.parseInt(parts[0]));
            // Fallthrough
        default:
            // Do nothing
        }
        return duration;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "None";
        }

        List<String> parts = new LinkedList<>();
        if (days > 0) {
            parts.add(days + " days");
        }
        if (hours > 0) {
            parts.add(hours + " hours");
        }
        if (minutes > 0) {
            parts.add(minutes + " minutes");
        }
        return String.join(", ", parts);
    }
}
