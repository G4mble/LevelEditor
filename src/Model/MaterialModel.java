package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MaterialModel
{
    private Image matImg;
    private int materialID;

    public MaterialModel(String paramMaterial, int paramMatID)
    {
        this.materialID = paramMatID;

        try
        {
            this.matImg = ImageIO.read(new File("data\\img\\materials\\" + paramMaterial + ".png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von " + paramMaterial + ".png\nMaterialModel.constructor()");
        }
    }

    public Image getMatImg()
    {
        return this.matImg;
    }

    public int getMaterialID()
    {
        return this.materialID;
    }
}
