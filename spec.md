# Java Swing MVC Counter App Spec

Create a small Java Swing application that demonstrates the Model-View-Controller pattern with one model, one controller window, and two independent view windows.

## Project Structure

Place all Java source files in a `src` directory:

- `Main.java`
- `Model.java`
- `ModelObserver.java`
- `Controller.java`
- `CounterLabelView.java`
- `CounterAnimationView.java`

Use plain Java and Swing only. Do not use external dependencies.

## Behavior

The application tracks a counter value and a selected color in the model.

When the app starts, it opens three separate `JFrame` windows:

- A controller window with buttons for changing the model.
- A label view window that displays the current counter value as text.
- An animation view window that displays random circles.

The controller must update the model. The views must observe the model and update themselves whenever the model changes.

## Model

Create a `Model` class that stores:

- `int counter`, initially `0`
- `Color color`, initially `new Color(52, 152, 219)`
- A list of `ModelObserver` objects

The model must provide:

- `getCounter()`
- `getColor()`
- `setColor(Color color)`
- `incrementCounter()`
- `resetCounter()`
- `addObserver(ModelObserver observer)`
- `removeObserver(ModelObserver observer)`

Whenever `counter` or `color` changes, notify all observers.

When an observer is added, immediately call `observer.modelChanged(this)` so the view shows the initial state.

## Observer Interface

Create a `ModelObserver` interface with:

```java
void modelChanged(Model model);
```

## Controller Window

Create a `Controller` class that extends `JFrame`.

The controller receives the `Model` in its constructor and stores it in a field.

The controller window should:

- Have the title `MVC Controller`
- Use `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`
- Use size `300 x 120`
- Use `setLocationByPlatform(true)`
- Contain three buttons in a `JPanel` with `FlowLayout`

Buttons:

- `Increment`: calls `model.incrementCounter()`
- `Reset`: calls `model.resetCounter()`
- `Color`: opens a `JColorChooser` dialog using the current model color as the initial color; if the user selects a color, call `model.setColor(selectedColor)`

## Counter Label View

Create a `CounterLabelView` class that extends `JFrame` and implements `ModelObserver`.

The label view should:

- Have the title `Counter Label View`
- Use `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`
- Use size `400 x 300`
- Use `setLocationRelativeTo(null)`
- Contain a centered `JLabel`
- Use a bold sans-serif font at size `32`

On `modelChanged`, update the label text to:

```text
Counter: <counter>
```

## Counter Animation View

Create a `CounterAnimationView` class that extends `JFrame` and implements `ModelObserver`.

The animation view should:

- Have the title `Counter Animation View`
- Use `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`
- Use size `400 x 300`
- Use `setLocationByPlatform(true)`
- Contain a custom `JPanel` in the center

The custom panel should:

- Have preferred size `400 x 300`
- Use background color `new Color(247, 249, 252)`
- Paint random circles
- The number of circles must equal the model counter
- The RGB values of every circle must come from the model color
- Each circle must have a random alpha/transparency value to create slight color variation
- Use random circle diameters from `20` to `70`
- Use random alpha values from `80` to `220`
- Use antialiasing in `paintComponent`
- Keep each circle inside the panel bounds when painting

When the model changes, regenerate the circle list using the current counter and color, then repaint the panel.

## Main

Create `Main.java` with `public static void main(String[] args)`.

Use `SwingUtilities.invokeLater` to create and show the UI.

Inside the Swing runnable:

1. Create one `Model`.
2. Create one `Controller` using the model.
3. Create one `CounterLabelView`.
4. Create one `CounterAnimationView`.
5. Add both views as observers to the model.
6. Show all three windows.

## Compile and Run

The project should compile with:

```powershell
javac src\*.java
```

The project should run with:

```powershell
java -cp src Main
```
