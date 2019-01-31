package com.idea.share.com.idea.share.configuration;

import com.idea.share.com.idea.share.sorting.SortEnum;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyEditorSupport;

@Configuration
public class EnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        try {
            setValue(SortEnum.valueOf(text.toUpperCase()));
        } catch (Exception ex) {
            setValue(SortEnum.ADDED);
        }
    }






}