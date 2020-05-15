package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class param2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
 response.setContentType("text/html; charset=UTF-8"); 
      out.write("\r\n");
      out.write("<HTML>\r\n");
      out.write("    <HEAD><TITLE>param2.jsp</TITLE>\r\n");
      out.write("    </HEAD>\r\n");
      out.write("    <BODY>\r\n");
      out.write("        param2.jsp テスト\r\n");
      out.write("        <CENTER><H1>サーブレットさんが<BR>\r\n");
      out.write("        HTMLさんからのデータを届けてくれました\r\n");
      out.write("        <BR><BR>\r\n");
      out.write("        あなたは\r\n");
      out.write("        <BR><FONT COLOR='deeppink'>\r\n");
      out.write("        ");
      out.print( request.getAttribute( "address1" ).toString() );
      out.write("\r\n");
      out.write("        </FONT>にお住まいの\r\n");
      out.write("        <FONT COLOR='deeppink'>\r\n");
      out.write("        ");
 String aaa = request.getAttribute( "namae" ).toString(); 
      out.write("\r\n");
      out.write("        ");
      out.print( aaa );
      out.write("\r\n");
      out.write("        </FONT>さんですね<BR>\r\n");
      out.write("        ほいでもって<BR>\r\n");
      out.write("        <FONT COLOR='deeppink'>\r\n");
      out.write("        ");
      out.print( request.getAttribute( "toshi" ).toString() );
      out.write("\r\n");
      out.write("        </FONT>才の\r\n");
      out.write("        <FONT COLOR='deeppink'>\r\n");
      out.write("        ");
 if ( request.getAttribute( "seibetu" ).toString().equals( "1" ) ) { 
      out.write("\r\n");
      out.write("              男性\r\n");
      out.write("        ");
 } else { 
      out.write("\r\n");
      out.write("              女性\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("        </FONT>なんですね</H1></CENTER>\r\n");
      out.write("        <HR>\r\n");
      out.write("        <A HREF=\"/JV27/param2.html\">戻る</A> \r\n");
      out.write("    </BODY>\r\n");
      out.write("</HTML>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
