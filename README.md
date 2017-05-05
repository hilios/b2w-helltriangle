# Hell Triangle Challenge

#### Given a triangle of numbers, find the maximum total from top to bottom.

This software implements a solution strategy to the Hell Triangle game in Scala.

### Running

First you need to install the [SBT](http://www.scala-sbt.org/release/docs/Setup.html) to then run:

```shell
$ sbt run
```

#### Testing

You can run the test through the [SBT](http://www.scala-sbt.org/release/docs/Setup.html):

```shell
$ sbt test
# ...
[info] HellTriangleSpec:
[info] .apply
[info] - should create a proper graph like structure from a list of list
[info] #solve
[info] - should return the correct path and sum
[info] - should return the correct path and sum again
[info] Run completed in 1 second, 14 milliseconds.
[info] Total number of tests run: 3
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 3, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 7 s, completed May 5, 2017 1:18:52 PM
```

### Instructions

Example:
```
   6
  3 5
 9 7 1
4 6 8 4
```

In this triangle the maximum total is 6 + 5 + 7 + 8 = 26

An element can only be summed with one of the two nearest elements in the next row.
So the element 3 in row 2 can be summed with 9 and 7, but not with 1.

Choose the programming language you want… let us know about why is that your choice.

Besides the solution itself, write an automated test for your code (using a known framework
or just another function/method).

Your code will receive an (multidimensional) array as input.

The triangle from above would be:
``` 
example = [[6],[3,5],[9,7,1],[4,6,8,4]]
```

We are interested in your solution considering:
1. Correctness;
2. Readability;
3. The automated test;
4. Execution time;