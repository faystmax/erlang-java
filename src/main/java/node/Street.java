package node;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public class Street extends BaseNode {

    static final String name = "street";

    private boolean fantic = false;
    private boolean leaves = false;

    public Street(ListView<String> listView) throws IOException {
        super(name, listView);
    }

    @Override
    public void start(Collection<String> addresses) throws Exception {
        super.start(addresses);
        doWork();
    }

    private void doWork() throws Exception {
        String value = receiveAtomValue();
        switch (value) {
            case "leaves":
                log("Received leaves from tree...");
                leaves = true;
                break;
            case "is_clean":
                log("Received is_clean from janitor...");
                log("Send " + (isClean() ? "yes" : "no") + " to janitor...");
                send(Janitor.name, isClean() ? "yes" : "no");
                break;
            case "cleaning":
                log("Received cleaning from janitor...");
                leaves = false;
                fantic = false;
                break;
            case "fantic":
                log("Received fantic from student...");
                fantic = true;
                break;
            case "is_fantic":
                log("Received is_fantic from police...");
                log("Send " + (fantic ? "yes" : "no") + " to police...");
                send(Police.name, fantic ? "yes" : "no");
                break;
        }
        doWork();
    }

    private boolean isClean() {
        return !fantic && !leaves;
    }
}
