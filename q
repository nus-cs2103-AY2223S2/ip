[33mcommit 5dd7418cf18f8dc9275bc88dc7b27a3031f4d163[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: rockman007372 <rockman007372@gmail.com>
Date:   Thu Jan 19 14:41:38 2023 +0800

    Add Task class

[33mcommit d3e6e6e7828f8489fa40e38c0d006d248bd9452c[m[33m ([m[1;33mtag: Level-2[m[33m, [m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: rockman007372 <rockman007372@gmail.com>
Date:   Thu Jan 19 13:13:11 2023 +0800

    Add Add and List funtions and rename bot

[33mcommit 43554d2b23a510bc549f0be6581e8eb06e0980de[m
Author: rockman007372 <rockman007372@gmail.com>
Date:   Thu Jan 19 12:28:57 2023 +0800

    Rename bot and separate logics into functions

[33mcommit add54a86c96f52800498e834a35add90678f9894[m[33m ([m[1;33mtag: Level-1[m[33m)[m
Author: rockman007372 <rockman007372@gmail.com>
Date:   Thu Jan 19 12:17:13 2023 +0800

    Add greet, echo and exit features

[33mcommit 3b83789e9ed75b9498c05ee150c073f6492ec6ff[m
Author: Sean Leong <70213029+seanleong339@users.noreply.github.com>
Date:   Thu Dec 29 13:56:26 2022 +0800

    .gitignore: Fix ACTUAL.txt -> ACTUAL.TXT  (#73)
    
    text-ui-test/runtest.bat|sh scripts generate a file ACTUAL.TXT.
    However, .gitignore uses ACTUAL.txt, which means the generated
    file will not be ignored by Git on non-Windows OS.
    
    Let'description update .gitignore as ACTUAL.txt -> ACTUAL.TXT

[33mcommit e698798c0df9eb614fc9263cf735a609c94e58ba[m
Author: Damith C. Rajapakse <damith@gmail.com>
Date:   Thu Jul 29 20:29:39 2021 +0800

    README: Mention the project language level setting

[33mcommit 7bfb6e7cbdc8444041ecc338d625bac60014a630[m
Author: damithc <damithc@github>
Date:   Sat Jul 17 16:49:07 2021 +0800

    README.md: Tweak template text
    
    * Let'description replace 'Feature 1' with 'Feature-ABC'
      reason: some students kept the 'Feature 1' in the final UG.
    * Let'description tweak the template for features and usage
      reasons: to fix grammar, to distinguish between output and outcome

[33mcommit eabcfca4d05e76d33f071483bb1d00cc5bdd6f3c[m
Author: damithc <>
Date:   Sun Jan 17 12:25:53 2021 +0800

    README: Update setup instructions to match the latest IDE version

[33mcommit 6001e2eb859ce70a5b8ffad77d18410d28c9a9e5[m
Author: damithc <>
Date:   Tue Aug 25 17:23:26 2020 +0800

    Improve runtest.* scripts
    
    * Improve the javac command to work for multiple java files.
    * In runtest.bat, delete ACTUAL.TXT only if it exists.

[33mcommit 87eb8b818ae0e244a172420fdab8c46c45c43e0b[m
Author: Damith C. Rajapakse <damith@gmail.com>
Date:   Sat Jul 4 01:45:26 2020 +0800

    README.md: Fix typo of => or

[33mcommit ef3c8cd14cc5d9d0350eb62b06f640aab4119f8c[m
Author: damithc <damithch@damithch-mbp.comp.nus.edu.sg>
Date:   Mon May 25 00:03:55 2020 +0800

    Add text-ui-test files

[33mcommit 71d7ea84f3d61821643e505e532051fcc09ec2b1[m
Author: damithc <damithch@damithch-mbp.comp.nus.edu.sg>
Date:   Sun May 24 23:34:49 2020 +0800

    Remove generic tutorials
    
    Let'description remove generic tutorials from this repo as they are not specific
    to this project tempalte

[33mcommit 1416279436d1c0c7c0c87db5dd9fe75a3c72e5bb[m
Merge: 6f2ddfa 2697ae1
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Feb 12 15:39:44 2020 +0800

    Merge pull request #41 from jiachen247/patch-2
    
    Remove unnecessary override annotation

[33mcommit 2697ae1dd966ac2f3ec34eb8a14819b4fa9e6f70[m
Author: jiachen <jiachen247@users.noreply.github.com>
Date:   Tue Feb 11 15:13:03 2020 +0800

    Remove unnecessary override annotation
    
    Remove extra '@Override' at the start of the code example.

[33mcommit 6f2ddfaef13a62f650128050d8923eff99761f82[m
Merge: fae28ab 2ca6b28
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Feb 3 23:29:41 2020 +0800

    Merge pull request #35 from j-lum/xplatformjfx
    
    JavaFX tutorial: Support cross-platform JARs

[33mcommit 2ca6b28c8d2ebebfe001e1a618a5d5af4094e088[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Sep 20 16:05:59 2019 +0800

    JavaFX tutorial: Support cross-platform JARs
    
    The OpenJFX plugin expects applications to be modular and bundled
    with jlink, resulting in fat jars that are not cross-platform. Let'description
    manually include the required dependencies so that shadow can package
    them properly.

[33mcommit fae28ab89ea8846e56f2e6df33fbd6f9dd7fbea0[m
Merge: baa18a5 26a5013
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Sat Aug 31 16:43:08 2019 +0800

    Merge pull request #14 from j-lum/fxml-indent
    
    Change indentation in FXML samples to match AB-3

[33mcommit 26a5013d00ef2201cd6433f308f53dc52e277b76[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Sat Aug 31 16:40:20 2019 +0800

    Change indentation in FXML samples to match AB-3

[33mcommit baa18a5b60aee205f3593787bac4dc4a98a7687c[m
Merge: 999fa98 5df56a8
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Aug 16 16:59:58 2019 +0800

    Merge pull request #8 from j-lum/tutorial-fix
    
    Remove references to DukeStub

[33mcommit 5df56a8908679dfc9efc6fcca655b1f9de85d51e[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Fri Aug 16 16:58:43 2019 +0800

    Remove references to DukeStub

[33mcommit 999fa98979ab5be8233b7838451650d8345bb520[m
Merge: d2ffa00 8d6d6f4
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 15:18:12 2019 +0800

    Merge pull request #7 from j-lum/tutorial-fix
    
    Address issues #5 and #6

[33mcommit 8d6d6f4b95dd028ee97ba3df4d36b38ffc92d343[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 14:05:46 2019 +0800

    Add images to JavaFX tutorial 3

[33mcommit b6df1a32827a0d318ba241a10bfded88101daca3[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 13:59:56 2019 +0800

    Fix typo referring to `HelloWorld`

[33mcommit d2ffa00dbe45016b1c087a9a26e2d5be1d595ee4[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:35:46 2019 +0800

    Update Gradle tutorial to reflect new checkstyle config

[33mcommit f20d61019a2ea5ce166440b47e9a37a8bd373bb0[m
Merge: 5c47c23 bad66fc
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:30:37 2019 +0800

    Merge pull request #3 from j-lum/inline-javafx-tutorials
    
    Inline JavaFX tutorials

[33mcommit bad66fc459bc2173e26be31598e04c4315df7933[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Aug 12 14:24:27 2019 +0800

    Adapt JavaFX tutorial for Duke
    
    Rather than to have distinct tutorials building up to a Duke-like
    application, let'description merge the tutorials into Duke'description tutorials
    to enhance cohesiveness in the course material.
    
    We also merge the gradle/gradleless tutorials into one by providing a
    universal entry point to JavaFX in the style of AddressBook
    applications.
    
    Fix header levels in JavaFX Tutorial 1.
    
    Change code samples to use Duke.
    
    Add hints on required import statements.
    
    Add location hints to code snippets to help students find where to copy
    and paste them.
    
    Remove nitpicks to make the development process smoother.
    
    Fix usage of `Collections` to `FXCollections`.
    
    Replace image for JavaFX tutorial 3.
    
    Specify location to place images.
    
    Replace a screenshot that referred to the outdated package structure.
    
    Remove reference to DukeStub.

[33mcommit 5c47c238cef0369657cea1f3023dcc053aabe733[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 20:38:08 2019 +0800

    Specify checkstyle version
    
    Not specifying the checkstyle version causes it to fail with a cryptic
    error.

[33mcommit a2150c17028761c775b44368ede448060a7ce44e[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 20:10:10 2019 +0800

    textUiTestingTutorial.md: mention updating java/javac commands

[33mcommit 2d06f90400a17fd0ad95c0a68edf4917d12ee7ff[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:54:03 2019 +0800

    textUiTestingTutorial.md: add missing <br>

[33mcommit 5c491d3de4822b43f1db4c3bf806f81eae0c64a0[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:50:20 2019 +0800

    Add a tutorial on text UI testing

[33mcommit 53c04603712fd4132acd73091ffa37e29b7c0e70[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:15:04 2019 +0800

    Update list of contributors

[33mcommit 21af6a13f93c15964b20eb0ead90ef50ef70a42b[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:06:11 2019 +0800

    Fix typo in link to checkstyle config files

[33mcommit 8fe8afd9397509e450f821bc1f5524a3519ab488[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:29:40 2019 +0800

    README: use numbered list for steps

[33mcommit f20bff25cfb935b211b47aa3bcb5e690bbe614c0[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:26:04 2019 +0800

    Tweak tutorial text

[33mcommit 6a7120cbb6568134aef5d1d35e9596e4862acae4[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:53 2019 +0800

    Move Duke.java out of the package

[33mcommit 6bb6b9f6c525f5343fdc6dd3a43a086a11e9708e[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:08 2019 +0800

    .gitignore: add *.iml

[33mcommit 946f3bb509aa0710d45d820855ea3aa6cf096071[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Import base branch for students to start off
    
    As student may not have learnt about branching, let'description include a copy of
    the base branch into the main repository.

[33mcommit 245013d3ac98862fe7c5f52fee9a7b5c389fb7ab[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Initial commit
