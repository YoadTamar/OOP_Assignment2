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

   - In this method, we employed a straightforward approach to calculate the number of lines in each of the 10,000 files. The process involves iterating through each 
   file and counting the number of lines in each file individually. The line count for each file is then recorded and stored. This method has a linear time complexity 
   of O(n), where n represents the number of files. While it is easy to implement, it is not the most efficient method as it does not account for the possibility of  p
   arallelization or optimization opportunities.
   It's worth noting that, real-world performance will vary based on several factors such as the type of file system and access method used, disk I/O operation and so 
   on. Therefore, this calculation is only considered as a rough estimate of time complexity of this method.

- Method 2: Multi-threaded Calculation

  - In this method, we utilized multi-threading to calculate the number of lines in each of the 10,000 files. For each file, we created a separate thread, with each 
  thread being responsible for counting the number of lines in one file. This method is more effective than the linear way, as all the threads are able to work in an 
  almost parallel manner. This approach allows for concurrent execution of multiple threads, reducing the overall processing time. However, the actual performance 
  improvement will depend on the system resources, such as the number of CPU cores, and the operating system's ability to schedule threads efficiently.
  
- Method 3: Thread Pool Calculation

  - In this method, we utilized a thread pool to calculate the number of lines in each of the 10,000 files. We created a thread pool with a fixed size equal to the 
  number of files, where each thread counted the lines of one file. This method improved performance over the linear method by utilizing a fixed number of threads and 
  efficiently using system resources. However, actual performance may not be as good as the multi-threaded method, depending on the resources of the system. 

### conclusions: 
- Based on the three methods described for calculating the number of lines in 10,000 files, we can conclude the following:
  - Method 1 is straightforward but not efficient.
  - Method 2 utilizes multi-threading to achieve almost parallel execution.
  - Method 3 utilizes a thread pool and improves over linear method but may not perform as well as method 2.

as we can see in the example image:<br>

![createfile](https://user-images.githubusercontent.com/119599940/211847818-ec276700-85c2-48ee-9726-3e862e62b59e.png)

## The second part – Ex2_2:
In this part of the assigment we had to create a new task type that can be used in a ThreadPool,
where tasks have a priority level and are executed accordingly by the ThreadPool. This would allow for prioritized task execution,
where tasks with higher priority are executed first.   
