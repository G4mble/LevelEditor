package View;

import Controller.ToolController;

import javax.swing.*;
import java.awt.*;

public class ToolWindow extends JFrame
{
    private JSlider sliderHeight, sliderWidth;
    private JTextField txtfWidth, txtfHeight;

    public ToolWindow(ToolController paramToolController)
    {
        this.setTitle("ToolWindow");
        this.setSize(new Dimension(315, 560));
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.sliderWidth = new JSlider();
        this.sliderWidth.setMaximum(39);
        this.sliderWidth.setMinimum(1);
        this.sliderWidth.setValue(1);
        this.sliderWidth.setPaintTicks(true);
        this.sliderWidth.setMinorTickSpacing(1);
        this.sliderWidth.setSnapToTicks(true);
        this.sliderWidth.setBounds(95, 343, 204, 23);
        this.sliderWidth.addChangeListener(paramToolController);
        this.getContentPane().add(this.sliderWidth);

        this.sliderHeight = new JSlider();
        this.sliderHeight.setSnapToTicks(true);
        this.sliderHeight.setPaintTicks(true);
        this.sliderHeight.setMinorTickSpacing(1);
        this.sliderHeight.setMinimum(1);
        this.sliderHeight.setValue(1);
        this.sliderHeight.setMaximum(21);
        this.sliderHeight.setBounds(95, 376, 204, 23);
        this.sliderHeight.addChangeListener(paramToolController);
        this.getContentPane().add(this.sliderHeight);

        this.txtfWidth = new JTextField();
        this.txtfWidth.setFocusable(false);
        this.txtfWidth.setEditable(false);
        this.txtfWidth.setColumns(10);
        this.txtfWidth.setBounds(63, 343, 20, 20);
        this.getContentPane().add(this.txtfWidth);

        this.txtfHeight = new JTextField();
        this.txtfHeight.setFocusable(false);
        this.txtfHeight.setEditable(false);
        this.txtfHeight.setColumns(10);
        this.txtfHeight.setBounds(63, 376, 20, 20);
        this.getContentPane().add(this.txtfHeight);

        JLabel lblWidth = new JLabel("Width:");
        lblWidth.setBounds(15, 345, 46, 14);
        this.getContentPane().add(lblWidth);

        JLabel lblHeight = new JLabel("Height:");
        lblHeight.setBounds(15, 378, 46, 14);
        this.getContentPane().add(lblHeight);

        JLabel lblBrushSize = new JLabel("Brush size:");
        lblBrushSize.setBounds(10, 306, 70, 14);
        this.getContentPane().add(lblBrushSize);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 293, 289, 2);
        this.getContentPane().add(separator);

        JTabbedPane materialTabPane = new JTabbedPane(JTabbedPane.TOP);
        materialTabPane.setBounds(10, 11, 289, 237);
        getContentPane().add(materialTabPane);

        JLayeredPane grassTab = new JLayeredPane();
        grassTab.setName("");
        materialTabPane.addTab("grass", null, grassTab, null);

        JToggleButton tglbtnGrass_bright = new JToggleButton("grass_bright");
        tglbtnGrass_bright.setBounds(4, 11, 135, 23);
        grassTab.add(tglbtnGrass_bright);
        tglbtnGrass_bright.addActionListener(paramToolController);
        tglbtnGrass_bright.setActionCommand("tgl_grass_bright_100");

        JToggleButton tglbtnGrass_standard = new JToggleButton("grass_std");
        tglbtnGrass_standard.addActionListener(paramToolController);
        tglbtnGrass_standard.setActionCommand("tgl_grass_standard_100");
        tglbtnGrass_standard.setBounds(145, 11, 135, 23);
        grassTab.add(tglbtnGrass_standard);

        JLayeredPane winterTab = new JLayeredPane();
        materialTabPane.addTab("winter", null, winterTab, null);

        JToggleButton tglbtnIceSheet_bright = new JToggleButton("iceSheet_bright");
        tglbtnIceSheet_bright.addActionListener(paramToolController);
        tglbtnIceSheet_bright.setActionCommand("tgl_iceSheet_bright_106");
        tglbtnIceSheet_bright.setBounds(4, 11, 135, 23);
        winterTab.add(tglbtnIceSheet_bright);

        JToggleButton tglbtnIceSheet_standard = new JToggleButton("iceSheet_std");
        tglbtnIceSheet_standard.addActionListener(paramToolController);
        tglbtnIceSheet_standard.setActionCommand("tgl_iceSheet_standard_107");
        tglbtnIceSheet_standard.setBounds(145, 11, 135, 23);
        winterTab.add(tglbtnIceSheet_standard);

        JToggleButton tglbtnSnow_standard = new JToggleButton("snow_std");
        tglbtnSnow_standard.addActionListener(paramToolController);
        tglbtnSnow_standard.setActionCommand("tgl_snow_standard_109");
        tglbtnSnow_standard.setBounds(4, 45, 135, 23);
        winterTab.add(tglbtnSnow_standard);

        JLayeredPane desertTab = new JLayeredPane();
        materialTabPane.addTab("desert", null, desertTab, null);

        JToggleButton tglbtnDesertSand_bright = new JToggleButton("sand_bright");
        tglbtnDesertSand_bright.addActionListener(paramToolController);
        tglbtnDesertSand_bright.setActionCommand("tgl_desertSand_bright_103");
        tglbtnDesertSand_bright.setBounds(4, 11, 135, 23);
        desertTab.add(tglbtnDesertSand_bright);

        JToggleButton tgblbtnDesertSand_standard = new JToggleButton("sand_std");
        tgblbtnDesertSand_standard.addActionListener(paramToolController);
        tgblbtnDesertSand_standard.setActionCommand("tgl_desertSand_standard_104");
        tgblbtnDesertSand_standard.setBounds(145, 11, 135, 23);
        desertTab.add(tgblbtnDesertSand_standard);

        JLayeredPane treeTab = new JLayeredPane();
        materialTabPane.addTab("tree", null, treeTab, null);

        JToggleButton tglbtnTree_bright = new JToggleButton("tree_bright");
        tglbtnTree_bright.setBounds(4, 11, 135, 23);
        treeTab.add(tglbtnTree_bright);
        tglbtnTree_bright.addActionListener(paramToolController);
        tglbtnTree_bright.setActionCommand("tgl_tree_bright_208");

        JToggleButton tglbtnTree_standard = new JToggleButton("tree_std");
        tglbtnTree_standard.addActionListener(paramToolController);
        tglbtnTree_standard.setActionCommand("tgl_tree_standard_207");
        tglbtnTree_standard.setBounds(145, 11, 135, 23);
        treeTab.add(tglbtnTree_standard);

        JToggleButton tglbtnTree_tarmac_bright = new JToggleButton("tree_tar_bright");
        tglbtnTree_tarmac_bright.addActionListener(paramToolController);
        tglbtnTree_tarmac_bright.setActionCommand("tgl_tree_tarmac_bright_209");
        tglbtnTree_tarmac_bright.setBounds(4, 45, 135, 23);
        treeTab.add(tglbtnTree_tarmac_bright);

        JToggleButton tglbtnTree_tarmac_std = new JToggleButton("tree_tar_std");
        tglbtnTree_tarmac_std.addActionListener(paramToolController);
        tglbtnTree_tarmac_std.setActionCommand("tgl_tree_tarmac_standard_210");
        tglbtnTree_tarmac_std.setBounds(145, 45, 135, 23);
        treeTab.add(tglbtnTree_tarmac_std);

        JToggleButton tglbtnTree_alternative_bright = new JToggleButton("tree_alt_bright");
        tglbtnTree_alternative_bright.addActionListener(paramToolController);
        tglbtnTree_alternative_bright.setActionCommand("tgl_tree_alternative_bright_205");
        tglbtnTree_alternative_bright.setBounds(4, 79, 135, 23);
        treeTab.add(tglbtnTree_alternative_bright);

        JToggleButton tglbtnTree_alternative_standard = new JToggleButton("tree_alt_std");
        tglbtnTree_alternative_standard.addActionListener(paramToolController);
        tglbtnTree_alternative_standard.setActionCommand("tgl_tree_alternative_standard_206");
        tglbtnTree_alternative_standard.setBounds(145, 79, 135, 23);
        treeTab.add(tglbtnTree_alternative_standard);

        JLayeredPane lavaTab = new JLayeredPane();
        materialTabPane.addTab("lava", null, lavaTab, null);

        JToggleButton tglbtnLava_orange_bright = new JToggleButton("lava_orng_bright");
        tglbtnLava_orange_bright.addActionListener(paramToolController);
        tglbtnLava_orange_bright.setActionCommand("tgl_lava_orange_bright_212");
        tglbtnLava_orange_bright.setBounds(4, 11, 135, 23);
        lavaTab.add(tglbtnLava_orange_bright);

        JToggleButton tglbtnLava_orange_standard = new JToggleButton("lava_orng_std");
        tglbtnLava_orange_standard.addActionListener(paramToolController);
        tglbtnLava_orange_standard.setActionCommand("tgl_lava_orange_standard_211");
        tglbtnLava_orange_standard.setBounds(145, 11, 135, 23);
        lavaTab.add(tglbtnLava_orange_standard);

        JToggleButton tglbtnLava_red_bright = new JToggleButton("lava_red_bright");
        tglbtnLava_red_bright.addActionListener(paramToolController);
        tglbtnLava_red_bright.setActionCommand("tgl_lava_red_bright_213");
        tglbtnLava_red_bright.setBounds(4, 45, 135, 23);
        lavaTab.add(tglbtnLava_red_bright);

        JToggleButton tglbtnLava_red_standard = new JToggleButton("lava_red_std");
        tglbtnLava_red_standard.addActionListener(paramToolController);
        tglbtnLava_red_standard.setActionCommand("tgl_lava_red_standard_214");
        tglbtnLava_red_standard.setBounds(145, 45, 135, 23);
        lavaTab.add(tglbtnLava_red_standard);

        JLayeredPane waterTab = new JLayeredPane();
        materialTabPane.addTab("water", null, waterTab, null);

        JToggleButton tglbtnWater_bright = new JToggleButton("water_bright");
        tglbtnWater_bright.addActionListener(paramToolController);
        tglbtnWater_bright.setActionCommand("tgl_water_bright_204");
        tglbtnWater_bright.setBounds(4, 11, 135, 23);
        waterTab.add(tglbtnWater_bright);

        JToggleButton tglbtnWater_standard = new JToggleButton("water_std");
        tglbtnWater_standard.addActionListener(paramToolController);
        tglbtnWater_standard.setActionCommand("tgl_water_standard_203");
        tglbtnWater_standard.setBounds(145, 11, 135, 23);
        waterTab.add(tglbtnWater_standard);

        JToggleButton tglbtnWater_alternative_bright = new JToggleButton("water_alt_bright");
        tglbtnWater_alternative_bright.addActionListener(paramToolController);
        tglbtnWater_alternative_bright.setActionCommand("tgl_water_alternative_bright_201");
        tglbtnWater_alternative_bright.setBounds(4, 45, 135, 23);
        waterTab.add(tglbtnWater_alternative_bright);

        JToggleButton tglbtnWater_alternative_standard = new JToggleButton("water_alt_std");
        tglbtnWater_alternative_standard.addActionListener(paramToolController);
        tglbtnWater_alternative_standard.setActionCommand("tgl_water_alternative_standard_202");
        tglbtnWater_alternative_standard.setBounds(145, 45, 135, 23);
        waterTab.add(tglbtnWater_alternative_standard);

        JLayeredPane eventPane = new JLayeredPane();
        materialTabPane.addTab("event", null, eventPane, null);

        JToggleButton tglbtnPlayer_start = new JToggleButton("player_start");
        tglbtnPlayer_start.setBounds(4, 45, 135, 23);
        tglbtnPlayer_start.addActionListener(paramToolController);
        tglbtnPlayer_start.setActionCommand("tgl_player_start_302");
        eventPane.add(tglbtnPlayer_start);

        JToggleButton tglbtnBattle_entrance = new JToggleButton("battle_entr");
        tglbtnBattle_entrance.setBounds(4, 11, 135, 23);
        tglbtnBattle_entrance.addActionListener(paramToolController);
        tglbtnBattle_entrance.setActionCommand("tgl_battle_entrance_300");
        eventPane.add(tglbtnBattle_entrance);

        JToggleButton tglbtnBattle_entrance_alternative = new JToggleButton("battle_entr_alt");
        tglbtnBattle_entrance_alternative.setBounds(145, 11, 135, 23);
        tglbtnBattle_entrance_alternative.addActionListener(paramToolController);
        tglbtnBattle_entrance_alternative.setActionCommand("tgl_battle_entrance_alternative_301");
        eventPane.add(tglbtnBattle_entrance_alternative);

        JLayeredPane miscTab = new JLayeredPane();
        materialTabPane.addTab("misc", null, miscTab, null);

        JToggleButton tglbtnMud_bright = new JToggleButton("mud_bright");
        tglbtnMud_bright.setBounds(4, 11, 135, 23);
        tglbtnMud_bright.addActionListener(paramToolController);
        tglbtnMud_bright.setActionCommand("tgl_mud_bright_108");
        miscTab.add(tglbtnMud_bright);

        JToggleButton tglbtnGravel_bright = new JToggleButton("gravel_bright");
        tglbtnGravel_bright.addActionListener(paramToolController);
        tglbtnGravel_bright.setActionCommand("tgl_gravel_bright_105");
        tglbtnGravel_bright.setBounds(145, 11, 135, 23);
        miscTab.add(tglbtnGravel_bright);

        JToggleButton tglbtnEraser = new JToggleButton("eraser");
        tglbtnEraser.setBounds(164, 259, 135, 23);
        getContentPane().add(tglbtnEraser);
        tglbtnEraser.addActionListener(paramToolController);
        tglbtnEraser.setActionCommand("tgl_eraser_200");

        this.requestFocus();
        this.setVisible(true);
    }

    public int getBrushHeight()
    {
        return this.sliderHeight.getValue();
    }

    public int getBrushWidth()
    {
        return this.sliderWidth.getValue();
    }

    public JSlider getSliderWidth()
    {
        return this.sliderWidth;
    }

    public JSlider getSliderHeight()
    {
        return this.sliderHeight;
    }

    public JTextField getTxtfWidth()
    {
        return this.txtfWidth;
    }

    public JTextField getTxtfHeight()
    {
        return this.txtfHeight;
    }
}
