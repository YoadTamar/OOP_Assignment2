# OOP_EX2
Third assignment in "Object-Oriented Programming" course at Ariel University.
 
## Ex2_1
In this part of assigment we were asked to make some text files and calculate all of their lines and measure time using three methods: <br>
 1. Regular, without any threads. <br>
 2. Using Threads. <br>
 3. Using TheardPool. <br>
 
 ### ThreadFile
 Extends the `Thread` class. A thread in Java is the direction or path that is taken while a program is being executed. 
 
 myThread has 2 private variables:
 - `String file`
 - `int lines`
 
 in addition, `ThreadFile` has the following methods:<br>
 - `ThreadFile`
 - `getLines()`
 - `run()`
 
 
 #### `ThreadFile(String file)`
 Assigns the name of the file, and creates thread with that name.
 
 #### `getLines()`
 Returns the variable `lines`.
 
 #### `run()`
 Executing the thread.<br>
 Run calculating the number of lines with the exact file name given in the constractor <br>
 and initializing the variable `lines` to the method result.
 
 ***
 ### CallLines
 Implements the Callable interface. A task that returns a result and may throw an exception. Implementors define a single method with no arguments called call.<br>
 
  CallLines has 2 private variables:
 - `String file`
 
 in addition, `CallLines` has the following methods:<br>
 - `CallLines`
 - `call()`

 #### `CallLines(String file)`
 Assigns the name of the file, and creates thread with that name .
 
 #### `calcLines()`
 A private method for calculating the number of lines with the exact file name given in the constractor <br>
 and initializing the variable `lines` to the method result.
 
 #### `call()`
 calculating the number of lines with the exact file name given in the constractor <br>
 and returns number of lines of the file.
