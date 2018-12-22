package cn.nuaa.ai;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CSResultsCellRender extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<? extends Object> list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        try {
            //ImageIcon icon = new ImageIcon(imageFile.toURI().toURL());
            //setIcon(icon);
            this.setText(((TextArea)value).getText());
            this.setVerticalTextPosition(SwingConstants.TOP);
            this.setHorizontalTextPosition(SwingConstants.LEFT);
            this.setEnabled(true);
            this.setAutoscrolls(true);
            ImageIcon icon = new ImageIcon("./resources/CodeRecommendationImage/pageup-icon.png");
            this.setIcon(icon);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Border llineBorder, etchedBorder;
        llineBorder = BorderFactory.createLineBorder(Color.gray);
        etchedBorder = BorderFactory.createEtchedBorder();
        this.setBorder(llineBorder);

        return this;
    }
}
