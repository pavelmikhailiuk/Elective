package by.epam.elective.tag;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoDateTimeTag extends TagSupport {
    private final static Logger LOGGER = Logger.getLogger(InfoDateTimeTag.class);

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String language = (String) session.getAttribute("language");
        Locale locale = null;
        if (language != null) {
            locale = new Locale(language.toLowerCase(), language.toUpperCase());
        } else {
            locale = Locale.getDefault();
        }
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, locale);
        Date now = new Date();
        String time = "<p> " + dateFormat.format(now) + "</p>";
        JspWriter out = pageContext.getOut();
        try {
            out.write(time);
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
