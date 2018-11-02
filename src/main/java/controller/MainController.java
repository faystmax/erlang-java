package controller;

import com.ericsson.otp.erlang.OtpNode;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 23.09.2018
 */
public class MainController {


    @Setter private Stage stage;

    @FXML
    protected void initialize() throws IOException {
        OtpNode node = new OtpNode("main");
        node.setCookie("lab3");
        if (node.ping("wind@max.wifi.astu", 2000)) {
            System.out.println("wind is up");
        } else {
            System.out.println("remote is not up");
        }
    }

}
