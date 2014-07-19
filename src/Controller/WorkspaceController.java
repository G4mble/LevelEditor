package Controller;

import Model.MaterialModel;
import View.Workspace;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WorkspaceController implements MouseListener, MouseMotionListener
{
    private Workspace workspace;
    private EditorController editorController;
    private MaterialModel[][] matModelArray = new MaterialModel[21][39];
    private int brushHeightUnit = 1, brushWidthUnit = 1;

    public WorkspaceController(EditorController paramEditorController)
    {
        this.editorController = paramEditorController;
        this.workspace = new Workspace(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        this.selectBrushAction(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        this.workspace.setInMouseFocus(true);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        this.workspace.setInMouseFocus(false);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        this.updateBrushPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        this.updateBrushPosition(e.getX(), e.getY());
        this.selectBrushAction(e.getX(), e.getY());
    }

    public void selectBrushAction(int paramX, int paramY)
    {
        if((this.editorController.getToolController() != null) && (this.editorController.getToolController().getSelectedMaterial() != null))
        {
            int xPos = (paramX - (paramX / 48)) / 48;
            int yPos = (paramY - (paramY / 48)) / 48;
            String material = this.editorController.getToolController().getSelectedMaterial();
            int matID = this.editorController.getToolController().getMatID();
            this.brushAction(xPos, yPos, material, matID);
        }
    }

    public void brushAction(int paramX, int paramY, String paramMaterial, int paramMatID)
    {
        for(int j = paramY; j < (this.brushHeightUnit + paramY); j++)
        {
            for(int i = paramX; i < (this.brushWidthUnit + paramX); i++)
            {
                if((i < 39) && (j < 21))
                {
                    if(paramMatID == 200)
                        this.matModelArray[j][i] = null;
                    else
                        this.matModelArray[j][i] = new MaterialModel(paramMaterial, paramMatID);
                }
            }
        }
        this.workspace.repaint();
    }

    public void updateBrushSize(int paramBrushWidth, int paramBrushHeight)
    {
        this.brushWidthUnit = paramBrushWidth;
        this.brushHeightUnit = paramBrushHeight;
        int tmpWidth = 48 * this.brushWidthUnit;
        int tmpHeight = 48 * this.brushHeightUnit;
        tmpWidth += (tmpWidth / 48);
        tmpHeight += (tmpHeight / 48);
        this.workspace.setBrushWidth(tmpWidth);
        this.workspace.setBrushHeight(tmpHeight);
        this.workspace.repaint();
    }

    public void updateBrushPosition(int paramX, int paramY)
    {
        int xPos = (((paramX - (paramX / 48)) / 48) * 48) + (paramX / 48);
        int yPos = (((paramY - (paramY / 48)) / 48) * 48) + (paramY / 48);
        this.workspace.setCurrentMouseX(xPos);
        this.workspace.setCurrentMouseY(yPos);
        this.workspace.repaint();
    }

    public MaterialModel[][] getMatModelArray()
    {
        return this.matModelArray;
    }

    public void setMatModelArray(MaterialModel[][] paramMatModelArray)
    {
        this.matModelArray = paramMatModelArray;
        this.workspace.repaint();
    }

    public Workspace getWorkspace()
    {
        return this.workspace;
    }

    @Override /*not in use*/
    public void mousePressed(MouseEvent e)
    {

    }

    @Override /*not in use*/
    public void mouseReleased(MouseEvent e)
    {

    }
}
