# User Guide

There are a couple of ways to run duke: 

- Go to the root directory. Then do ./gradlew build. After the test cases has
  passed, do ./gradlew run
- The other way is to use the run scripts written in bash
- The last way is to manually navigate to src/main/java/duke folder. Complie
  using javac. Then go back to src/main/java. To run, do java Duke duke. 

## Features 

There are 5 different types of task that Duke handles: 

- A normal task. For example "borrow book" 
- A todo task. For example "todo borrow book"
- A deadline task. For example "deadline return book /by Sunday"
- An event task. For example "event project meeting /from Mon 2pm /to 4pm"

** The new customised task added is a recur task. Please follow the
instructions below to test it out**

1. To add a recur task, type "recur", followed by a single word task name, and
   the period of repetition in ms.
2. For example, if I want a recur task study to run 1s periodically, I will
   type "recur study 1000".
3. You should see the recur task popping up. You will be able to do other
   tasks meanwhile since the recur task runs on a different thread. 
4. To delete the recur task, type "recur delete", followed by the nth recur
   task that you intend to delete. The list starts from index 1.
5. For example, if I want to delete the first recur task, I will type "recur
   delete 1".
