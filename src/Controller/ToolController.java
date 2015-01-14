package Controller;

import View.ToolWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolController implements ActionListener, ChangeListener
{
    private ToolWindow toolWindow;
    private EditorController editorController;
    private JToggleButton currentToggleButton = null, previousToggleButton = null;
    private String selectedMaterial;
    private int matID;

    public ToolController(EditorController paramEditorController)
    {
        this.editorController = paramEditorController;
        this.toolWindow = new ToolWindow(this);
        this.updateSliderTextFields();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String tmpAction = e.getActionCommand();
        if(tmpAction.startsWith("tgl"))
        {
            JToggleButton tmpButton = (JToggleButton) e.getSource();

            if(tmpButton.isSelected())
            {
                this.previousToggleButton = this.currentToggleButton;
                this.currentToggleButton = tmpButton;
                this.switchToggleStatus();
            }
            int tmpIndex = tmpAction.lastIndexOf("_");
            this.selectedMaterial = tmpAction.substring(4, tmpIndex);
            this.matID = Integer.parseInt(tmpAction.substring(tmpIndex + 1));
        }
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        this.updateSliderTextFields();
        this.editorController.getWorkspaceController().updateBrushSize(this.toolWindow.getBrushWidth(), this.toolWindow.getBrushHeight());
    }

    private void switchToggleStatus()
    {
        if((this.previousToggleButton != null) && (this.previousToggleButton != this.currentToggleButton))
            this.previousToggleButton.setSelected(false);
    }

    public void modifySliderValue(int paramState, int paramIndicator)
    {
        if(paramIndicator == 0)
        {
            if(paramState == -1)
                this.toolWindow.getSliderWidth().setValue(this.toolWindow.getSliderWidth().getValue() - 1);
            else
                this.toolWindow.getSliderWidth().setValue(this.toolWindow.getSliderWidth().getValue() + 1);
        }
        else
        {
            if(paramState == -1)
                this.toolWindow.getSliderHeight().setValue(this.toolWindow.getSliderHeight().getValue() - 1);
            else
                this.toolWindow.getSliderHeight().setValue(this.toolWindow.getSliderHeight().getValue() + 1);

        }
        this.updateSliderTextFields();
    }

    private void updateSliderTextFields()
    {
        this.toolWindow.getTxtfWidth().setText(String.valueOf(this.toolWindow.getSliderWidth().getValue()));
        this.toolWindow.getTxtfHeight().setText(String.valueOf(this.toolWindow.getSliderHeight().getValue()));
    }

    public String getSelectedMaterial()
    {
        return this.selectedMaterial;
    }

    public int getMatID()
    {
        return this.matID;
    }

    public ToolWindow getToolWindow()
    {
        return this.toolWindow;
    }
}