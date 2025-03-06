package com.example;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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
        this.gui.getLabel().setText("");
    }

    public void reset(){
        //resets the sudoku grid to the default values
        this.gui.setDefaultGrid();
        this.gui.getLabel().setText("");
    }

    public void brutForceSudokuSolving(){
        //initializes the application logic object
        //with the actual textfield inputs
        //returns an array with the solved sudoku
        //measures duration
        long before = System.currentTimeMillis();

        int[] solvedSudoku = new ApplicationLogic(this.textFields).letsSolve();

        long after = System.currentTimeMillis();

        for (int i = 0; i < this.textFields.size(); i++){
           //adds the returned sudoku values to the respective text fields
            this.textFields.get(i).setText(solvedSudoku[i] + "");
        }

        this.gui.getLabel().setText("Duration: " + (after - before) + " milliseconds");
    }
}
