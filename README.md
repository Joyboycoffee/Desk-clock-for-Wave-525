# Desk Clock for Samsung Wave 525

Full-screen digital desk clock J2ME MIDlet for the Samsung Wave 525 (GT-S5250).

## Summary

Desk Clock is a Java ME (J2ME) MIDlet designed to repurpose legacy bada/Java mobile devices like the Samsung Wave 525 into dedicated bedside or desk digital clocks. The MIDlet displays current time and date in full-screen mode, disabling unnecessary softkeys and screen clutter for clean, high-contrast visibility.

## Features

- True full-screen mode with softkey bars and status icons hidden.
- Clean typography displaying 12-hour time (AM/PM) and full date with calendar month names.
- Efficient 1-second repaint cycle minimizing CPU load and battery usage.
- Suppressed key inputs to prevent accidental exit or accidental touch dismissals when docked.

## System Requirements

- Platform: Java ME (MIDP 2.0 / CLDC 1.1)
- Target Device: Samsung Wave 525 (GT-S5250, bada OS with J2ME runtime)
- Display: 240x400 WQVGA (also scales across standard J2ME display resolutions)

## Building and Packaging

1. Build the MIDlet using the Sun Java Wireless Toolkit (WTK 2.5.2) or Oracle Java ME SDK.
2. Compile `DeskClockMIDlet.java` against the MIDP 2.0 and CLDC 1.1 classpath.
3. Package the class files into a `.jar` archive and generate the corresponding `.jad` descriptor.
4. Transfer both `.jar` and `.jad` files to the phone via USB storage or Bluetooth and launch the application.
