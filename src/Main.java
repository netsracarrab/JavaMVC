import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            Controller controller = new Controller(model);
            CounterLabelView labelView = new CounterLabelView();
            CounterAnimationView animationView = new CounterAnimationView();

            model.addObserver(labelView);
            model.addObserver(animationView);
            labelView.setVisible(true);
            animationView.setVisible(true);
            controller.setVisible(true);
        });
    }
}
