package node;

import com.ericsson.otp.erlang.OtpErlangAtom;
import com.ericsson.otp.erlang.OtpErlangObject;
import com.ericsson.otp.erlang.OtpMbox;
import com.ericsson.otp.erlang.OtpNode;
import common.Const;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import lombok.Getter;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 04.11.2018
 */
public abstract class BaseNode {

    @Getter private String name;
    @Getter private OtpNode node;
    @Getter protected OtpMbox mbox;
    @Getter private ListView<String> listView;

    BaseNode(String name, ListView<String> listView) throws IOException {
        this.name = name;
        this.listView = listView;
        node = new OtpNode(getName(), Const.COOKIE);
    }

    public void start(Collection<String> addresses) throws Exception {
        mbox = node.createMbox(getName());
        log(getName() + " started...");
        addresses.forEach(this::ping);
    }

    String receiveAtomValue() throws Exception {
        return ((OtpErlangAtom) mbox.receive()).atomValue();
    }

    String receiveAtomValue(long timeout) throws Exception {
        OtpErlangObject object = mbox.receive(timeout);
        if (object == null) {
            return null;
        }
        return ((OtpErlangAtom) object).atomValue();
    }

    void send(String name, String value) {
        mbox.send(name, name, new OtpErlangAtom(value));
    }

    void log(String msg) {
        Platform.runLater(() -> listView.getItems().add(msg));
    }

    private void ping(String address) {
        log(mbox.getName() + ": Waiting " + address + "...");
        while (!mbox.ping(address, 10000)) {
            log(mbox.getName() + ": No reply after 10sec...");
        }
        log(mbox.getName() + ": ping successful to " + address + "...");
    }
}
