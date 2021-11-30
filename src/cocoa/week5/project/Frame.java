package cocoa.week5.project;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    String option;

    public Frame() {
        super("Cafe");
        constructFrame();
    }

    public void constructFrame() {
        Panel panel = new Panel();
//        FrameComponents components = new FrameComponents(this);
        /*Main Frame*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());
        getContentPane().add(panel.buttonPanel, BorderLayout.EAST);
        getContentPane().add(panel.leftPanel, BorderLayout.WEST);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
//        leftPanel.add(ingredientPanel, BorderLayout.NORTH);
    }

    public void setIngredient(String ingredient) {
        option = ingredient;
    }

    public static void main(String[] args) {
        new Frame();

    }
}