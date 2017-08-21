package com.test.test.command;

/**
 * Created by Administrator on 2017/8/17.
 */
public class CloseCommand implements Command {

    private TV tv;

    public CloseCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.close();
    }

    @Override
    public void undo() {
        tv.open();
    }




}
