package com.example;

import java.util.ArrayList;

import javafx.scene.control.TextField;

public class ApplicationLogic {

    private int[] numbers = new int[81];
    private int[] control = new int[81];
    
    public ApplicationLogic(ArrayList<TextField> list){
        //numbers is the actual altered list
        //control is to check which, number is fixed
        this.numbers = convertStringToNumber(list);
        this.control = convertStringToNumber(list);
    }

    public static int[] convertStringToNumber(ArrayList<TextField> list){
        //takes input of TextFields
        //transform it to int array
        int [] numbers = new int[81];

        for (int i = 0; i < list.size(); i++){

            String fieldContent = list.get(i).getText();
            //if textfield input is empty, adds 0 instead
            if (fieldContent.equals("")){
                numbers[i] = 0;
                continue;
            }

            numbers[i] = Integer.parseInt(fieldContent);
        }
        
        return numbers;
    }

    public  int [] letsSolve() {
        int index = 0;
        int loopCount = 0;

        //if 81 is reached sudoku is finished
        //last array index is 80 for 9x9 entries 
        while(index < 81){
            loopCount++;
            //function to go through the array
            //takes actual index and returns possibly altered index
            index = moveAndIncrement(index);
        }

        System.out.println("Iterations: " + loopCount);

        return this.numbers;
    }

    public int moveAndIncrement(int i){
        //if number is present in the control array
        //skip the position and returns next index
        if (this.control[i] != 0){
            return i += 1;
        }

        //increments the value of the active textfield
        //starting from 0 
        this.numbers[i]++;
        
        //if 1-9 didn't matched (value larger than nine)
        //correction mechanism is started 
        if (this.numbers[i] > 9){
            //sets actual value again to zero
            this.numbers[i] = 0;
            //index is diminished by one
            i -= 1;
            //diminishes index until non occupied 
            //controlfield is reached
            while(this.control[i] != 0){
                i -= 1;
            }
            //recursively calls the function until
            //next possible number is found
            i = moveAndIncrement(i);
        }

        // if it matches conditions (each number once a row/column/3x3 grid)
        // index is increased by one, continuing with next field
        if(checkConditions(i)){
            i++;
        }
        //otherwise restarting the cycle with the same textField
        return i;
    }

    public boolean checkConditions(int index){

        return checkRow(index) && checkColumn(index) && checkSquare(index) && 
                this.numbers[index] <= 9 && this.numbers[index] != 0;
    }

    public boolean checkRow(int index){
        int start = index/9*9;
        int end = start + 9;

        for (int i = start; i < end; i++){
            if(this.numbers[i] == this.numbers[index] && i != index){
                return false;
            }    
        }
        return true;
    }

    public boolean checkColumn(int index){
        int start = index % 9;
        
        for (int i = 0; i < 9; i++){
            int currentIndex = start + i *9;
            if(this.numbers[currentIndex] == this.numbers[index] && currentIndex != index){
                return false;
            }    
        }
        return true;
    }

    public boolean checkSquare(int index){
        int row = index % 9 / 3; //for field 20 row = 0     for field 80 row = 2
        int column = index / 9 / 3; //colum = 0             column 2

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j ++){
                int currentIndex = (row * 3) + i + (column * 3 + j) * 9;
                if(this.numbers[currentIndex] == this.numbers[index] && currentIndex != index){
                    return false;
                }    
            }
        }

        return true;
    }
    
}
