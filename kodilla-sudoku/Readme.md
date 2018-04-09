# Sudoku
Training project - application solving Sudoku

## Main features
* Application can solve Sudoku using algorithm chosen by the user. The following algorithms are supported: 
    * brute-force approach using iteration. To find all solutions, this approach in fact uses 
    recursion, but implemented with heap data structure and _while_ loop,
    * dancing links algorithm, as implemented by Rafał Szymański. The only changes to the original implementation were 
    made in order to fit the code into the project. Original code is available at: https://github.com/rafalio/dancing-links-java
* Additional algorithms can be easily added by implementing interfaces SudokuSolver and SolverSpec.

## Tools used
* The project was created with **IntelliJ IDEA** IDE.  
* It is a **Gradle** project.

## Licence for original dancing links implementation

The MIT License (MIT)

Copyright (c) 2014 Rafal Szymanski <rafalio>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

