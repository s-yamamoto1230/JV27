import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class param2 extends HttpServlet{
  public void doPost(
    HttpServletRequest req, 
    HttpServletResponse res )
    throws ServletException, IOException{
    req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");

    //webブラウザのFormからパラメータを取得
    String namaeStr = req.getParameter( "NAMAE" );
    String seibetuStr = req.getParameter( "SEIBETU" );
    String toshiStr = req.getParameter( "TOSHI" );
    String address1Str = req.getParameter( "ADDRESS1" );

    //入力データの変換
    req.setAttribute( "namae", namaeStr );
    req.setAttribute( "seibetu", seibetuStr );
    req.setAttribute( "toshi", toshiStr );
    req.setAttribute( "address1", address1Str );
    ServletContext sc = getServletContext();
    sc.getRequestDispatcher( "/param2.jsp" ).forward(req, res);     
  }
}