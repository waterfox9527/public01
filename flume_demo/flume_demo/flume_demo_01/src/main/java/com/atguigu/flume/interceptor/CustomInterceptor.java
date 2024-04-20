package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

public class CustomInterceptor implements Interceptor {


    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        if ((body[0] >= 'a' && body[0] <= 'z') || (body[0] >= 'A' && body[0] <= 'Z')){
            event.getHeaders().put("type","letter");
        }
        if (body[0] >= '0' && body[0] <= '9'){
            event.getHeaders().put("type","number");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public void configure(Context context) {
        }

        @Override
        public Interceptor build() {
            return new CustomInterceptor();
        }
    }
}
