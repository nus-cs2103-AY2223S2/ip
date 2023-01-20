package formatters;

/**
 * Various responses of UwU_TaskMaster
 */

public enum Response {

    INVALID_COMMAND {
        @Override
        public String toString() {
            return "Ohh nyoo I cannyot ^w^ undewstand *cries* what you mean, c-can you twy again?";
        }
    },
    MISSING_DEADLINE_DETAILS {
        @Override
        public String toString() {
            return "Oh nyoo did you fowget t-to add a date t-to this deadline?!? *blushes*";
        }
    },

    MISSING_EVENT_DETAILS {
        @Override
        public String toString() {
            return "Oh nyoo did you fowget t-to add a /from or /to t-to this event?!? *blushes*";
        }
    },

    MISSING_TASK_NAME {
        @Override
        public String toString() {
            return "You didnt teww me the name of youw t-task UwU";
        }
    },

    COMPLETED_TASK {
        @Override
        public String toString() {
            return "Wowww c-congwatuwations on compweting youw task!!";
        }
    },

    INCOMPLETE_TASK {
        @Override
        public String toString() {
            return "omg nyoooooo why you have nyot compwete youw t-task :(";
        }
    },


    NO_TASK {
        @Override
        public String toString() {
            return "Wooks wike *screams* you do not have any OwO tasks!";
        }
    },

    EVENT_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw e-eevent has been successfuwwy added!";
        }
    },

    DEADLINE_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw d-deadline has been successfuwwy added!";
        }
    },

    TODO_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw t-to-do has been successfuwwy added!";
        }
    },

}

