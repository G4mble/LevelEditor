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
    private String levelName = null;
    private boolean levelIsActive = false;

    public EditorController()
    {
        this.dbController = DBController.getInstance();
        this.editorFrame = new EditorFrame(this);
        this.startInitialization();
    }

    private void startInitialization()
    {
        String[] selectOptions = {"Neues Level", "Level Öffnen"};

        int userOptionInput = - 1;
        while(userOptionInput == - 1)
            userOptionInput = JOptionPane.showOptionDialog(null, "Möchten Sie ein neues Level erstellen oder ein bestehendes Laden?", "Levelauswahl", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, selectOptions, selectOptions[0]);

        this.initiateEditor();
        if(userOptionInput == JOptionPane.NO_OPTION)
            this.initiateLoad();
        else
            this.newLevel();
    }

    private void initiateEditor()
    {
        this.openToolWindow();
        this.workspaceController = new WorkspaceController(this);
        this.editorFrame.getContentPane().add(this.workspaceController.getWorkspace());
        this.workspaceController.getWorkspace().repaint();
    }

    private void openToolWindow()
    {
        if(this.toolController == null)
            this.toolController = new ToolController(this);
        else
            this.toolController.getToolWindow().requestFocus();
    }

    private void initiateLoad()
    {
        int userInputOption = -1;

        if(levelIsActive)
            while(userInputOption == - 1)//TODO abbrechen sollte -1 liefern dennoch fehler
                userInputOption = JOptionPane.showConfirmDialog(null, "Möchten Sie die Änderungen speichern?", "Fortfahren?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        String tmpName = "";
        while(tmpName == null || tmpName.equals(""))
            tmpName = JOptionPane.showInputDialog(null, "Welches Level möchten Sie laden?\n[region][ _ ][int][int]", "Levelname", JOptionPane.QUESTION_MESSAGE);

        if(this.dbController.levelIsExisting(tmpName))
        {
            this.levelName = tmpName;
            this.workspaceController.setMatModelArray(this.dbController.loadLevel(tmpName));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Level existiert nicht!", "Fehler beim Laden", JOptionPane.WARNING_MESSAGE);
            this.initiateLoad();
        }
        this.levelIsActive = true;

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "beenden":
                this.dbController.closeConnection();
                break;
            case "neu":
                this.newLevel();
                break;
            case "offnen":
                this.initiateLoad();
                break;
            case "speichern":
                this.initiateSave();
                break;
            case "toolWindow":
                this.openToolWindow();
                break;
        }
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

    private void initiateSave()
    {
        int userInput = - 1;
        while(userInput == - 1)
            userInput = JOptionPane.showConfirmDialog(null, "Als " + this.levelName + " speichern?", "Levelname", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(userInput == JOptionPane.YES_OPTION)
            this.dbController.saveLevel(this.workspaceController.getMatModelArray(), this.levelName);
        else
        {
            String tmpName = "";
            while(tmpName == null || tmpName.equals(""))
                tmpName = JOptionPane.showInputDialog(null, "Bitte geben Sie einen neuen Levelname ein!\n[region][ _ ][int][int]", "Levelname", JOptionPane.INFORMATION_MESSAGE);

            this.levelName = tmpName;
            this.dbController.saveLevel(this.workspaceController.getMatModelArray(), this.levelName);
        }
    }

    private void newLevel()
    {
        int userInputOption = JOptionPane.NO_OPTION;

        if(levelIsActive)
        {
            userInputOption = - 1;
            while(userInputOption == - 1)
                userInputOption = JOptionPane.showConfirmDialog(null, "Möchten Sie die Änderungen speichern?", "Fortfahren?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        }

        if(userInputOption != JOptionPane.CANCEL_OPTION)
        {
            if(userInputOption == JOptionPane.YES_OPTION)
                this.initiateSave();
            if(levelIsActive)
            {
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

            do
            {
                this.levelName = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Levelnamen ein!", "Neues Level", JOptionPane.QUESTION_MESSAGE);
            } while(this.levelName == null || this.levelName.equals(""));

            this.levelIsActive = true;
        }
    }

    private void modifyBrushSize(int paramState, int paramIndicator)
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

    @Override
    /**not in use*/ public void keyTyped(KeyEvent e)
    {

    }

    @Override
    /**not in use*/ public void keyReleased(KeyEvent e)
    {

    }
}
