package com.example;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EventHandling {

    public GUI gui;
    public int [] numbers;
    public ArrayList<Button> buttons;
    public ArrayList<TextField> textFields;
    public ApplicationLogic logic;

    public EventHandling(GUI gui){
        this.gui = gui;
        this.numbers = new int[81];

        this.buttons = gui.getButtons();
        this.textFields = gui.getTextFields();
    }

    public void buttonActions(){
        this.buttons.get(0).setOnAction((event) -> reset());
        this.buttons.get(1).setOnAction((event) -> brutForceSudokuSolving());
        this.buttons.get(2).setOnAction((event) -> clear());
    }

    public void clear(){
        //adds for each textfield an empty string
        this.textFields.stream().forEach(each -> each.setText(""));
    }

    public void reset(){
        //resets the sudoku grid to the default values
        this.gui.setDefaultGrid();
    }

    public void brutForceSudokuSolving(){
        //initializes the application logic object
        //with the actual textfield inputs
        //returns an array with the solved sudoku
        int[] solvedSudoku = new ApplicationLogic(this.textFields).letsSolve();

        for (int i = 0; i < this.textFields.size(); i++){
           //adds the returned sudoku values to the respective text fields
            this.textFields.get(i).setText(solvedSudoku[i] + "");
        }
    }
}
