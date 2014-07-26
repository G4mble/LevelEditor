package View;

import Controller.WorkspaceController;
import Model.MaterialModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Workspace extends JPanel
{
    private Image raster;
    private WorkspaceController workspaceController;
    private boolean isInMouseFocus = false;
    private int brushHeight = 48, brushWidth = 48, currentMouseX = 0, currentMouseY = 0;

    public Workspace(WorkspaceController paramWorkspaceController)
    {
        this.workspaceController = paramWorkspaceController;

        try
        {
            this.raster = ImageIO.read(new File("data\\img\\raster.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von raster.png\nWorkspace.constructor()");
        }

        this.addMouseListener(this.workspaceController);
        this.addMouseMotionListener(this.workspaceController);
        this.setLayout(null);
        this.setBounds(4, 14, 1911, 1029);
        this.setVisible(true);
    }

    public void paintComponent(Graphics workspace)
    {
        MaterialModel[][] matModelArray = this.workspaceController.getMatModelArray();

        super.paintComponent(workspace);

        workspace.setColor(Color.green);
        workspace.drawImage(this.raster, 0, 0, this);

        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 39; j++)
            {
                if(matModelArray[i][j] != null)
                    workspace.drawImage(matModelArray[i][j].getMatImg(), (48 * j) + j, (48 * i) + i, this);
            }
        }

        if(this.isInMouseFocus)
            workspace.drawRect(this.currentMouseX, this.currentMouseY, this.brushWidth, this.brushHeight);
    }

    public void setInMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }

    public void setBrushHeight(int paramHeight)
    {
        this.brushHeight = paramHeight;
    }

    public void setBrushWidth(int paramWidth)
    {
        this.brushWidth = paramWidth;
    }

    public void setCurrentMouseX(int paramX)
    {
        this.currentMouseX = paramX;
    }

    public void setCurrentMouseY(int paramY)
    {
        this.currentMouseY = paramY;
    }
}
