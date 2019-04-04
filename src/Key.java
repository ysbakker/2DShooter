public class Key {
    private char keyCode;
    private boolean pressed;

    /** maakt een toets aan die ingedrukt kan worden
     * @param keyCode welke toets is ingedrukt (char)
     */
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

    /**
     * @return of de toets ingedrukt is of niet
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * @return het karakter van de keycode
     */
    public char getKeyCode() {
        return keyCode;
    }
}
