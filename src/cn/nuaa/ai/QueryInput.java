package cn.nuaa.ai;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class QueryInput extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String message = Messages.showInputDialog(
                project,
                "Please Input Your Query",
                "Query",
                Messages.getQuestionIcon());

        if(message != null && !message.isEmpty())
            System.out.println(message);
        else
            System.out.println("input is empty");
    }
}
