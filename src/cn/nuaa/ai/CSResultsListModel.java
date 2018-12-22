package cn.nuaa.ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class CSResultsListModel extends AbstractListModel<TextArea> {

    private static final long serialVersionUID = 1L;

    private List<TextArea> imageFile = new ArrayList<TextArea>();

    public void addElement(TextArea ta){
        this.imageFile.add(ta);
    }

    public int getSize() {
        return imageFile.size();
    }

    public TextArea getElementAt(int index) {
        return imageFile.get(index);
    }

}
