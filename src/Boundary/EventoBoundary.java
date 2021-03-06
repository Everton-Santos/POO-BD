package Boundary;

import Control.EventoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.format.DateTimeFormatter;

public class EventoBoundary extends Application {
    private TextField txtNome = new TextField();
    private TextField txtData = new TextField();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private EventoControl control = new EventoControl();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane principal = new BorderPane();
        GridPane grid = new GridPane();
        principal.setTop(grid);

        grid.add(new Label("Nome"), 0, 0);
        grid.add(txtNome, 1, 0);
        grid.add(new Label("Data"), 0, 1);
        grid.add(txtData, 1, 1);
        grid.add(btnAdicionar, 0, 2);
        grid.add(btnPesquisar, 1, 2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateStringConverter ldc =
                new LocalDateStringConverter(formatter, null);

        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(
                txtData.textProperty(), control.dataProperty(), ldc);

        principal.setCenter(control.getTable());

        btnAdicionar.setOnAction( e -> control.adicionar());

        btnPesquisar.setOnAction(e -> control.pesquisar());

        Scene scn = new Scene(principal, 300, 200);
        stage.setScene(scn);
        stage.setTitle("Gestão de Eventos");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(EventoBoundary.class, args);
    }
}
