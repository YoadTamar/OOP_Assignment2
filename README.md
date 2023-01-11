# OOP_EX2
Third assignment in "Object-Oriented Programming" course at Ariel University.

In this assigment we were asked to make some text files and calculate all of their lines and measure time using three methods: <br>
 1. Regular, without any threads. <br>
 2. Using Threads. <br>
 3. Using TheardPool. <br>
 

 
 ## ThreadFile
 Extends the `Thread` class. A thread in Java is the direction or path that is taken while a program is being executed. 
 
 myThread has 2 private variables:
 - `String fileName`
 - `int lines`
 
 in addition, `ThreadFile` has the following methods:<br>
 - `ThreadFile`
 - `calcLines() `
 - `getLines()`
 - `run()`
 
 
 #### `ThreadFile(String fileName)`
 Assigns the name of the file, and creates thread with that name .
 
 #### `calcLines()`
 A private method for calculating the number of lines with the exact file name given in the constractor <br>
 and initializing the variable `lines` to the method result.
 
 #### `getLines()`
 Returns the variable `lines`.
 
 #### `run()`
 Executing the thread.<br>
 In our case, run() will call the help-function - `calcLines`
 
 ***
 ## ThreadFilePool
 Implements the Callable interface. A task that returns a result and may throw an exception. Implementors define a single method with no arguments called call.<br>
 This class cause is to create threadpool.
 
  ThreadFilePool has 2 private variables:
 - `String fileName`
 - `int lines`
 
 in addition, `ThreadFilePool` has the following methods:<br>
 - `ThreadFilePool`
 - `calcLines() `
 - `call()`

 #### `ThreadFilePool(String fileName)`
 Assigns the name of the file, and creates thread with that name .
 
 #### `calcLines()`
 A private method for calculating the number of lines with the exact file name given in the constractor <br>
 and initializing the variable `lines` to the method result.
 
 #### `call()`
 Calls the help-function `calcLines` and returns number of lines of the file.
