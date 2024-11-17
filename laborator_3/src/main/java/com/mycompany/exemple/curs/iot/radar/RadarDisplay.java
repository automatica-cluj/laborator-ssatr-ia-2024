package com.mycompany.exemple.curs.iot.radar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class RadarDisplay extends JPanel {
    private double angle = 0;
    private final int RADIUS = 200;
    private final CopyOnWriteArrayList<Target> targets = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Target> historyPoints = new CopyOnWriteArrayList<>();
    private Target selectedTarget = null;
    private final JPopupMenu popupMenu;

    private static class Target {
        Point position;
        double distance;
        double bearing;
        String label;

        Target(Point position, String label) {
            this.position = position;
            this.distance = Math.sqrt(position.x * position.x + position.y * position.y);
            this.bearing = Math.toDegrees(Math.atan2(position.y, position.x));
            if (this.bearing < 0) {
                this.bearing += 360;
            }
            this.label = label;
        }
    }

    public RadarDisplay() {
        setPreferredSize(new Dimension(2 * RADIUS + 50, 2 * RADIUS + 50));
        setBackground(Color.BLACK);

        // Create popup menu for adding targets
        popupMenu = new JPopupMenu();
        JMenuItem addTarget = new JMenuItem("Add Target Here");
        popupMenu.add(addTarget);

        // Create timer for animation
        Timer timer = new Timer(50, e -> {
            angle += 0.1;
            if (angle > 2 * Math.PI) {
                angle = 0;
                historyPoints.clear();
            }

            // Check for targets in the sweep line's path
            for (Target target : targets) {
                double targetAngle = Math.atan2(target.position.y, target.position.x);
                if (targetAngle < 0) {
                    targetAngle += 2 * Math.PI;
                }

                if (Math.abs(targetAngle - angle) < 0.1) {
                    historyPoints.add(target);
                }
            }

            repaint();
        });
        timer.start();
    }

    // Public method to add a target programmatically
    public void addTarget(double distance, double bearing) {
        // Convert polar coordinates (distance, bearing) to Cartesian coordinates (x, y)
        double normalizedDistance = distance * (RADIUS / 100.0); // Convert nm to pixels
        double radians = Math.toRadians(bearing);
        int x = (int)(normalizedDistance * Math.cos(radians));
        int y = (int)(normalizedDistance * Math.sin(radians));

        String label = String.format("T%d", targets.size() + 1);
        targets.add(new Target(new Point(x, y), label));
        repaint();
    }

    private void selectTarget(int screenX, int screenY) {
        int mouseX = screenX - getWidth() / 2;
        int mouseY = screenY - getHeight() / 2;

        selectedTarget = null;
        double minDistance = 10;
        for (Target target : targets) {
            double distance = Math.sqrt(
                    Math.pow(mouseX - target.position.x, 2) +
                            Math.pow(mouseY - target.position.y, 2)
            );
            if (distance < minDistance) {
                selectedTarget = target;
                minDistance = distance;
            }
        }
        repaint();
    }

    private void showAddTargetMenu(int x, int y) {
        popupMenu.setLocation(x, y);
        popupMenu.show(this, x, y);
    }

    private void drawDistanceScale(Graphics2D g2d, int centerX, int centerY) {
        g2d.setColor(new Color(0, 255, 0, 100));
        g2d.setFont(new Font("Monospace", Font.PLAIN, 10));

        for (int i = 1; i <= 4; i++) {
            int diameter = i * (RADIUS / 2);
            String distance = String.format("%d nm", i * 25);
            g2d.drawString(distance, centerX + 5, centerY - diameter / 2);
        }
    }

    private void drawBearingScale(Graphics2D g2d, int centerX, int centerY) {
        g2d.setColor(new Color(0, 255, 0, 100));
        g2d.setFont(new Font("Monospace", Font.PLAIN, 10));

        for (int angle = 0; angle < 360; angle += 30) {
            double rad = Math.toRadians(angle);
            int x = (int) (RADIUS * Math.cos(rad));
            int y = (int) (RADIUS * Math.sin(rad));
            String bearing = String.format("%d°", angle);
            g2d.drawString(bearing, centerX + x - 10, centerY + y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw radar circles
        g2d.setColor(new Color(0, 255, 0, 50));
        for (int i = 1; i <= 4; i++) {
            int diameter = i * (RADIUS / 2);
            g2d.drawOval(centerX - diameter / 2, centerY - diameter / 2, diameter, diameter);
        }

        // Draw scales
        drawDistanceScale(g2d, centerX, centerY);
        drawBearingScale(g2d, centerX, centerY);

        // Draw sweep line
        g2d.setColor(new Color(0, 255, 0, 180));
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new Line2D.Double(
                centerX,
                centerY,
                centerX + RADIUS * Math.cos(angle),
                centerY + RADIUS * Math.sin(angle)
        ));

        // Draw history points
        for (Target target : historyPoints) {
            g2d.setColor(new Color(0, 255, 0, 180));
            g2d.fillOval(
                    centerX + target.position.x - 3,
                    centerY + target.position.y - 3,
                    6,
                    6
            );
            g2d.drawString(target.label,
                    centerX + target.position.x + 5,
                    centerY + target.position.y - 5
            );
        }

        // Draw selected target information
        if (selectedTarget != null) {
            g2d.setColor(new Color(0, 255, 0, 255));
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
            g2d.drawOval(
                    centerX + selectedTarget.position.x - 15,
                    centerY + selectedTarget.position.y - 15,
                    30,
                    30
            );

            String targetInfo = String.format(
                    "Target %s: Distance: %.1f nm, Bearing: %.1f°",
                    selectedTarget.label,
                    selectedTarget.distance * (100.0/RADIUS),
                    selectedTarget.bearing
            );
            g2d.setFont(new Font("Monospace", Font.PLAIN, 12));
            g2d.drawString(targetInfo, 10, getHeight() - 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Radar Display");
        RadarDisplay radar = new RadarDisplay();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a menu bar with controls
        JMenuBar menuBar = new JMenuBar();
        JMenu targetMenu = new JMenu("Targets");

        JMenuItem addTargetMenuItem = new JMenuItem("Add Target...");
        addTargetMenuItem.addActionListener(e -> {
            // Show dialog to input distance and bearing
            JPanel panel = new JPanel(new GridLayout(2, 2));
            JTextField distanceField = new JTextField();
            JTextField bearingField = new JTextField();
            panel.add(new JLabel("Distance (nm):"));
            panel.add(distanceField);
            panel.add(new JLabel("Bearing (degrees):"));
            panel.add(bearingField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Add Target",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double distance = Double.parseDouble(distanceField.getText());
                    double bearing = Double.parseDouble(bearingField.getText());
                    radar.addTarget(distance, bearing);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numbers only.");
                }
            }
        });

        targetMenu.add(addTargetMenuItem);
        menuBar.add(targetMenu);
        frame.setJMenuBar(menuBar);

        frame.add(radar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
