
The idea of this exercise was try to make it simple and quick.

The main components are as it follows:

INPUT COMMAND LINE -> COMMAND PATTERN  ->  BUSINESS LOGIC  -> STATE

Approach:

    The main idea is:

     1) to get what it is wanted from the command line
     2) to interpret the intent using a command pattern
     3) the command will be used to execute the logic associated
     4) the principle we tried to implement here is that whatever the logic will be, it will take the previous state,
        create a new one according to the what is required, store it and discard the previous one


     COMMAND LINE ------------> ACTION ------> |--------|
                                               |        |
                                               | LOGIC  | -------> NEXT STATE
                                               |        |
                        PREVIOUS STATE ------> |--------|

    I've tried to explore the idea  of making this approach very evident in the code.

Principles:

    1)  The commands gives us the flexibility to decouple the logic from the way we introduce the data in the application. Adding another protocol to get the commands would be easy

    2)  This idea of the the Commands and this "old state"that is discarded or disposed when a new state is created enables funny scenarios like "replay" or compute the current state based on the actions.

    3)  I have implemented the logic as functions because I wanted to explore them and also because I like the future idea of the composability doing something like moves.andThen(right).
    But an approach using static methods would be nice as well following the main idea

    4)  I've tried to reduce the logical "entities" to a couple of ancillary structures like Position, Tuple or the Direction enum

    5)  Also I've tried to reduce the state to the minimum expression like a Tuple without any kind of additional structure on top

    5)  I've tried not to use any additional library for example to read from command line, or utilities. Just the basic stuff

    In regards of the rest I think the code is easy to read and follow.

TODOs

    - I think there are plenty of things that I could have done. Simplify the commands to make them less verbose and easy to use with and without parameters
    - The idea of the composability of the actions or composed commands is really nice
    - Commands batches is also a good idea

     ... etc

Thanks!




