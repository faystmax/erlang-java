package node;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public class Student extends BaseNode {

    static final String name = "student";

    private boolean cleaned = true;

    public Student(ListView<String> listView) throws IOException {
        super(name, listView);
    }

    @Override
    public void start(Collection<String> addresses) throws Exception {
        super.start(addresses);
        doWork();
    }

    private void doWork() throws Exception {
        log("Sending fantic to street...");
        send(Street.name, "fantic");
        String value = receiveAtomValue(5000);
        if ("penalty".equals(value)) {
            log("Received penalty from police...");
            Thread.sleep(3000);
        } else if (value == null) {
            log("Got nothing from Police");
        }
        doWork();
    }
}
