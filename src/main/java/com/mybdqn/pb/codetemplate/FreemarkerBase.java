package com.mybdqn.pb.codetemplate;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by chenliheng on 2017/10/13.
 */
public class FreemarkerBase {

    protected Configuration config = null;

    public FreemarkerBase(String templateDir){
        config = new Configuration(Configuration.VERSION_2_3_26);

        try {
            config.setDirectoryForTemplateLoading(new File(templateDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        config.setDefaultEncoding("utf-8");
        config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        config.setLogTemplateExceptions(false);
    }
}
