package View;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

import Controller.Application;
import Model.Rdv;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
public class PieChartExample extends JFrame {

    private static final long serialVersionUID = 6294689542092367723L;

    public PieChartExample(String title,Application App) throws SQLException
    {
        super(title);
        PieDataset dataset = createDataset(App);
        JFreeChart chart = ChartFactory.createPieChart("Repartition des rendez_vous  : ("+App.getRendezvous().size()+")", dataset, true, true, false);
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(" {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public PieChartExample() {

    }

    private PieDataset createDataset(Application App) throws SQLException {

        DefaultPieDataset dataset=new DefaultPieDataset();

        ArrayList<Object> Localite;
        Localite=App.getMaconnexion().Selection_distinct("Rendez_vous","loc");
        ArrayList<Integer> Types = new ArrayList<>();
        for (int i=0;i<Localite.size();i++)
        {
            Types.add(0);
        }
        for (int i=0;i<Localite.size();i++)
        {
            for (Rdv N :App.getRendezvous())
            {
                if (Objects.equals(N.Get_lieu(), Localite.get(i)))
                {
                    int a=Types.get(i)+1;
                    Types.set(i,a);
                }
            }
        }

        for (int i=0;i<Localite.size();i++)
        {
            dataset.setValue((String)Localite.get(i),new Double(Types.get(i)) );
        }

        return dataset;
    }

    public void Plot(Application App)
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            PieChartExample example = null;
            try {
                example = new PieChartExample("Statistiques",App);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            assert example != null;
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            example.setVisible(true);
        });
        add(new JButton());
    }
}