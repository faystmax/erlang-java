package node;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public class Tree extends BaseNode {

    static final String name = "tree";

    public Tree(ListView<String> listView) throws IOException {
        super(name, listView);
    }

    @Override
    public void start(Collection<String> addresses) throws Exception {
        super.start(addresses);
        doWork();
    }

    private void doWork() throws Exception {
        String value = receiveAtomValue();
        if (value.equals("wind")) {
            log("Received wind from tree...");
            log("Sending leaves to street...");
            send(Street.name, "leaves");
        }
        doWork();
    }
}
