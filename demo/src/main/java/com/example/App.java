package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
    
    @Override
    public void start(Stage window){
        
        window.setTitle("Sudoku Solving Machine");
        GUI gui = new GUI();
        EventHandling action = new EventHandling(gui); 

        action.buttonActions();
       
        Scene mainView = new Scene(gui.getBorderPane());
        
        window.setScene(mainView);
        window.show();
    }

    public static void main(String[] args){
        App.launch();
    }
    
}