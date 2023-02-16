package duke;

public enum Priority {
        LOW, MEDIUM, HIGH;

        @Override
        public String toString() {
            String result;
            switch (this) {
            case LOW:
                result = "priority: LOW";
                break;
            case MEDIUM:
                result = "priority: MEDIUM";
                break;
            case HIGH:
                result = "priority: HIGH";
                break;
            default:
                result = null;
                break;
            }
            return result;
        }

        public static Priority parsePriority(String priorityLevel) {
            switch (priorityLevel) {
            case "LOW":
                return Priority.LOW;
            case "MEDIUM":
                return Priority.MEDIUM;
            case "HIGH":
                return Priority.HIGH;
            default:
                return null;
            }
        }
}
