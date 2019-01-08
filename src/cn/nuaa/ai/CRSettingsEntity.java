package cn.nuaa.ai;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@State(name = "CRSettings.xml",
        storages = {@Storage(StoragePathMacros.WORKSPACE_FILE)},
        reloadable = true)
public class CRSettingsEntity implements PersistentStateComponent<CRSettingsEntity> {
    private String userID = "default";
    private String groupID = "default";
    private String gitLink = "default";
    private String IPAddress = "localhost";
    private String IPPort = "8080";
    private int csResultsNum = 10;
    private int apiResultsNum = 10;
    private double Sensitivity = 0.85;
    private boolean autoRecommendation = true;
    private boolean recordQuery = true;

    private boolean androidCheckBox = true;
    private boolean socketCheckBox = true;
    private boolean SWTCheckBox = true;
    private boolean swingCheckBox = true;
    private boolean javaWebCheckBox = true;
    private boolean ASTCheckBox = true;
    private boolean eclipsePluginCheckBox = true;
    private boolean intelliJPluginCheckBox = true;
    private boolean multiThreadCheckBox = true;

    private int sensitivityLevel = 1;
    private boolean sensitivityModel = false;

    @Nullable
    @Override
    public CRSettingsEntity getState() {
        readFiles();
        return this;
    }

    @Override
    public void loadState(@NotNull CRSettingsEntity state) {
        XmlSerializerUtil.copyBean(state, this);
        writeFiles();
    }

    private void writeFiles() {
        Element Root = new Element("CodeRecommendationSettings");

        Element userID = new Element("userID");
        Element groupID = new Element("groupID");
        Element gitLink = new Element("gitLink");
        Element IPAddress = new Element("IPAddress");
        Element IPPort = new Element("IPPort");
        Element csResultsNum = new Element("csResultsNum");
        Element apiResultsNum = new Element("apiResultsNum");
        Element Sensitivity = new Element("Sensitivity");
        Element autoRecommendation = new Element("autoRecommendation");
        Element recordQuery = new Element("recordQuery");

        Element androidCheckBox = new Element("androidCheckBox");
        Element socketCheckBox = new Element("socketCheckBox");
        Element SWTCheckBox = new Element("SWTCheckBox");
        Element swingCheckBox = new Element("swingCheckBox");
        Element javaWebCheckBox = new Element("javaWebCheckBox");
        Element ASTCheckBox = new Element("ASTCheckBox");
        Element eclipsePluginCheckBox = new Element("eclipsePluginCheckBox");
        Element intelliJPluginCheckBox = new Element("intelliJPluginCheckBox");
        Element multiThreadCheckBox = new Element("multiThreadCheckBox");

        Element sensitivityLevel = new Element("sensitivityLevel");
        Element sensitivityModel = new Element("sensitivityModel");

        // 定义一个Document对象
        Document document = new Document(Root);

        // 设置元素内容
        userID.setText(this.userID);
        groupID.setText(this.groupID);
        gitLink.setText(this.gitLink);
        IPAddress.setText(this.IPAddress);
        IPPort.setText(this.IPPort);
        csResultsNum.setText("" + this.csResultsNum);
        apiResultsNum.setText("" + this.apiResultsNum);
        Sensitivity.setText("" + this.Sensitivity);
        autoRecommendation.setText("" + this.autoRecommendation);
        recordQuery.setText("" + this.recordQuery);

        sensitivityLevel.setText("" + this.sensitivityLevel);
        sensitivityModel.setText("" + this.sensitivityModel);

        androidCheckBox.setText("" + this.androidCheckBox);
        socketCheckBox.setText("" + this.socketCheckBox);
        SWTCheckBox.setText("" + this.SWTCheckBox);
        swingCheckBox.setText("" + this.swingCheckBox);
        javaWebCheckBox.setText("" + this.javaWebCheckBox);
        ASTCheckBox.setText("" + this.ASTCheckBox);
        eclipsePluginCheckBox.setText("" + this.eclipsePluginCheckBox);
        intelliJPluginCheckBox.setText("" + this.intelliJPluginCheckBox);
        multiThreadCheckBox.setText("" + this.multiThreadCheckBox);

        // 在根节点下加入节点;
        Root.addContent(userID);
        Root.addContent(groupID);
        Root.addContent(gitLink);
        Root.addContent(IPAddress);
        Root.addContent(IPPort);
        Root.addContent(csResultsNum);
        Root.addContent(apiResultsNum);
        Root.addContent(Sensitivity);
        Root.addContent(autoRecommendation);
        Root.addContent(recordQuery);

        Root.addContent(sensitivityLevel);
        Root.addContent(sensitivityModel);

        Root.addContent(androidCheckBox);
        Root.addContent(socketCheckBox);
        Root.addContent(SWTCheckBox);
        Root.addContent(swingCheckBox);
        Root.addContent(javaWebCheckBox);
        Root.addContent(ASTCheckBox);
        Root.addContent(eclipsePluginCheckBox);
        Root.addContent(intelliJPluginCheckBox);
        Root.addContent(multiThreadCheckBox);


        XMLOutputter out = new XMLOutputter(); // 输出xml文件
        out.setFormat(out.getFormat().setEncoding("GBK")); // 设置输出编码

        try {
            out.output(document, new FileOutputStream("../settings.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFiles() {
        File file = new File("../settings.xml");
        if (!file.exists()) {
            writeFiles();
            return;
        }
        //1.建立SAX解析
        SAXBuilder builder = new SAXBuilder();
        // 2.找到Document文档
        Document dc = null;
        try {
            dc = builder.build("../settings.xml");
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        //File file = new File("./");
        //System.out.println(file.getPath() + " " + file.getAbsolutePath());
        //System.out.println(System.getProperty("user.dir"));
        //System.out.println(StoragePathMacros.WORKSPACE_FILE);

        // 3.读取根元素
        Element root = dc.getRootElement();
        // 得到全部的linkman子元素
        //List list = root.getChildren();

        this.userID = root.getChildren("userID").get(0).getText();
        this.groupID = root.getChildren("groupID").get(0).getText();
        this.gitLink = root.getChildren("gitLink").get(0).getText();
        this.IPAddress = root.getChildren("IPAddress").get(0).getText();
        this.IPPort = root.getChildren("IPPort").get(0).getText();
        this.csResultsNum = Integer.parseInt(root.getChildren("csResultsNum").get(0).getText());
        this.apiResultsNum = Integer.parseInt(root.getChildren("apiResultsNum").get(0).getText());
        this.Sensitivity = Double.parseDouble(root.getChildren("Sensitivity").get(0).getText());
        this.autoRecommendation = Boolean.parseBoolean(root.getChildren("autoRecommendation").get(0).getText());
        this.recordQuery = Boolean.parseBoolean(root.getChildren("recordQuery").get(0).getText());

        this.sensitivityLevel = Integer.parseInt(root.getChildren("sensitivityLevel").get(0).getText());
        this.sensitivityModel = Boolean.parseBoolean(root.getChildren("sensitivityModel").get(0).getText());

        this.androidCheckBox = Boolean.parseBoolean(root.getChildren("androidCheckBox").get(0).getText());
        this.socketCheckBox = Boolean.parseBoolean(root.getChildren("socketCheckBox").get(0).getText());
        this.SWTCheckBox = Boolean.parseBoolean(root.getChildren("SWTCheckBox").get(0).getText());
        this.swingCheckBox = Boolean.parseBoolean(root.getChildren("swingCheckBox").get(0).getText());
        this.javaWebCheckBox = Boolean.parseBoolean(root.getChildren("javaWebCheckBox").get(0).getText());
        this.ASTCheckBox = Boolean.parseBoolean(root.getChildren("ASTCheckBox").get(0).getText());
        this.eclipsePluginCheckBox = Boolean.parseBoolean(root.getChildren("eclipsePluginCheckBox").get(0).getText());
        this.intelliJPluginCheckBox = Boolean.parseBoolean(root.getChildren("intelliJPluginCheckBox").get(0).getText());
        this.multiThreadCheckBox = Boolean.parseBoolean(root.getChildren("multiThreadCheckBox").get(0).getText());
    }

    public CRSettingsEntity() {
    }

    public String getUserID() {
        return userID;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getGroupID() { return groupID; }

    public void setGroupID(String groupID) { this.groupID = groupID; }

    public String getGitLink() { return gitLink; }

    public void setGitLink(String gitLink) { this.gitLink = gitLink; }

    public String getIPPort() {
        return IPPort;
    }

    public int getCsResultsNum() {
        return csResultsNum;
    }

    public int getApiResultsNum() {
        return apiResultsNum;
    }

    public double getSensitivity() {
        return Sensitivity;
    }

    public boolean isAutoRecommendation() {
        return autoRecommendation;
    }

    public boolean isRecordQuery() {
        return recordQuery;
    }

    public boolean isAndroidCheckBox() {
        return androidCheckBox;
    }

    public boolean isSocketCheckBox() {
        return socketCheckBox;
    }

    public boolean isSWTCheckBox() {
        return SWTCheckBox;
    }

    public boolean isSwingCheckBox() {
        return swingCheckBox;
    }

    public boolean isJavaWebCheckBox() {
        return javaWebCheckBox;
    }

    public boolean isASTCheckBox() {
        return ASTCheckBox;
    }

    public boolean isEclipsePluginCheckBox() {
        return eclipsePluginCheckBox;
    }

    public boolean isIntelliJPluginCheckBox() {
        return intelliJPluginCheckBox;
    }

    public boolean isMultiThreadCheckBox() {
        return multiThreadCheckBox;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setIPPort(String IPPort) {
        this.IPPort = IPPort;
    }

    public void setCsResultsNum(int csResultsNum) {
        this.csResultsNum = csResultsNum;
    }

    public void setApiResultsNum(int apiResultsNum) {
        this.apiResultsNum = apiResultsNum;
    }

    public void setSensitivity(double sensitivity) {
        Sensitivity = sensitivity;
    }

    public void setAutoRecommendation(boolean autoRecommendation) {
        this.autoRecommendation = autoRecommendation;
    }

    public void setRecordQuery(boolean recordQuery) {
        this.recordQuery = recordQuery;
    }

    public void setAndroidCheckBox(boolean androidCheckBox) {
        this.androidCheckBox = androidCheckBox;
    }

    public void setSocketCheckBox(boolean socketCheckBox) {
        this.socketCheckBox = socketCheckBox;
    }

    public void setSWTCheckBox(boolean SWTCheckBox) {
        this.SWTCheckBox = SWTCheckBox;
    }

    public void setSwingCheckBox(boolean swingCheckBox) {
        this.swingCheckBox = swingCheckBox;
    }

    public void setJavaWebCheckBox(boolean javaWebCheckBox) {
        this.javaWebCheckBox = javaWebCheckBox;
    }

    public void setASTCheckBox(boolean ASTCheckBox) {
        this.ASTCheckBox = ASTCheckBox;
    }

    public void setEclipsePluginCheckBox(boolean eclipsePluginCheckBox) {
        this.eclipsePluginCheckBox = eclipsePluginCheckBox;
    }

    public void setIntelliJPluginCheckBox(boolean intelliJPluginCheckBox) {
        this.intelliJPluginCheckBox = intelliJPluginCheckBox;
    }

    public void setMultiThreadCheckBox(boolean multiThreadCheckBox) {
        this.multiThreadCheckBox = multiThreadCheckBox;
    }

    public int getSensitivityLevel() { return sensitivityLevel; }

    public boolean isSensitivityModel() { return sensitivityModel; }

    public void setSensitivityLevel(int sensitivityLevel) { this.sensitivityLevel = sensitivityLevel; }

    public void setSensitivityModel(boolean sensitivityModel) { this.sensitivityModel = sensitivityModel; }
}
