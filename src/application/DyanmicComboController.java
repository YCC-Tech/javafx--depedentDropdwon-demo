//check with while
//6_3_2021 only work well with UTYCC (not yet know reason may be multiple listener on one combo box( it may be solved with db
//6_3_2021 well done uni+year+major
package application;

import java.net.URL;
//import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import database.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class DyanmicComboController implements Initializable {

	@FXML
	private ComboBox<String> cobUniversity;

	@FXML
	private ComboBox<String> cobYear;

	@FXML
	private ComboBox<String> cobMajor;
	@FXML
	private ComboBox<String> cobRegion;

	@FXML
	private ComboBox<String> cobLongTownship;
	@FXML
	private ComboBox<String> cobNumber;

	@FXML
	private ComboBox<String> cobShortTownship;

	@FXML
	private Label lblUniveristy;

	@FXML
	private Label lblYear;

	@FXML
	private Label lblMajor;

	@FXML
	private Label lblNumber;

	@FXML
	private Label lblShortTownship;

	@FXML
	private Label lblRegion;

	@FXML
	private Label lblLongTownship;

	int uniId;

	int regionId;

	TestUtil testingUtil = new TestUtil();

	// ObservableList<String> uniList = FXCollections.observableArrayList();
	ObservableList<String> yearList = FXCollections.observableArrayList();
	ObservableList<String> majorList = FXCollections.observableArrayList();

	// to process change effect in processMethod , set some string as initialize
	// I am not sure to use all combo in initialize method

	@FXML
	void processLongTownship(ActionEvent event) {

		lblLongTownship.setText(cobLongTownship.getValue());
	}

	@FXML
	void processMajor(ActionEvent event) {

		lblMajor.setText(cobMajor.getValue());
	}

	@FXML
	void processShortTownship(ActionEvent event) {

		lblShortTownship.setText(cobShortTownship.getValue());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// choose university + academic year + major

		// set university list from db to UI

		try {
			cobUniversity.setItems(testingUtil.getUniList("select * from university;"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// listen chosen university
		cobUniversity.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observeUni, String oldUni, String newUni) {
				lblUniveristy.setText(newUni);
				try {
					uniId = testingUtil.getUniId(newUni);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set academic year according to chosen university name

				try {
					cobYear.setValue("Choose Year");
					cobYear.setItems(
							testingUtil.getYearList("select * from attendanceyear where uniId='" + uniId + "';"));

				} catch (SQLException e) {

					e.printStackTrace();
				}
				// choose academic year(listen chosen year)
				cobYear.valueProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observeYear, String oldYear, String newYear) {
						lblYear.setText(newYear);

						try {
							cobMajor.setValue("Choose Major");
							cobMajor.setItems(testingUtil.getMajorList("select majorName from major where yearName='"
									+ newYear + "' and uniId='" + uniId + "';"));

							//
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

			}
		});

		// choose region code + township short name

		// set region code list from db to UI

		try {
			cobNumber.setItems(testingUtil.getRegionCodeList("select regionId from region;"));
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// listen chosen region code
		cobNumber.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observeValue, String oldNo, String newNo) {
				lblNumber.setText(newNo);
				int changedRegionId = Integer.parseInt(newNo);
				// set township short name according to chosen region code
				try {
					cobShortTownship.setItems(testingUtil.getTownshipShortList(
							"select townshipShortName from township where regionId='" + changedRegionId + "';"));

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// choose region name + township full name

		// set region list from db to UI

		try {
			cobRegion.setItems(testingUtil.getRegionNameList("select regionName from region;"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// choose region and listen chosen region code
		cobRegion.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observeRegion, String oldRegion, String newRegion) {
				lblRegion.setText(cobRegion.getValue());
				try {
					regionId = testingUtil.getRegionId(newRegion);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// set township name according to chosen region
				try {
					cobLongTownship.setValue("Choose Township");
					cobLongTownship.setItems(testingUtil.getTownshipLongList(
							"select townshipName from township where regionId='" + regionId + "';"));
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		});

	}
}
