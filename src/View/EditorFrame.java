package View;

import Controller.EditorController;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame
{
    public EditorFrame(EditorController paramEditorController)
    {
        this.getContentPane().setBackground(Color.black);
        this.setLayout(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menuDatei = new JMenu("Datei");
        menuBar.add(menuDatei);

        JMenuItem itemNeu = new JMenuItem("Neues Level  [ STRG+N ]");
        menuDatei.add(itemNeu);
        itemNeu.addActionListener(paramEditorController);
        itemNeu.setActionCommand("neu");

        JMenuItem itemOffnen = new JMenuItem("Öffnen  [ STRG+O ]");
        menuDatei.add(itemOffnen);
        itemOffnen.addActionListener(paramEditorController);
        itemOffnen.setActionCommand("offnen");

        JMenuItem itemSpeichern = new JMenuItem("Speichern  [ STRG+S ]");
        menuDatei.add(itemSpeichern);
        itemSpeichern.addActionListener(paramEditorController);
        itemSpeichern.setActionCommand("speichern");

        JMenuItem itemBeenden = new JMenuItem("Beenden");
        menuDatei.add(itemBeenden);
        itemBeenden.addActionListener(paramEditorController);
        itemBeenden.setActionCommand("beenden");

        JMenu menuTools = new JMenu("Tools");
        menuBar.add(menuTools);

        JMenuItem itemToolWindow = new JMenuItem("ToolWindow  [ T ]");
        menuTools.add(itemToolWindow);
        itemToolWindow.addActionListener(paramEditorController);
        itemToolWindow.setActionCommand("toolWindow");

        this.addKeyListener(paramEditorController);
        this.setVisible(true);
    }
}
