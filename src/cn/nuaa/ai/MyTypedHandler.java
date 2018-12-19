package cn.nuaa.ai;

import com.intellij.codeInsight.editorActions.smartEnter.SmartEnterProcessor;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyTypedHandler implements TypedActionHandler {
    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        final Document document = editor.getDocument();
        Project project = editor.getProject();

        //System.out.println("Line Count " + document.getLineCount());
        //System.out.println("Line Number " + document.getLineNumber(document.getLineCount()));
        //System.out.println("Line Start Offset " + document.getLineStartOffset(document.getLineCount() - 1));
        //System.out.println("Line End Offset " + document.getLineEndOffset(document.getLineCount() - 1));
        //System.out.println("Line Separator Length " + document.getLineSeparatorLength(document.getLineCount() - 1));

        List<Caret> carets = editor.getCaretModel().getAllCarets();
        for(Caret cat : carets){
            System.out.println("!!!!!!!! " + cat.getSelectedText());
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //document.insertString(0, "Typed\n");
                final SelectionModel selectionModel = editor.getSelectionModel();
                final int start = selectionModel.getSelectionStart();
                final int end = selectionModel.getSelectionEnd();
                document.replaceString(start, end, "" + c);
                //document.replaceString(start, end, "Replacement");
                System.out.println("start " + start);
                System.out.println("end " + end);
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }
}