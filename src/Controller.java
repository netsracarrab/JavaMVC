import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller extends JFrame {
    private final Model model;

    public Controller(Model model) {
        this.model = model;

        setTitle("MVC Controller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 120);
        setLocationByPlatform(true);

        JButton incrementButton = new JButton("Increment");
        incrementButton.addActionListener(event -> incrementCounter());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(event -> resetCounter());

        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(event -> chooseColor());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(incrementButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(colorButton);

        add(buttonPanel);
    }

    public void incrementCounter() {
        model.incrementCounter();
    }

    public void resetCounter() {
        model.resetCounter();
    }

    public void chooseColor() {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Circle Color", model.getColor());
        if (selectedColor != null) {
            model.setColor(selectedColor);
        }
    }
}
