import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import java.util.Calendar;

public class DeskClockMIDlet extends MIDlet implements Runnable {

    private Display display;
    private ClockCanvas canvas;
    private boolean running = true;

    protected void startApp() {
        display = Display.getDisplay(this);
        canvas = new ClockCanvas();
        canvas.setFullScreenMode(true); // removes softkeys and status bar
        try {
            display.setCurrent(canvas);
        } catch (Exception e) {}
        new Thread(this).start();
    }

    protected void pauseApp() {}
    protected void destroyApp(boolean unconditional) { running = false; }

    public void run() {
        while (running) {
            if (canvas != null) {
                canvas.repaint();
            }
            try { Thread.sleep(1000); } catch (Exception e) {}
        }
    }
}

class ClockCanvas extends Canvas {

    public ClockCanvas() {
        setFullScreenMode(true);
    }

    protected void showNotify() {
        setFullScreenMode(true);
    }

    protected void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        // Background - fill entire screen
        g.setColor(0x000000);
        g.fillRect(0, 0, w, h);

        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR);
        if (hour == 0) hour = 12;

        int min = c.get(Calendar.MINUTE);
        boolean pm = (c.get(Calendar.AM_PM) == Calendar.PM);

        String time =
                two(hour) + ":" +
                two(min) + " " +
                (pm ? "PM" : "AM");

        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        String date =
                day + suffix(day) + " " +
                month(c.get(Calendar.MONTH)) + ", " +
                year;

        g.setColor(0xFFFFFF);

        // HUGE TIME - use largest available font, no layering
        Font timeFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
        g.setFont(timeFont);
        int timeY = (h / 3) + 20;
        g.drawString(time, w / 2, timeY, Graphics.HCENTER | Graphics.BASELINE);

        // DATE - smaller, below
        Font dateFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(dateFont);
        int dateY = (2 * h / 3) + 10;
        g.drawString(date, w / 2, dateY, Graphics.HCENTER | Graphics.BASELINE);
    }

    // Disable ALL keys and handle device rotation
    protected void keyPressed(int keyCode) { handleKey(keyCode); }
    protected void keyReleased(int keyCode) {}
    protected void keyRepeated(int keyCode) {}

    private void handleKey(int keyCode) {
        // Suppress all key events
        // Don't let any key do anything
    }

    private String two(int n) { return (n < 10) ? "0" + n : "" + n; }

    private String month(int m) {
        String[] names = {
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        };
        return names[m];
    }

    private String suffix(int d) {
        if (d >= 11 && d <= 13) return "th";
        switch (d % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
}
