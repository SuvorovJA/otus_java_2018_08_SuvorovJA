package ru.otus.sua.L12.webserver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@Slf4j
class TemplateProcessor {

    private Configuration configuration;

    TemplateProcessor() {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), TemplateConstants.HTML_TEMPLATES_DIR);
        configuration.setDefaultEncoding("UTF-8");
    }

    String getPage(String filename, Map<String, Object> data) {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException | IOException e) {
            log.error(e.getMessage());
            return "TEMPLATE_LOAD_ERR: look logfile.";
        }
    }
}
