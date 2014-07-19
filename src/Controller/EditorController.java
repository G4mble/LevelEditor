package Controller;

import View.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorController implements ActionListener, KeyListener
{
    private EditorFrame editorFrame;
    private WorkspaceController workspaceController;
    private ToolController toolController;
    private DBController dbController;
    private String levelName;

    public EditorController()
    {
        this.dbController = new DBController();
        this.editorFrame = new EditorFrame(this);
        String[] selectOptions = {"Neues Level", "Level Öffnen"};
        int userOptionInput = JOptionPane.showOptionDialog(null, "Möchten Sie ein neues Level erstellen oder ein bestehendes Laden?", "Levelauswahl", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, selectOptions, selectOptions[0]);
        this.initiateEditor();
        if(userOptionInput == JOptionPane.NO_OPTION)
            this.initiateLoad();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            this.dbController.closeConnection();
        else if(e.getActionCommand().equals("neu"))
            this.newLevel();
        else if(e.getActionCommand().equals("offnen"))
            this.initiateLoad();
        else if(e.getActionCommand().equals("speichern"))
            this.initiateSave();
        else if(e.getActionCommand().equals("toolWindow"))
            this.openToolWindow();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_T)
            this.openToolWindow();
        else if(e.getKeyCode() == KeyEvent.VK_N && e.isControlDown())
            this.newLevel();
        else if(e.getKeyCode() == KeyEvent.VK_O && e.isControlDown())
            this.initiateLoad();
        else if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown())
            this.initiateSave();
        else if(e.getKeyCode() == KeyEvent.VK_SUBTRACT || e.getKeyCode() == KeyEvent.VK_MINUS)
        {
            if(e.isControlDown())
                this.modifyBrushSize(- 1, 0);
            else
                this.modifyBrushSize(- 1, 1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ADD || e.getKeyCode() == KeyEvent.VK_PLUS)
        {
            if(e.isControlDown())
                this.modifyBrushSize(0, 0);
            else
                this.modifyBrushSize(0, 1);
        }
    }

    public void initiateEditor()
    {
        this.levelName = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Levelnamen ein!");
        this.openToolWindow();
        this.workspaceController = new WorkspaceController(this);
        this.editorFrame.getContentPane().add(this.workspaceController.getWorkspace());
        this.workspaceController.getWorkspace().repaint();
    }

    public void initiateSave()
    {
        int userInput = JOptionPane.showConfirmDialog(null, "Als " + this.levelName + " speichern?", "Levelname", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(userInput == JOptionPane.YES_OPTION)
            this.dbController.saveLevel(this.workspaceController.getMatModelArray(), this.levelName);
        else
        {
            String tmpName = JOptionPane.showInputDialog(null, "Bitte geben Sie einen neuen Levelname ein!\n[region][ _ ][int][int]", "Levelname", JOptionPane.INFORMATION_MESSAGE);
            if(tmpName != null && tmpName.length() > 0)
                this.dbController.saveLevel(this.workspaceController.getMatModelArray(), tmpName);
        }
    }

    public void initiateLoad()
    {
        String tmpName = JOptionPane.showInputDialog(null, "Welches Level möchten Sie laden?\n[region][ _ ][int][int]", "Levelname", JOptionPane.QUESTION_MESSAGE);
        if(tmpName != null && tmpName.length() > 0)
            if(this.dbController.levelIsExisting(tmpName))
            {
                this.levelName = tmpName;
                this.workspaceController.setMatModelArray(this.dbController.loadLevel(tmpName));
            }
            else
                JOptionPane.showMessageDialog(null, "Level existiert nicht!", "Fehler beim Laden", JOptionPane.WARNING_MESSAGE);
    }

    public void newLevel()
    {
        int userInputOption = JOptionPane.showConfirmDialog(null, "Möchten Sie die Änderungen speichern?", "Fortfahren?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(userInputOption != JOptionPane.CANCEL_OPTION)
        {
            if(userInputOption == JOptionPane.YES_OPTION)
                this.initiateSave();
            try
            {
                this.toolController.getToolWindow().dispose();
                this.toolController = null;
            }
            catch(NullPointerException npE)
            {
            }
            this.workspaceController.getWorkspace().setVisible(false);
            this.editorFrame.remove(this.workspaceController.getWorkspace());
            this.initiateEditor();
        }
    }

    public void openToolWindow()
    {
        if(this.toolController == null)
            this.toolController = new ToolController(this);
        else
            this.toolController.getToolWindow().requestFocus();
    }

    public void modifyBrushSize(int paramState, int paramIndicator)
    {
        this.toolController.modifySliderValue(paramState, paramIndicator);
    }

    public ToolController getToolController()
    {
        return this.toolController;
    }

    public WorkspaceController getWorkspaceController()
    {
        return this.workspaceController;
    }

    @Override /*not in use*/
    public void keyTyped(KeyEvent e)
    {

    }

    @Override /*not in use*/
    public void keyReleased(KeyEvent e)
    {

    }
}
