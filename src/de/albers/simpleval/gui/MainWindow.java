package de.albers.simpleval.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import layout.TableLayout;
import de.albers.simpleval.db.dto.Datum;
import de.albers.simpleval.db.dto.Material;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.XYPlot.XYPlotArea2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

/**
 * The main application window showing the data of interest.
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = -8788314043456977013L;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private Color red = new Color(200, 100, 100);
	private Color green = new Color(50, 150, 150);
	private Color grey = new Color(70, 70, 70, 100);
	private Color invisible = new Color(0, 0, 0, 0);

	/**
	 * Constructor.
	 * 
	 * @param materials The materials to be shown.
	 */
	public MainWindow(List<Material> materials) {
		super("SimpleVal");
		
		setSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JTabbedPane tabs = new JTabbedPane();
		
		// Construct layout for tabs
		double layoutSizes[][] = {{0.6, TableLayout.FILL}, {TableLayout.FILL}};
		TableLayout tableLayout = new TableLayout(layoutSizes);
		
		// Iterate through materials
		for(Material m : materials) {
			@SuppressWarnings("unchecked")
			DataTable data = new DataTable(Float.class, Float.class);
			
			@SuppressWarnings("unchecked")
			DataTable lineData = new DataTable(Float.class, Float.class);
			
			Object[] columnNames = {"U", "I"};
			TableModel tableModel = new DefaultTableModel(columnNames, m.getData().size());
			
			// Variables for collecting and calculating line metrics
			int count = 0;
			float sumX = 0, sumY = 0, lineX1 = 0, lineX2 = 0;
			for(Datum d : m.getData()) {
				float u = d.getU();
				float i = d.getI();
				
				data.add(u, i);
				
				if(count == 0) lineX1 = u;
				lineX2 = u;
				
				sumX += u;
				sumY += i;
				
				tableModel.setValueAt(u, count, 0);
				tableModel.setValueAt(i, count, 1);
				
				count++;
			}
			
			// Variables for collecting and calculating line metrics
			float barX = sumX / count;
			float barY = sumY / count;
			
			// Variables for collecting and calculating line metrics
			float barXX = 0, barXY = 0;
			for(int n = 0; n < count; n++) {
				float u = m.getData().get(n).getU();
				float i = m.getData().get(n).getI();
				
				barXX += (u - barX) * (u - barX); 
				barXY += (u - barX) * (i - barY);
			}
			
			// Variables for collecting and calculating line metrics
			float beta1 = barXY / barXX;
			float beta0 = barY - beta1 * barX;
			
			// Construct calculated line
			lineData.add(lineX1, beta1 * lineX1 + beta0);
			lineData.add(lineX2, beta1 * lineX2 + beta0);
			
			// Construct renderers for plot
			LineRenderer lineRenderer = new DefaultLineRenderer2D();
			lineRenderer.setColor(red);
			PointRenderer pointRenderer = new DefaultPointRenderer2D();
			pointRenderer.setColor(green);
			PointRenderer invisiblePointRenderer = new DefaultPointRenderer2D();
			invisiblePointRenderer.setColor(invisible);
			
			// Construct plot
			XYPlot plot = new XYPlot(data);
			((XYPlotArea2D )plot.getPlotArea()).setMajorGridColor(grey);
			plot.add(lineData);
			plot.getPlotArea().setBorderColor(invisible);
			plot.setPointRenderer(data, pointRenderer);
			plot.setLineRenderer(lineData, lineRenderer);
			plot.setPointRenderer(lineData, invisiblePointRenderer);
			plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(Double.MAX_VALUE);
			plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(Double.MAX_VALUE);

			// Construct material panel
			JPanel materialPanel = new JPanel(tableLayout);
			materialPanel.add(new InteractivePanel(plot), "0,0");
			materialPanel.add(new JScrollPane(new JTable(tableModel)), "1,0");
			
			tabs.add(m.getName(), materialPanel);
		}
		
		getContentPane().add(tabs);
	}
}
