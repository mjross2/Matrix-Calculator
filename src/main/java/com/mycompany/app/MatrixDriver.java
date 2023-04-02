package com.mycompany.app;

import javax.swing.*;
import java.awt.*;

public class MatrixDriver{
    public static void main(String[] args) {
        MatrixPanel panel = new MatrixPanel();

        panel.setPreferredSize(new Dimension(700, 500));
        JFrame frame = new JFrame("Matrix Calculator"); // window showing the panel
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
