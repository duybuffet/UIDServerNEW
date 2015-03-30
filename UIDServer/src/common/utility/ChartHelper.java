/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.utility;

import dao.PersonDetailsDAO;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Duy Buffet
 */
public class ChartHelper {

    public static CategoryDataset createDataset() throws SQLException {

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ResultSet rs = new PersonDetailsDAO().selectAllPersonofArea();
        while (rs.next()) {
            dataset.setValue(rs.getDouble("NumOfCitizens"), "Area Code : " + rs.getString("AreaCode"), "Areas");
        }

        return dataset;

    }

    public static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Quantity Of Citizens Of Each Area", // chart title
                "(Include unactived people)", // domain axis label
                "Quantity", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.yellow); // Set the background colour of the chart
        chart.getTitle().setPaint(Color.blue); // Adjust the colour of the title
        CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
        p.setBackgroundPaint(Color.black); // Modify the plot background 
        p.setRangeGridlinePaint(Color.red);

        return chart;

    }
}
