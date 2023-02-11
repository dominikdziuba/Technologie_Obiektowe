package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import general.*;
import states.*;

public class Box implements ActionListener {
    private static JFrame frame;
    private static JLabel label;
    private BufferedImage image;
    private final int height;
    private final int width;
    private final BufferedImage emptyImage;

    public Box(int height, int width) {
        this.frame = null;
        this.label = null;
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(width , height, BufferedImage.TYPE_INT_ARGB);
        this.emptyImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void displayPopulation(Population population) {
        image.setData(emptyImage.getData());
        Graphics2D graphic = this.image.createGraphics();
        List<Single> people = population.getPeople();

        for(Single person: people) {
            int x = (int)(person.getX() * 100);
            int y = (int)(person.getY() * 100);

            IState condition = person.getStatus();

            graphic.setPaint(colorStates(condition));
            graphic.fillOval(x, y, 8, 8);
        }
        this.displayJframe(this.image);
    }

    private void displayJframe(BufferedImage image) {
        if (this.frame == null) {

            this.frame = new JFrame();
            this.frame.setTitle("Infection simulator");
            this.frame.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            this.frame.pack();
            this.frame.setLocationRelativeTo(null);
            this.frame.setVisible(true);

            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.label = new JLabel();

            JButton button = new JButton("Save to file");
            button.addActionListener(this);
            button.setBackground(Color.GREEN);
            button.setForeground(Color.BLUE);

            this.frame.getContentPane().add(label, BorderLayout.WEST);
            this.frame.getContentPane().add(button, BorderLayout.SOUTH);

            this.frame.getContentPane().setBackground(Color.WHITE);

            this.label.setBounds(width, height, 200, 200);
            this.frame.setLocationRelativeTo(null);
            this.frame.pack();
            this.frame.setVisible(true);

        } else {
            this.label.setIcon(new ImageIcon(image));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Simulation.saveToFile();
    }

    public Color colorStates(IState currentState){
        Color tab[]={Color.GREEN,Color.RED,Color.PINK,Color.darkGray};
        if(currentState instanceof Healthy) return tab[0];
        else if(currentState instanceof SickWithSymptoms) return tab[1];
        else if(currentState instanceof SickWIthNoSymptoms) return tab[2];
        else if(currentState instanceof Resistant) return tab[3];
        return null;
    }

}
