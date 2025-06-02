package com.maron.dawid.main;

import com.maron.dawid.inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        addKeyListener(new KeyboardInputs());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(100, 100, 200, 50);
    }
}
