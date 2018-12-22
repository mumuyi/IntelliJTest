package cn.nuaa.ai;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import com.intellij.ui.treeStructure.Tree;

public class APIResultsCellRender extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<? extends Object> list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        try {
            //ImageIcon icon = new ImageIcon(imageFile.toURI().toURL());
            //setIcon(icon);
            //this.setText(((TextArea)value).getText());
            //this.setVerticalTextPosition(SwingConstants.TOP);
            //this.setHorizontalTextPosition(SwingConstants.LEFT);

            JTree tree = (JTree)value;
            if(tree == null){
                System.out.println("is null");
            }else{
                System.out.println("is not null");
            }

            this.add(tree);
            this.setComponentZOrder(tree,0);
            //this.setText("11111111111111111111111111111111");
            //this.setText("22222222222222222222222222222222");
            //this.setText("33333333333333333333333333333333");
            //this.setText("44444444444444444444444444444444");
            //this.setText("55555555555555555555555555555555");

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
