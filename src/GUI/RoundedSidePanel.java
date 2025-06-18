package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class RoundedSidePanel extends JPanel {
    private final Color backgroundColor;
    private final int arc;
    private final boolean roundLeft;

    /**
     * @param bgColor 背景颜色
     * @param arcRadius 圆角半径
     * @param roundLeft 是否圆左边（否则圆右边）
     */
    public RoundedSidePanel(Color bgColor, int arcRadius, boolean roundLeft) {
        this.backgroundColor = bgColor;
        this.arc = arcRadius;
        this.roundLeft = roundLeft;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 避免丢失内容
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        Path2D.Float path = new Path2D.Float();

        if (roundLeft) {
            // 左边圆角
            path.moveTo(arc, 0);
            path.quadTo(0, 0, 0, arc);
            path.lineTo(0, h - arc);
            path.quadTo(0, h, arc, h);
            path.lineTo(w, h);
            path.lineTo(w, 0);
            path.closePath();
        } else {
            // 右边圆角
            path.moveTo(0, 0);
            path.lineTo(w - arc, 0);
            path.quadTo(w, 0, w, arc);
            path.lineTo(w, h - arc);
            path.quadTo(w, h, w - arc, h);
            path.lineTo(0, h);
            path.closePath();
        }

        g2.setColor(backgroundColor);
        g2.fill(path);

        g2.dispose();
    }
}
