import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<ModelObserver> observers = new ArrayList<>();
    private int counter;
    private Color color = new Color(52, 152, 219);

    public int getCounter() {
        return counter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        notifyObservers();
    }

    public void incrementCounter() {
        counter++;
        notifyObservers();
    }

    public void resetCounter() {
        counter = 0;
        notifyObservers();
    }

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
        observer.modelChanged(this);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelChanged(this);
        }
    }
}
