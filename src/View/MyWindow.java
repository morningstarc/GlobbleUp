package View;

import Controller.*;
import Model.PlayerCharacter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public JButton startButton;

    public void init() {
        setSize(700, 500);
        setLocation(300, 200);
        setTitle("Globble UP");

        canvas = new MyCanvas();

        //mouse listener
        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        //key listener
        KeyEventListener keyListener = new KeyEventListener();
        canvas.addKeyListener(keyListener);
        canvas.setFocusable(true); //can receive keyboard input


        var cp = getContentPane();
        cp.add(BorderLayout.CENTER, canvas);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1));



        startButton = new JButton("Start");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        bottomPanel.add(buttonPanel);
        cp.add(BorderLayout.SOUTH, bottomPanel);
        startButton.setFocusable(false);

         startButton.addActionListener(e -> {
            if(!Main.running){
                startButton.setText("Quit");
                Main.running = true;
            }

            else {
                System.exit(0);
                }
        }

        );

    }




}

