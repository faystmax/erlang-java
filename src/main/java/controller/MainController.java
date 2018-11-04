package controller;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Setter;
import node.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Amosov Maxim - amosov.m@ext-system.com
 * @since 23.09.2018
 */
public class MainController {

    @FXML private ListView<String> windListView;
    @FXML private ListView<String> treeListView;
    @FXML private ListView<String> streetListView;
    @FXML private ListView<String> janitorListView;
    @FXML private ListView<String> policeListView;
    @FXML private ListView<String> studentListView;

    @Setter private Stage stage;

    private Wind wind;
    private Tree tree;
    private Street street;
    private Police police;
    private Janitor janitor;
    private Student student;

    @FXML
    protected void initialize() throws IOException {
        wind = new Wind(windListView);
        tree = new Tree(treeListView);
        street = new Street(streetListView);
        police = new Police(policeListView);
        janitor = new Janitor(janitorListView);
        student = new Student(studentListView);

        addScrollDownListeners(Arrays.asList(windListView, treeListView, streetListView, janitorListView, policeListView, studentListView));
    }

    /* Прокрутка вниз при добавлении */
    private void addScrollDownListeners(List<ListView<String>> listViews) {
        listViews.forEach(l -> l.getItems().addListener((ListChangeListener<String>) c -> l.scrollTo(c.getList().size() - 1)));
    }

    @FXML
    private void startWind() {
        execute(wind, Collections.singleton(tree.getNode().node()));
    }

    @FXML
    private void startTree() {
        execute(tree, Collections.singleton(street.getNode().node()));
    }

    @FXML
    private void startStreet() {
        execute(street, Collections.singleton(janitor.getNode().node()));
    }

    @FXML
    private void startJanitor() {
        execute(janitor, Collections.singleton(street.getNode().node()));
    }

    @FXML
    private void startPolice() {
        execute(police, Arrays.asList(street.getNode().node(), student.getNode().node()));
    }

    @FXML
    private void startStudent() {
        execute(student, Collections.singleton(street.getNode().node()));
    }

    private void execute(BaseNode node, Collection<String> addresses) {
        new Thread(() -> {
            try {
                node.start(addresses);
            } catch (Exception e) {
                Platform.runLater(() -> node.getListView().getItems().add("............................"));
                Platform.runLater(() -> node.getListView().getItems().add("Exit - exception during work of " + wind.getNode()));
            }
        }).start();
    }
}
