package com.yyuze.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * Author: yyuze
 * Created: 2018/9/21
 */
public class TemplateUtil {

    public static String getTemplate(Configuration cfg, String tmplFileName, Map root) throws IOException, TemplateException {
        Template template = cfg.getTemplate(tmplFileName);
        StringWriter writer = new StringWriter();
        template.process(root, writer);
        String result = writer.toString();
        writer.close();
        return result;
    }
}
