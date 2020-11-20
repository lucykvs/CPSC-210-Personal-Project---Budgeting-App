package ui.tools;

import model.Category;
import model.Transaction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import ui.BudgetAppGUI;

import javax.swing.*;
import java.util.ArrayList;

public abstract class PieChartWindow extends JFrame {
    private BudgetAppGUI budgetAppGUI;

    public PieChartWindow(BudgetAppGUI budgetAppGUI) {
        super();
        this.budgetAppGUI = budgetAppGUI;
        setSize(560, 367);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    private JFreeChart createChart(PieDataset dataSet, String title) {
        JFreeChart chart = ChartFactory.createPieChart(
                title,   // chart title
                dataSet,           // data
                true,       // include legend
                true,
                false);

        return chart;
    }

    public JPanel createChartPanel(String title) {
        JFreeChart chart = createChart(createDataSet(), title);
        return new ChartPanel(chart);
    }

    abstract PieDataset createDataSet();

    protected double getTotalCostsInCategory(Category c) {
        ArrayList<Transaction> allTransactions = budgetAppGUI.getUser().getAllTransactions().getTransactions();

        double total = 0;
        for (Transaction t : allTransactions) {
            if (t.getCategory().equals(c)) {
                total += t.getAmount();
            }
        }
        return total;
    }
}
