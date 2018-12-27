package cn.nuaa.ai;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JCheckBox androidCheckBox;
    private JCheckBox socketCheckBox;
    private JCheckBox SWTCheckBox;
    private JCheckBox swingCheckBox;
    private JCheckBox javaWebCheckBox;
    private JCheckBox ASTCheckBox;
    private JCheckBox eclipsePluginCheckBox;
    private JCheckBox intelliJPluginCheckBox;
    private JCheckBox multiThreadCheckBox;

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
        return myPanel;
    }

    @Override
    public boolean isModified() {
        if (cse.getUserID().equals(userID.getText()) && cse.getIPAddress().equals(IPAddress.getText()) && cse.getIPPort().equals(IPPort.getText())) {
            //System.out.println("level 1");
            if (cse.getCsResultsNum() == (int) csResultsNum.getValue() && cse.getApiResultsNum() == (int) apiResultsNum.getValue() && ("" + cse.getSensitivity()).equals(Sensitivity.getText())) {
                //System.out.println("level 2");
                if (cse.isAutoRecommendation() == autoRecommendation.isSelected() && cse.isRecordQuery() == recordQuery.isSelected()) {
                    //System.out.println("level 3");
                    if(cse.isAndroidCheckBox() == androidCheckBox.isSelected() && cse.isASTCheckBox() == ASTCheckBox.isSelected() && cse.isEclipsePluginCheckBox() == eclipsePluginCheckBox.isSelected() &&
                    cse.isIntelliJPluginCheckBox() == intelliJPluginCheckBox.isSelected() && cse.isJavaWebCheckBox() == javaWebCheckBox.isSelected() && cse.isSocketCheckBox() == socketCheckBox.isSelected() &&
                    cse.isMultiThreadCheckBox() == multiThreadCheckBox.isSelected() && cse.isSwingCheckBox() == swingCheckBox.isSelected() && cse.isSWTCheckBox() == SWTCheckBox.isSelected()){
                        return false;
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
    }

    private void getSettings() {
        cse.setUserID(userID.getText());
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
    }
}
