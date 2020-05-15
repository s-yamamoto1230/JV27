import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class param1 extends HttpServlet{
  public void doPost(
      HttpServletRequest req, 
      HttpServletResponse res )
         throws ServletException, IOException{
    PrintWriter out;
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html;charset=UTF-8");
    out = res.getWriter();
    //webブラウザのFormからパラメータを取得
    String namaeStr = req.getParameter( "NAMAE" );
    String seibetuStr = req.getParameter( "SEIBETU" );
    String toshiStr = req.getParameter( "TOSHI" );
    String address1Str = req.getParameter( "ADDRESS1" );

    //入力データの表示
    StringBuffer  sb = new StringBuffer();
    sb.append("<HTML><BODY>");
    sb.append("param1.java");
    sb.append("<CENTER><H1>");
    sb.append("HTMLさんから届きました");
    sb.append("<BR><BR>");
    sb.append("あなたは");
    sb.append("<BR><FONT COLOR='deeppink'>");
    sb.append(address1Str);
    sb.append("</FONT>");
    sb.append("にお住まいの");
    sb.append("<FONT COLOR='deeppink'>");
    sb.append(namaeStr);
    sb.append("</FONT>");
    sb.append("さんですね");
    sb.append("<BR>");
    sb.append("ほいでもって");
    sb.append("<BR>");
    sb.append("<FONT COLOR='deeppink'>");
    sb.append(toshiStr);
    sb.append("</FONT>");
    sb.append("才の");
    sb.append("<FONT COLOR='deeppink'>");
    if ( seibetuStr.equals( "1" ) ) {
      sb.append("男性");
    } else {
      sb.append("女性");
    }
    sb.append("</FONT>");
    sb.append("なんですね");
    sb.append("</H1></CENTER></BODY></HTML>");
    out.println( sb.toString() );
  }
}

