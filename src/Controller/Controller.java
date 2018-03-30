package Controller;

import javafx.event.ActionEvent;
import Model.Model;

public class Controller {
	
	private Model model;
	//private View view;
	private ActionColor1 actionColor1;
	private ActionColor2 actionColor2;
	
	public Controller (Model model) {
		this.model = model;
		//this.view = new View ();
		
		
	}
	
	private class ActionColor1 extends ActionEvent {
		
		public ActionColor1 () {
		}
		
		public void actionPerformed (ActionEvent arg0) {

		}	
	}
	
	private class ActionColor2 extends ActionEvent {
		
		public ActionColor2 () {
		}
		
		public void actionPerformed (ActionEvent arg0) {

		}
	}
	
}
