package ui;

import ui.tools.StartUpWindow;

import java.sql.Statement;

public class Main {

    // EFFECTS: runs GUI of budget application
    public static void main(String[] args) {
        new StartUpWindow().setVisible(true);
    }
}
