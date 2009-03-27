package org.jboss.workspace.client.listeners;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.workspace.client.widgets.WSTab;
import org.jboss.workspace.client.widgets.WSModalDialog;
import org.jboss.workspace.client.layout.WorkspaceLayout;
import org.jboss.workspace.client.framework.AcceptsCallback;


public class TabCloseListener implements ClickListener, AcceptsCallback {
    /**
     * The reference to the tab.
     */
    private WSTab tab;

    /**
     * The reference to the root GuvnorLayout object.
     */
    private WorkspaceLayout guvWorkspace;

    public TabCloseListener(WSTab tab, WorkspaceLayout guvWorkspace) {
        this.tab = tab;
        this.guvWorkspace = guvWorkspace;
    }

    public void onClick(Widget sender) {
        /**
         * Check to see if the current tool has a modified flag.
         */
        if (tab.getPacket().isModifiedFlag()) {

            /**
             * Create a new warning Dialog
             */
            WSModalDialog dialog = new WSModalDialog();

            dialog.getOkButton().setText("Close Anyways");
            dialog.getCancelButton().setText("Don't Close");

            /**
             * Initialize the dialog
             */
            dialog.ask(
                    "You have unsaved changes, closing this dialog" +
                            " without saving will cause you lose date.",
                    this);


            /**
             * Flash the tab
             */
            tab.blink();    


            /**
             * Prompt the user.
             */
            dialog.showModal();
        }
        else {
            close();
        }
    }

    /**
     * The callback receiver method for the warning dialog box.
     * @param message
     */
    public void callback(String message) {
        /**
         * If the user pressed okay, close the tab.
         */
        if (AcceptsCallback.MESSAGE_OK.equals(message)) {
            close();
        }
        else {
            /**
             * Do nothing.
             */
        }

        tab.stopAnimation();
    }

    /**
     * Close the tab.
     */
    public void close() {
        guvWorkspace.closeTab(tab.getPacket());
    }
}