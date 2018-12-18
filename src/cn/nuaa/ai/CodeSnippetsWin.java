package cn.nuaa.ai;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;

public class CodeSnippetsWin implements ToolWindowFactory {

    private ToolWindow myToolWindow;
    private JPanel mPanel;
    private JScrollPane scrollPane;
    private JList list;
    private JButton pageUp;
    private JButton pageDown;
    private JButton addQuery;
    private TextArea text1;
    private TextArea text2;
    private TextArea text3;


    @Override
    public void createToolWindowContent(@NotNull Project project,
                                        @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        this.initPane();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mPanel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {

    }

    /**
     * 初始化控件
     */
    public void initPane() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        //设置主面板的大小
        mPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        list.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));

        MyListModel listModel = new MyListModel();

        text1 = new TextArea();
        text2 = new TextArea();
        text3 = new TextArea();
        text1.setText("code snippet 111111111111");
        text2.setText("code snippet 222222222222");
        text3.setText("code snippet 333333333333");

        listModel.addElement(text1);
        listModel.addElement(text2);
        listModel.addElement(text3);


        list.setModel(listModel);

        list.setCellRenderer(new MyCellRender());
        list.setVisibleRowCount(1);
        list.setValueIsAdjusting(true);
        list.setAutoscrolls(true);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setFixedCellWidth((int)mPanel.getPreferredSize().getWidth()/3);
        list.setFixedCellHeight((int)mPanel.getPreferredSize().getHeight()/2);
        list.setVisible(true);


        ///构造一个 有滚动条的面板
        //设置滚动条面板位置
        scrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2 - 50));
        //将tree添加道滚动条面板上
        scrollPane.setViewportView(list);
        //将滚动条面板设置其可见
        scrollPane.setVisible(true);
        //设置滚动条的滚动速度
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        //解决闪烁问题
        scrollPane.getVerticalScrollBar().setDoubleBuffered(true);

        //pageUp.setContentAreaFilled(false);
        //pageUp.setMargin(new Insets(0,0,0,0));
        //System.out.println("old size: " + pageUp.getWidth() + pageUp.getHeight());
        //Icon icon = pageUp.getIcon();
        //pageUp.setSize(icon.getIconWidth(),icon.getIconHeight());
        //pageUp.setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
        //pageUp.updateUI();
        //System.out.println("new size: " + pageUp.getWidth() + pageUp.getHeight());

        System.out.println("old size: " + pageUp.getWidth() + pageUp.getHeight());
        System.out.println("old size: " + pageUp.getPreferredSize().getWidth() + pageUp.getPreferredSize().getHeight());

    }
}