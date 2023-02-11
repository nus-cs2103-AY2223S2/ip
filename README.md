# Duke <sub>copy</sub>

A simple task tracker. It's quick and responsive, easy-to-use, and **FREE**.\
Developed by: @lhy-hoyin 😎

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.


## Work In Progress
- [X] README
- [ ] Add more `Date` and `Time` parsing formats
- [ ] Include more JUnit tests
- [ ] Automate more using Gradle
   - [X] Run checkstyle after building

- [ ] Add more Flags to ...
   - [ ] load configuration files
   - [ ] open specific task list (requires multi- task lists feature)

## Extra Features Ideas
* Add `help` for in-application support (see [here](https://nus-cs2103-ay2223s2.github.io/website/se-book-adapted/projectDuke/index.html#c-help))
* Option to `list` all uncompleted (or completed) tasks
* Manage multiple task lists
* Have a config file to allow user preferences
   * Custom date/time input format
   * Custom separator for mass operations (e.g. mark, unmark, delete)
* Background auto-save

## Known / Potential Bugs
+ *FATAL*: Catch exceptions when parsing date/time (currently assume user always enter in correct format)
+ When GUI text becomes too long, the label text are `...` (including new text)

Report new bugs under [Issues](https://github.com/lhy-hoyin/ip/issues).