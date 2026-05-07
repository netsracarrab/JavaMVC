import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CounterAnimationView extends JFrame implements ModelObserver {
    private final CounterAnimationPanel animationPanel = new CounterAnimationPanel();

    public CounterAnimationView() {
        setTitle("Counter Animation View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationByPlatform(true);

        add(animationPanel, BorderLayout.CENTER);
    }

    @Override
    public void modelChanged(Model model) {
        animationPanel.setCounter(model.getCounter(), model.getColor());
    }

    private static class CounterAnimationPanel extends JPanel {
        private static final int MIN_DIAMETER = 20;
        private static final int MAX_DIAMETER = 70;
        private static final int MIN_ALPHA = 80;
        private static final int MAX_ALPHA = 220;
        private static final Random RANDOM = new Random();

        private final List<Circle> circles = new ArrayList<>();
        private Color circleColor = new Color(52, 152, 219, 180);

        CounterAnimationPanel() {
            setPreferredSize(new Dimension(400, 300));
            setBackground(new Color(247, 249, 252));
        }

        void setCounter(int counter, Color color) {
            circleColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 180);
            circles.clear();
            for (int i = 0; i < counter; i++) {
                circles.add(createRandomCircle());
            }
            repaint();
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Graphics2D g2 = (Graphics2D) graphics.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            for (Circle circle : circles) {
                int x = Math.min(circle.x, Math.max(0, width - circle.diameter));
                int y = Math.min(circle.y, Math.max(0, height - circle.diameter));

                g2.setColor(new Color(
                        circleColor.getRed(),
                        circleColor.getGreen(),
                        circleColor.getBlue(),
                        circle.alpha));
                g2.fillOval(x, y, circle.diameter, circle.diameter);
            }

            g2.dispose();
        }

        private Circle createRandomCircle() {
            int diameter = RANDOM.nextInt(MAX_DIAMETER - MIN_DIAMETER + 1) + MIN_DIAMETER;
            int maxX = Math.max(1, getWidth() - diameter);
            int maxY = Math.max(1, getHeight() - diameter);
            int x = RANDOM.nextInt(maxX);
            int y = RANDOM.nextInt(maxY);
            int alpha = RANDOM.nextInt(MAX_ALPHA - MIN_ALPHA + 1) + MIN_ALPHA;

            return new Circle(x, y, diameter, alpha);
        }

        private static class Circle {
            private final int x;
            private final int y;
            private final int diameter;
            private final int alpha;

            Circle(int x, int y, int diameter, int alpha) {
                this.x = x;
                this.y = y;
                this.diameter = diameter;
                this.alpha = alpha;
            }
        }
    }
}
