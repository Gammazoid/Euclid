package euclid;


import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Show{

	
	public Show(Point[] pts) {

		Stage window = new Stage();
		window.setTitle("Graph");
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		
		ScatterChart scatterChart = new ScatterChart(xAxis, yAxis);
		
		 XYChart.Series dataSeries = new XYChart.Series();
		 
		 for(Point pt :pts) {
			 
			
			 dataSeries.getData().add(new XYChart.Data(pt.getX() , pt.getY()));
			 
			 
		 }
		 
		 scatterChart.getData().add(dataSeries);

		 VBox vbox = new VBox(scatterChart);

		 Scene scene = new Scene(vbox, 400, 400);
	
		
		window.setScene(scene);
		window.initModality(Modality.NONE);
		window.show();
		
	}

	
	
	
	
	

	
	


}
