package node;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public class Police extends BaseNode {

    static final String name = "police";

    public Police(ListView<String> listView) throws IOException {
        super(name, listView);
    }

    @Override
    public void start(Collection<String> addresses) throws Exception {
        super.start(addresses);
        doWork();
    }

    private void doWork() throws Exception {
        log("Sending is_fantic  to street...");
        send(Street.name, "is_fantic");
        String value = receiveAtomValue();
        switch (value) {
            case "yes":
                log("Received yes from street...");
                log("Sending penalty to student...");
                send(Student.name, "penalty");
                break;
            case "no":
                log("Received no from street...");
                break;
        }
        Thread.sleep(3000);
        doWork();
    }
}
