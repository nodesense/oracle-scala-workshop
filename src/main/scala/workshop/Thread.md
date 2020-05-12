Application
    .exe/unit/linux executable

Process
    runtime context
    memory reserved
    instuctions are loaded in memory

Thread - Execution Context
         CPU - Execute instruction one after another
         
         Paused
         Resumed
         Sleep mode
         
Computation
        - CPU/CPU Cores
        
I/O
        - Input/Output
        - Network
        - File System
        - user input
        
        
Sync
    Blocking call
    if read the file,
     if reading takes 5 sec,
       CPU is idle here
Async 
    Non-Blocking call
    doesn't wait for result to come
    works based on call back in JS
    Java/ExecutionContext/Task/Future
    Thread pool/worker threads
         
Main Thread - already there
        Primary Thread
        dedicated to Process
        
-- low level threading
Worker Thread - addtional thread, created on demand
                - Java Thread, Runnable etc
Thread Pool - Libraries 
            Set of pre-created thread
            it will be idle
            in programs,
                get a thread from pool
                exuecute the task
                release the thread to pool
                
Application Libraries
    Spring Boot - reserve set of threads
    Quartz - reserve set of threads
    Akka - reserve set of threads
    Custom Thread pool - reserve set of threads
    
    These threads are not shared amoung
                
ExecutionContext
    JDK/JRE specification
     Set of thread pool managed by JVM instead of libraries
     
     Libraries/Application, can submit a task / Future
     to the execution context
     
     ExecutionContext shall queue all the Future/task
     
     ExecutationContext shall pick thread from thread pool,
      pick a Future from future/task queue, assign hte future to
      thread to execute.
      
      Future/Task, is performed,
          Successfully completed
          Failed
          
          
      The caller [application/library code] 
         sync - can either wait for the result 
         async - can do other job, till the result comes
                 
                 -- wait for another request
                 -- process anotehr request
                 -- once result is ready, a callback is used

Future + Task
Promise - deprecated