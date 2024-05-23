package org.example.iss_hospital_v2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.iss_hospital_v2.Model.Comanda;
import org.example.iss_hospital_v2.Repo.ComandaRepo;

import java.util.List;
import java.util.stream.Collectors;

public class ComenziPerSectieController {
    @FXML
    private TextField sectieIdField;

    @FXML
    private TableView<Comanda> tableComenzi;

    @FXML
    private TableColumn<Comanda, Integer> colId;

    @FXML
    private TableColumn<Comanda, Integer> colSectieId;

    @FXML
    private TableColumn<Comanda, Integer> colMedicamentId;

    @FXML
    private TableColumn<Comanda, Integer> colCantitate;

    @FXML
    private TableColumn<Comanda, Boolean> colOnorata;

    private ComandaRepo comandaRepo;

    public void initialize() {
        comandaRepo = new ComandaRepo();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSectieId.setCellValueFactory(new PropertyValueFactory<>("sectieId"));
        colMedicamentId.setCellValueFactory(new PropertyValueFactory<>("medicamentId"));
        colCantitate.setCellValueFactory(new PropertyValueFactory<>("cantitate"));
        colOnorata.setCellValueFactory(new PropertyValueFactory<>("onorata"));
    }

    @FXML
    private void handleCautaComenzi() {
        try {
            int sectieId = Integer.parseInt(sectieIdField.getText());
            List<Comanda> comenzi = comandaRepo.getAll()
                    .stream()
                    .filter(comanda -> comanda.getSectieId() == sectieId)
                    .collect(Collectors.toList());

            ObservableList<Comanda> comenziObservable = FXCollections.observableArrayList(comenzi);
            tableComenzi.setItems(comenziObservable);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
