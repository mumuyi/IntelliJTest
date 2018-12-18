package cn.nuaa.ai;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CodeRecommendationWin implements ToolWindowFactory {
    private JScrollPane scrollPane;
    private ToolWindow myToolWindow;
    private JTabbedPane tabbedPane;

    private JPanel mainPanel;
    private JPanel csPanel;
    private JPanel apiPanel;
    private JPanel csOpPanel;

    private JList csList;
    private JList apiList;

    private JButton pageUp;
    private JButton pageDown;
    private JButton addQuery;

    private TextArea csResult1;
    private TextArea csResult2;
    private TextArea csResult3;

    @Override
    public void createToolWindowContent(@NotNull Project project,
                                        @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        this.initPane();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mainPanel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {

    }

    /**
     * 初始化控件
     */
    public void initPane() {

        //在tabbedPane中添加Page页;
        tabbedPane.addTab("Code Snippets",csPanel);
        tabbedPane.addTab("APIs",apiPanel);


        //设置主面板的大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        mainPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        csPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        apiPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        csList.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));

        //设置Page1 的List内容;
        csResult1 = new TextArea();
        csResult2 = new TextArea();
        csResult3 = new TextArea();
        csResult1.setText("code snippet 111111111111");
        csResult2.setText("code snippet 222222222222");
        csResult3.setText("code snippet 333333333333");

        MyListModel listModel = new MyListModel();
        listModel.addElement(csResult1);
        listModel.addElement(csResult2);
        listModel.addElement(csResult3);
        csList.setModel(listModel);
        csList.setCellRenderer(new MyCellRender());

        csList.setVisibleRowCount(1);
        csList.setValueIsAdjusting(true);
        csList.setAutoscrolls(true);
        csList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        csList.setFixedCellWidth((int) csPanel.getPreferredSize().getWidth() / 3);
        csList.setFixedCellHeight((int) csPanel.getPreferredSize().getHeight() / 3);
        csList.setVisible(true);


        ///构造一个 有滚动条的面板
        //设置滚动条面板位置
        scrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 ));
        //将tree添加道滚动条面板上
        scrollPane.setViewportView(csList);
        //将滚动条面板设置其可见
        scrollPane.setVisible(true);
        //设置滚动条的滚动速度
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        //解决闪烁问题
        scrollPane.getVerticalScrollBar().setDoubleBuffered(true);

        //设置page2 List 的内容;

    }
}
