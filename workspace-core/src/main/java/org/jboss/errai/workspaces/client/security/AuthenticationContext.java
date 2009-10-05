package org.jboss.errai.workspaces.client.security;

import java.util.Set;

public interface AuthenticationContext {
    public Set<Role> getRoles();
    public void logout();
    public boolean isValid();
}