# Sudoku Solver

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A simple program with GUI for solving [Sudoku](https://en.wikipedia.org/wiki/Sudoku) using backtracking.

Program is written in Kotlin and uses the [JavaFX library](https://openjfx.io/).

## Usage

Click on ```Controls->CreateGrid``` to create a new empty grid board. You can edit individual squares by clicking on them and pressing a number on your keyboard. You can delete a number using backspace.

Once you're done with editing, press ```Controls->Solve``` and the program will automatically solve your board and visualize the process.

## Installation

Clone the repo and use ```mvn clean package``` to create a .jar file. You need to have a working JRE (version 1.8 or higher) in order to run the program. 
