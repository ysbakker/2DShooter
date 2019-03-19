public class Key {
    private char keyCode;
    private boolean pressed;

    public Key(char keyCode) {
        this.keyCode = keyCode;
        pressed = false;
    }

    public void press() {
        pressed = true;
    }

    public void release() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public char getKeyCode() {
        return keyCode;
    }
}
