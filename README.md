# OOP_EX2
Third assignment in "Object-Oriented Programming" course at Ariel University.
 
# First part: Ex2_1
In this part of assigment we were asked to make some text files and calculate all of their lines and measure time using three methods: <br>
 1. Regular, without any threads. <br>
 2. Using Threads. <br>
 3. Using TheardPool. <br>
 
 ## ThreadFile
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
 ## CallLines
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
 
 ### uml
 ![ex1_uml](https://user-images.githubusercontent.com/119599940/211843277-5caa8825-379a-4fca-8143-b4904ad55760.png)

### time calculation
we were used 3 diffrent ways, to calculte the amount of lines in 10,000 files.
- Method 1: Linear Time Calculation<br>

  In this method, we employed a straightforward approach to calculate the number of lines in each of the 10,000 files. The process involves iterating through each file   and counting the number of lines in each file individually. The line count for each file is then recorded and stored. This method has a linear time complexity of   
  O(n), where n represents the number of files. While it is easy to implement, it is not the most efficient method as it does not account for the possibility of  p
  arallelization or optimization opportunities.
  It's worth noting that, real-world performance will vary based on several factors such as the type of file system and access method used, disk I/O operation and so 
  on. Therefore, this calculation is only considered as a rough estimate of time complexity of this method.

- second way: 


![createfile](https://user-images.githubusercontent.com/119599940/211847818-ec276700-85c2-48ee-9726-3e862e62b59e.png)

