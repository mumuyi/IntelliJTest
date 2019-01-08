package cn.nuaa.ai;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CRSettings implements Configurable{
    private CRSettingsEntity cse = new CRSettingsEntity();

    private JPanel myPanel;
    private JTextField userID;
    private JTextField IPAddress;
    private JSpinner csResultsNum;
    private JTextField IPPort;
    private JSpinner apiResultsNum;
    private JRadioButton autoRecommendation;
    private JRadioButton recordQuery;
    private JTextField Sensitivity;
    private JTextField groupID;
    private JTextField gitLink;

    private JCheckBox androidCheckBox;
    private JCheckBox socketCheckBox;
    private JCheckBox SWTCheckBox;
    private JCheckBox swingCheckBox;
    private JCheckBox javaWebCheckBox;
    private JCheckBox ASTCheckBox;
    private JCheckBox eclipsePluginCheckBox;
    private JCheckBox intelliJPluginCheckBox;
    private JCheckBox multiThreadCheckBox;

    private JButton gitTestButton;

    private JComboBox sensitivityLevel;
    private JRadioButton sensitivityModel;

    private static String disPlayName = "Code Recommendation Settings";

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return disPlayName;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        cse = cse.getState();
        setSettings();
        gitTestButton.addMouseListener(new gitTestButtonListener());
        return myPanel;
    }

    @Override
    public boolean isModified() {
        if (cse.getUserID().equals(userID.getText()) && cse.getGroupID().equals(groupID.getText()) && cse.getIPAddress().equals(IPAddress.getText()) && cse.getIPPort().equals(IPPort.getText())) {
            if (cse.getCsResultsNum() == (int) csResultsNum.getValue() && cse.getApiResultsNum() == (int) apiResultsNum.getValue() && ("" + cse.getSensitivity()).equals(Sensitivity.getText())) {
                if (cse.isAutoRecommendation() == autoRecommendation.isSelected() && cse.isRecordQuery() == recordQuery.isSelected()) {
                    if(cse.getGitLink().equals(gitLink.getText())) {
                        if (cse.isAndroidCheckBox() == androidCheckBox.isSelected() && cse.isASTCheckBox() == ASTCheckBox.isSelected() && cse.isEclipsePluginCheckBox() == eclipsePluginCheckBox.isSelected() &&
                                cse.isIntelliJPluginCheckBox() == intelliJPluginCheckBox.isSelected() && cse.isJavaWebCheckBox() == javaWebCheckBox.isSelected() && cse.isSocketCheckBox() == socketCheckBox.isSelected() &&
                                cse.isMultiThreadCheckBox() == multiThreadCheckBox.isSelected() && cse.isSwingCheckBox() == swingCheckBox.isSelected() && cse.isSWTCheckBox() == SWTCheckBox.isSelected()) {
                            if(cse.getSensitivityLevel() == sensitivityLevel.getSelectedIndex() && cse.isSensitivityModel() == sensitivityModel.isSelected()) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        getSettings();
        cse.loadState(cse);
        System.out.println("apply");
    }

    private void setSettings() {
        userID.setText(cse.getUserID());
        groupID.setText(cse.getGroupID());
        gitLink.setText(cse.getGitLink());
        IPAddress.setText(cse.getIPAddress());
        IPPort.setText(cse.getIPPort());
        csResultsNum.setValue(cse.getCsResultsNum());
        apiResultsNum.setValue(cse.getApiResultsNum());
        autoRecommendation.setSelected(cse.isAutoRecommendation());
        recordQuery.setSelected(cse.isAutoRecommendation());
        Sensitivity.setText("" + cse.getSensitivity());

        androidCheckBox.setSelected(cse.isAndroidCheckBox());
        socketCheckBox.setSelected(cse.isSocketCheckBox());
        SWTCheckBox.setSelected(cse.isSWTCheckBox());
        swingCheckBox.setSelected(cse.isSwingCheckBox());
        javaWebCheckBox.setSelected(cse.isJavaWebCheckBox());
        ASTCheckBox.setSelected(cse.isASTCheckBox());
        eclipsePluginCheckBox.setSelected(cse.isEclipsePluginCheckBox());
        intelliJPluginCheckBox.setSelected(cse.isIntelliJPluginCheckBox());
        multiThreadCheckBox.setSelected(cse.isMultiThreadCheckBox());

        sensitivityLevel.addItem("High");
        sensitivityLevel.addItem("Relatively High");
        sensitivityLevel.addItem("Middle");
        sensitivityLevel.addItem("Relatively Low");
        sensitivityLevel.addItem("Low");
        sensitivityLevel.addItem("User Define");

        sensitivityLevel.setSelectedIndex(cse.getSensitivityLevel());
        sensitivityModel.setSelected(cse.isSensitivityModel());

        //调整控制 Sensitivity 按钮;
        SensitivityControl();


        sensitivityModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SensitivityControl();
            }
        });
    }

    private void getSettings() {
        cse.setUserID(userID.getText());
        cse.setGroupID(groupID.getText());
        cse.setGitLink(gitLink.getText());
        cse.setIPAddress(IPAddress.getText());
        cse.setIPPort(IPPort.getText());
        cse.setCsResultsNum((int)csResultsNum.getValue());
        cse.setApiResultsNum((int)apiResultsNum.getValue());
        cse.setSensitivity(Double.parseDouble(Sensitivity.getText()));
        cse.setAutoRecommendation(autoRecommendation.isSelected());
        cse.setRecordQuery(recordQuery.isSelected());

        cse.setAndroidCheckBox(androidCheckBox.isSelected());
        cse.setSocketCheckBox(socketCheckBox.isSelected());
        cse.setSWTCheckBox(SWTCheckBox.isSelected());
        cse.setSwingCheckBox(swingCheckBox.isSelected());
        cse.setJavaWebCheckBox(javaWebCheckBox.isSelected());
        cse.setASTCheckBox(ASTCheckBox.isSelected());
        cse.setEclipsePluginCheckBox(eclipsePluginCheckBox.isSelected());
        cse.setIntelliJPluginCheckBox(intelliJPluginCheckBox.isSelected());
        cse.setMultiThreadCheckBox(multiThreadCheckBox.isSelected());

        cse.setSensitivityLevel(sensitivityLevel.getSelectedIndex());
        cse.setSensitivityModel(sensitivityModel.isSelected());
    }

    class gitTestButtonListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent event) {
            String message = gitLinkTest(cse.getGitLink());
            Messages.showMessageDialog(message,"Testing Result",Messages.getInformationIcon());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private String gitLinkTest(String git){
        //todo github 链接测试代码;
        if(Math.random() <= 0.5){
            return "link to git success";
        }else{
            return "link to git failure";
        }
    }

    private void SensitivityControl(){
        if(sensitivityModel.isSelected()){
            sensitivityLevel.setSelectedIndex(5);
            sensitivityLevel.setEditable(false);
            sensitivityLevel.setEnabled (false);
            Sensitivity.setVisible(true);
            Sensitivity.setEnabled(true);
        }else {
            sensitivityLevel.setEditable(true);
            sensitivityLevel.setSelectedIndex(cse.getSensitivityLevel());
            Sensitivity.setVisible(false);
            Sensitivity.setEnabled(false);
            sensitivityLevel.setEnabled (true);
        }
    }
}
