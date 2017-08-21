package com.test.test.command;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ChangeCommand implements Command {

    private TV tv;

    public ChangeCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.change(1);
    }

    @Override
    public void undo() {
        tv.change(0);
    }
}
