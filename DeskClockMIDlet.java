import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

public class DeskClockMIDlet extends MIDlet implements Runnable {

    private Display display;
    private ClockCanvas canvas;
    private boolean running = true;

    public DeskClockMIDlet() {
        display = Display.getDisplay(this);
        canvas = new ClockCanvas();
    }

    protected void startApp() {
        display.setCurrent(canvas);
        Thread t = new Thread(this);
        t.start();
    }

    protected void pauseApp() {}

    protected void destroyApp(boolean unconditional) {
        running = false;
    }

    public void run() {
        while (running) {
            canvas.repaint();
            try {
                Thread.sleep(1000); // update every second
            } catch (InterruptedException e) {}
        }
    }
}

class ClockCanvas extends Canvas {

    protected void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        // Background
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);

        // Time
        Calendar cal = Calendar.getInstance();
        String time =
            two(cal.get(Calendar.HOUR_OF_DAY)) + ":" +
            two(cal.get(Calendar.MINUTE)) + ":" +
            two(cal.get(Calendar.SECOND));

        String date =
            cal.get(Calendar.DAY_OF_MONTH) + "/" +
            (cal.get(Calendar.MONTH) + 1) + "/" +
            cal.get(Calendar.YEAR);

        g.setColor(0xFFFFFF);

        Font timeFont = Font.getFont(Font.FACE_SYSTEM,
                                     Font.STYLE_BOLD,
                                     Font.SIZE_LARGE);

        Font dateFont = Font.getFont(Font.FACE_SYSTEM,
                                     Font.STYLE_PLAIN,
                                     Font.SIZE_MEDIUM);

        g.setFont(timeFont);
        g.drawString(time, w / 2, h / 2 - 20,
                     Graphics.HCENTER | Graphics.BASELINE);

        g.setFont(dateFont);
        g.drawString(date, w / 2, h / 2 + 20,
                     Graphics.HCENTER | Graphics.BASELINE);
    }

    private String two(int n) {
        return (n < 10) ? "0" + n : String.valueOf(n);
    }
}
