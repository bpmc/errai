package org.jboss.workspace.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.json.client.JSONParser;
import org.jboss.workspace.client.framework.AcceptsCallback;
import org.jboss.workspace.client.rpc.MessageBusService;
import org.jboss.workspace.server.bus.Message;
import org.jboss.workspace.server.bus.MessageBus;
import org.jboss.workspace.server.bus.SimpleMessageBusProvider;
import org.mvbus.Configuration;
import org.mvbus.MVBus;
import org.mvbus.encode.engines.json.JsonDecodingEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MessageBusServiceImpl extends RemoteServiceServlet implements MessageBusService {
    private MessageBus bus;

    @Override
    public void init() throws ServletException {
        // just use the simple bus for now.  more integration options to come...
        bus = new SimpleMessageBusProvider().getBus();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    /**
                     * Wait 10 seconds.
                     */
                    while (true) {
                        sleep(1000 * 5);

                        System.out.println("transmitting test message...");

                        Map message = new HashMap();
                        message.put("CommandType", "Hello");
                        message.put("Name", "Mr. Server");

                        bus.store("org.jboss.workspace.WorkspaceLayout", message);
                       
                    }
                }
                catch (InterruptedException e) {

                }
            }
        };

        t.start();

        bus.subscribe("ServerEchoService", new AcceptsCallback() {
            public void callback(Object message, Object data) {
               


            }
        });
    }

    public void store(String subject, String message) {
        bus.store(subject, message);
    }

    public String[] nextMessage() {
        System.out.println("Polling...");
        Message m = bus.nextMessage(getId());
        if (m != null) {
            System.out.println("pushing message [subject:" + m.getSubject() + ";msg=" + m.getMessage() + "]");
            return new String[]{m.getSubject(), m.getMessage()};
        }
        else {
            return null;
        }
    }

    public void remoteSubscribe(String subject) {

        bus.remoteSubscribe(getId(), subject);
    }

    private String getId() {
        HttpServletRequest request = getThreadLocalRequest();
        HttpSession session = request.getSession();

        if (session.getAttribute("WSSessionID") == null) {
            session.setAttribute("WSSessionID", session.getId());
        }

        return (String) session.getAttribute("WSSessionID");
    }

    public String[] getSubjects() {
        return bus.getSubjects().toArray(new String[bus.getSubjects().size()]);
    }
}