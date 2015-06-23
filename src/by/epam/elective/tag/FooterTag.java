package by.epam.elective.tag;


import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(FooterTag.class);

    @Override
    public int doStartTag() throws JspException {
        String copyright = "\u00a9 Pavel Mikhailiuk";
        JspWriter out = pageContext.getOut();
        try {
            out.write("<p>" + copyright + "</p>");
        } catch (IOException e) {
            LOGGER.error("Custom tag error", e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
