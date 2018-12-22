package cn.nuaa.ai;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.intellij.ui.treeStructure.Tree;

public class APIResultsListModel extends AbstractListModel<JTree> {

    private static final long serialVersionUID = 2L;

    private List<JTree> imageFile = new ArrayList<JTree>();

    public void addElement(JTree ta){
        this.imageFile.add(ta);
    }

    public int getSize() {
        return imageFile.size();
    }

    public JTree getElementAt(int index) {
        return imageFile.get(index);
    }

}
