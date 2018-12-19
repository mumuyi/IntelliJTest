package cn.nuaa.ai;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.Language;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class MyCompletionContributor extends CompletionContributor {
    public MyCompletionContributor() {
        Collection<Language> languages = Language.getRegisteredLanguages();
        Language Jlanguages = null;
        for(Language language : languages){
            if(language.getDisplayName().equals("Java") || language.getDisplayName().equals("JAVA")){
                Jlanguages = language;
            }
        }
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement().withLanguage(Jlanguages),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                        resultSet.addElement(LookupElementBuilder.create("Hello 1"));
                        resultSet.addElement(LookupElementBuilder.create("Hello 2"));
                        resultSet.addElement(LookupElementBuilder.create("Hello 3"));
                        resultSet.addElement(LookupElementBuilder.create("Hello 4"));
                        resultSet.addElement(LookupElementBuilder.create("Hello NUAA"));
                    }
                }
        );
    }
}