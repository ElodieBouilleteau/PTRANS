package pqmethodvisu.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import pqmethodvisu.FXUtils;

public class VisualisationOverviewController {
	
	@FXML
	private SplitPane sp;
	
	@FXML
	private void addItemsOnCb()
	{
		System.out.println("test");
		ComboBox cb = FXUtils.getChildByID(sp, "comboboxFacteurPrincipal");
        ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
        cb.setItems(list);
	}
}
