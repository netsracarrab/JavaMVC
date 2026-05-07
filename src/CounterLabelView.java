import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CounterLabelView extends JFrame implements ModelObserver {
    private final JLabel counterLabel = new JLabel();

    public CounterLabelView() {
        setTitle("Counter Label View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));

        add(counterLabel, BorderLayout.CENTER);
    }

    @Override
    public void modelChanged(Model model) {
        counterLabel.setText("Counter: " + model.getCounter());
    }
}
