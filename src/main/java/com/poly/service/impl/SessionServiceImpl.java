package com.poly.service.impl;

import com.poly.model.Session;
import com.poly.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class SessionServiceImpl implements SessionService {

    public static List<Session> listSession=new ArrayList<>();
    @Override
    public Object get(String key) {
        Object value = null;
        if(key.isEmpty()||key=="") {
            return null;
        }
        for(Session item:listSession) {
            if(key.equalsIgnoreCase(item.getKey())) {
                value=item.getValue();
            }
        }
        return value;
    }

    @Override
    public void set(String key, Object value) {
        for(int i=0;i<listSession.size();i++) {
            Session item=listSession.get(i);
            if(key.equalsIgnoreCase(item.getKey())) {
                listSession.remove(i);
            }
        }
        Session session=new Session();
        session.setKey(key);
        session.setValue(value);
        listSession.add(session);
    }
    @Override
    public void clear(String key) {
        for(Session session:listSession) {
            if(key.equals(session.getKey())) {
                listSession.remove(session);
                break;
            }
        }
    }
}
