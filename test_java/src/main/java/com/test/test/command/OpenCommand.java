package com.test.test.command;

/**
 * Created by Administrator on 2017/8/17.
 */
public class OpenCommand implements  Command {

    private TV tv;


    public OpenCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.open();
    }

    @Override
    public void undo() {
        tv.close();
    }
}
