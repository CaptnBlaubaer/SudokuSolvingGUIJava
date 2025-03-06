package com.example;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUI {

    public BorderPane borderPane;
    public ArrayList<TextField> textFields;
    public ArrayList<Button> buttons;
    public Label label;

    public GUI(){
        this.borderPane = new BorderPane();
        this.textFields = createTextFields();
        this.buttons = createButtons();
        this.label = new Label();
        

        addElements();

        this.borderPane.setPrefSize(400,370);  
        
        setDefaultGrid();
    }

    private void addElements(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //generates a 9x9 matrix  in grid pane
        //each position contains a textField for sudoku input
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                grid.add(this.textFields.get(j*9+i), i, j);
            }
        }
        
        HBox hbox = new HBox ();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        //adds the buttons below the textfields
        hbox.getChildren().addAll(this.buttons);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(this.label, hbox);

        this.borderPane.setCenter(grid);
        this.borderPane.setBottom(vbox);        
    }

    //creates a list with 81 Textfields
    //which is used to store sudoku
    private static ArrayList<TextField> createTextFields(){
        ArrayList<TextField> list = new ArrayList<>();

        for (int i = 0; i < 81; i ++){
            list.add(new TextField());
        }
        return list;
    }

    //creates a list with the control buttons
    private static ArrayList<Button> createButtons(){
        ArrayList<Button> list = new ArrayList<>();

        //resets the sudoku grid to the default values
        list.add(new Button("Set Default"));
        //starts the solving mechanism 
        list.add(new Button("Let's solve"));
        //emptys the grid 
        list.add(new Button("Clear"));

        return list;
    }

    protected void setDefaultGrid(){
        //initializes the default sudoku grid
        int [] defaultArray = 
                {5,3,0,0,7,0,0,0,0,
                 6,0,0,1,9,5,0,0,0,
                 0,9,8,0,0,0,0,6,0,
                 8,0,0,0,6,0,0,0,3,
                 4,0,0,8,0,3,0,0,1,
                 7,0,0,0,2,0,0,0,6,
                 0,6,0,0,0,0,2,8,0,
                 0,0,0,4,1,9,0,0,5,
                 0,0,0,0,8,0,0,7,9};
        //adds the default values to each textfield
        for (int i = 0; i < defaultArray.length; i++){
            if (defaultArray[i] > 0){
                this.textFields.get(i).setText(defaultArray[i]+"");
            } else {
                this.textFields.get(i).setText("");
            }
        }
    }

    public BorderPane getBorderPane(){
        return this.borderPane;
    }

    public ArrayList<TextField> getTextFields(){
        return this.textFields;
    }

    public ArrayList<Button> getButtons(){
        return this.buttons;
    }

    public Label getLabel(){
        return this.label;
    }
}
