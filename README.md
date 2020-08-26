# mazeSolver

Implemented Maze Solver, a program in Java that helps a robot solve a maze by finding a path from the start node to the goal node.

A class project with skeleton code was provided. I implemented the AI search algorithms BFS and A-star search. I implemented the following methods:

AStarSearcher.java: boolean search()

BreadthFirstSearcher.java: boolean search()

State.java: ArrayList<State> getSuccessors(boolean[][] explored, Maze maze)
  
  A maze will be given in a text file as a matrix in which the start position is indicated by “S”, the goal position is indicated by “G”, walls are indicated by “%”, and empty positions where the robot can move are indicated by “ “. The outer border of the maze, i.e., the entire first row, last row, first column and last column will always contain “%” characters. A robot is allowed to move only horizontally or vertically, not diagonally.
  
  The program outputs the following:
1. the maze with a “.” in each square that is part of the solution path
2. the length of the solution path
3. the number of nodes expanded (i.e., the number of nodes removed from Frontier, including the goal node)
4. the maximum depth searched
5. the maximum size of Frontier at any point during the search.  

To compile and run:
$java FindPath maze search-method, where search-method can be either 'bfs' or 'astar'. maze is the input file. 
Example: java FindPath input.txt bfs
[sample input and output files provided for testing]
