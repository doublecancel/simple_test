package com.test.test.command;

/**
 * Created by Administrator on 2017/8/17.
 */
public class Control {


    private Command openCommand, closeCommand, changeCommand;

    public Control(Command openCommand, Command closeCommand, Command changeCommand) {
        this.openCommand = openCommand;
        this.closeCommand = closeCommand;
        this.changeCommand = changeCommand;
    }

    public void open(){
        openCommand.execute();
    }

    public void close(){
        closeCommand.execute();
    }

    public void change(int target){
        changeCommand.execute();
    }







}
