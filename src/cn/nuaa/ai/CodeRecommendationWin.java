package cn.nuaa.ai;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class CodeRecommendationWin implements ToolWindowFactory {
    private ToolWindow myToolWindow;
    private JTabbedPane tabbedPane;

    private JPanel mainPanel;
    private JPanel csPanel;
    private JPanel apiPanel;
    private JPanel csOpPanel;
    private JPanel apiInformationPanel;
    private JPanel apiOpPanel;
    private JPanel csResultsPanel;
    private JPanel apiResultsPanel;

    private JList csList;

    private JButton cspageUp;
    private JButton cspageDown;
    private JButton csaddQuery;
    private JButton apibutton1;
    private JButton apibutton2;
    private JButton apibutton3;

    private JTextArea apiInformationtextArea;
    private JTree apiResultsTree;

    private JScrollPane csScroll;
    private JScrollPane apiInformationScroll;
    private JScrollPane apiResultsScroll;

    private TextArea csResult1;
    private TextArea csResult2;
    private TextArea csResult3;

    private int screenWidth = 0;
    private int screenHeight = 0;

    private double csProportion1 = 0.05,csProportion2 = 0.95;
    private double apiProportion1 = 0.05,apiProportion2 = 0.55,apiProportion3 = 0.4;

    @Override
    public void createToolWindowContent(@NotNull Project project,
                                        @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        this.initPane();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(mainPanel, "", false);
        toolWindow.getContentManager().addContent(content);
        //Dimension size = toolWindow.getContentManager().getComponent().getRootPane().getSize();
        //System.out.println("size: " + size.getWidth() + " " + size.getHeight());
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


        //设置主面板的大小;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        screenWidth = (int)screenSize.getWidth() - 200;
        screenHeight = (int)screenSize.getHeight() / 3 ;

        //按比例设置部件大小;
        mainPanel.setPreferredSize(new Dimension(screenWidth, screenHeight));

        csOpPanel.setPreferredSize(new Dimension((int)(screenWidth*csProportion1), screenHeight));
        csResultsPanel.setPreferredSize(new Dimension((int)(screenWidth*csProportion2), screenHeight));
        apiOpPanel.setPreferredSize(new Dimension((int)(screenWidth*apiProportion1), screenHeight));
        apiResultsPanel.setPreferredSize(new Dimension((int)(screenWidth*apiProportion2), screenHeight));
        apiInformationPanel.setPreferredSize(new Dimension((int)(screenWidth*apiProportion3), screenHeight));

        csOpPanel.setSize(new Dimension((int)(screenWidth*csProportion1), screenHeight));
        csResultsPanel.setSize(new Dimension((int)(screenWidth*csProportion2), screenHeight));
        apiOpPanel.setSize(new Dimension((int)(screenWidth*apiProportion1), screenHeight));
        apiResultsPanel.setSize(new Dimension((int)(screenWidth*apiProportion2), screenHeight));
        apiInformationPanel.setSize(new Dimension((int)(screenWidth*apiProportion3), screenHeight));

        //mainPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        //csPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        //apiPanel.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));
        //csList.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 * 2));

        //设置Page1 的List内容;
        csResult1 = new TextArea();
        csResult2 = new TextArea();
        csResult3 = new TextArea();
        csResult1.setText("code snippet 111111111111");
        csResult2.setText("code snippet 222222222222");
        csResult3.setText("code snippet 333333333333");

        csResult1.setEditable(true);
        csResult2.setEditable(true);
        csResult3.setEditable(true);

        CSResultsListModel csListModel = new CSResultsListModel();
        csListModel.addElement(csResult1);
        csListModel.addElement(csResult2);
        csListModel.addElement(csResult3);
        csList.setModel(csListModel);
        csList.setCellRenderer(new CSResultsCellRender());

        csList.setVisibleRowCount(1);
        csList.setValueIsAdjusting(true);
        csList.setAutoscrolls(true);
        csList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        csList.setFixedCellWidth((int) csPanel.getPreferredSize().getWidth() / 3);
        csList.setFixedCellHeight((int) csPanel.getPreferredSize().getHeight());
        csList.setVisible(true);


        ///构造一个 有滚动条的面板
        //设置滚动条面板位置
        //scrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth() - 140, (int) screenSize.getHeight() / 3 ));
        //将tree添加道滚动条面板上
        //scrollPane.setViewportView(csList);
        //将滚动条面板设置其可见
        //scrollPane.setVisible(true);
        //设置滚动条的滚动速度
        //scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        //解决闪烁问题
        //scrollPane.getVerticalScrollBar().setDoubleBuffered(true);


        //todo 设置page2 List 的内容;
        //tree根目录
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("API Recommendation");
        //子节点
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("API Usage Pattern1 (Similarity)");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("API Usage Pattern2 (Similarity)");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("API Usage Pattern3 (Similarity)");
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("API Usage Pattern4 (Similarity)");
        DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("API Usage Pattern5 (Similarity)");
        DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("API Usage Pattern6 (Similarity)");

        DefaultMutableTreeNode node11 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern1");
        DefaultMutableTreeNode node12 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern1");
        DefaultMutableTreeNode node13 = new DefaultMutableTreeNode("API Sequence3 for API Usage Pattern1");
        DefaultMutableTreeNode node21 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern2");
        DefaultMutableTreeNode node22 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern2");
        DefaultMutableTreeNode node23 = new DefaultMutableTreeNode("API Sequence3 for API Usage Pattern2");
        DefaultMutableTreeNode node24 = new DefaultMutableTreeNode("API Sequence4 for API Usage Pattern2");
        DefaultMutableTreeNode node31 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern3");
        DefaultMutableTreeNode node32 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern3");
        DefaultMutableTreeNode node41 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern4");
        DefaultMutableTreeNode node42 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern4");
        DefaultMutableTreeNode node43 = new DefaultMutableTreeNode("API Sequence3 for API Usage Pattern4");
        DefaultMutableTreeNode node51 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern5");
        DefaultMutableTreeNode node52 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern5");
        DefaultMutableTreeNode node53 = new DefaultMutableTreeNode("API Sequence3 for API Usage Pattern5");
        DefaultMutableTreeNode node54 = new DefaultMutableTreeNode("API Sequence4 for API Usage Pattern5");
        DefaultMutableTreeNode node61 = new DefaultMutableTreeNode("API Sequence1 for API Usage Pattern6");
        DefaultMutableTreeNode node62 = new DefaultMutableTreeNode("API Sequence2 for API Usage Pattern6");

        node1.add(node11);
        node1.add(node12);
        node1.add(node13);
        node2.add(node21);
        node2.add(node22);
        node2.add(node23);
        node2.add(node24);
        node3.add(node31);
        node3.add(node32);
        node4.add(node41);
        node4.add(node42);
        node4.add(node43);
        node5.add(node51);
        node5.add(node52);
        node5.add(node53);
        node5.add(node54);
        node6.add(node61);
        node6.add(node62);

        root1.add(node1);
        root1.add(node2);
        root1.add(node3);
        root1.add(node4);
        root1.add(node5);
        root1.add(node6);
        //构造一个treeModel 对象，进行刷新树操作
        DefaultTreeModel dt = new DefaultTreeModel(root1);
        apiResultsTree.setModel(dt);

        apiInformationtextArea.setEditable(false);


        //todo 设置page1 监听事件;

        //todo 设置page2 监听事件;
        apiResultsTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) apiResultsTree.getLastSelectedPathComponent();
                if(node == null){
                    return;
                }

                if(node.isLeaf()){
                    System.out.println("nodeName:" + node.toString());
                    //apiInformationtextArea.append("The source code of " + node.toString());
                    apiInformationtextArea.setText("The source code of " + node.toString());
                }
            }
        });
    }
}
