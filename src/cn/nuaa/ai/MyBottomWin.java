package cn.nuaa.ai;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Enumeration;

public class MyBottomWin implements ToolWindowFactory {

    private ToolWindow myToolWindow;
    private JPanel mPanel;
    private JTree tree1;
    private JScrollPane scrollPane;

    @Override
    public void createToolWindowContent(@NotNull Project project,
                                        @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;

        //tree1 = new JTree();
        //mPanel = new JPanel();
        //scrollPane = new JScrollPane();

        this.initTreeAndPane();
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
    public void initTreeAndPane() {
        //定义字体
        Font fnt = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        //找到字体的资源管理
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
        //tree根目录
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XX公司");
        //子节点
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("研发部");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("行政部");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("物流部");
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("人事部");
        DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("财务部");
        root.add(node1);
        root.add(node2);
        root.add(node3);
        root.add(node4);
        root.add(node5);
        //构造一个treeModel 对象，进行刷新树操作
        DefaultTreeModel dt = new DefaultTreeModel(root);
        tree1.setModel(dt);
        //tree1.setRootVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        //设置主面板的大小
        mPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 50, (int) screenSize.getHeight() / 3 * 2));
        //tree 设置大小
        tree1.setPreferredSize(new Dimension((int) screenSize.getWidth() - 50, (int) screenSize.getHeight() / 3 * 2));
        ///构造一个 有滚动条的面板
        //设置滚动条面板位置
        scrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth() - 50, (int) screenSize.getHeight() / 3 * 2 - 50));
        //将tree添加道滚动条面板上
        scrollPane.setViewportView(tree1);
        //将滚动条面板设置哼可见
        scrollPane.setVisible(true);
        //设置滚动条的滚动速度
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        //解决闪烁问题
        scrollPane.getVerticalScrollBar().setDoubleBuffered(true);


    }
}