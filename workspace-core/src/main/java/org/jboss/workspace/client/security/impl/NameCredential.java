package org.jboss.workspace.client.security.impl;

import org.jboss.workspace.client.security.Credential;

/**
 * Created by IntelliJ IDEA.
 * User: christopherbrock
 * Date: 26-Aug-2009
 * Time: 4:46:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class NameCredential implements Credential {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}