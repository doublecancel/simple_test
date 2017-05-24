package com.easyweb.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2017/5/24.
 */
public class ContentEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(Object source) {
        super(source);
    }

}
