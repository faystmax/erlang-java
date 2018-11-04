package node;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public class Janitor extends BaseNode {

    static final String name = "janitor";

    public Janitor(ListView<String> listView) throws IOException {
        super(name, listView);
    }

    @Override
    public void start(Collection<String> addresses) throws Exception {
        super.start(addresses);
        doWork();
    }

    private void doWork() throws Exception {
        log("Sending send is_clean to street...");
        send(Street.name, "is_clean");
        String value = receiveAtomValue();
        switch (value) {
            case "yes":
                log("Received yes from street...");
                break;
            case "no":
                log("Received no from street...");
                log("Sending clean to street...");
                send(Street.name, "cleaning");
                break;
        }
        Thread.sleep(5000);
        doWork();
    }
}
