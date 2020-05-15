import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class kokushi1 extends HttpServlet{
     public void doPost(HttpServletRequest req,HttpServletResponse res )
     throws ServletException, IOException{
         PrintWriter out;
         req.setCharacterEncoding("UTF-8");
         res.setContentType("text/html;charset=UTF-8");
         out = res.getWriter();
         //webブラウザのFormからパラメータを取得
         String namaeStr = req.getParameter( "namae" );
         String gakunenStr = req.getParameter( "gakunen" );
         String gozenStr = req.getParameter( "gozen" );
         String gogoStr = req.getParameter( "gogo" );
         int gozen = Integer.parseInt(gozenStr);
         int gogo = Integer.parseInt(gogoStr);
         int goukei = gozen + gogo;
         String hantei = "不合格";
         if(goukei>=140 && gozen>=65 && gogo>=65){
              hantei = "合格";
         }

         //入力データの表示
         StringBuffer  sb = new StringBuffer();
         sb.append("<HTML><BODY>");
         sb.append("kokushi1.java");
         sb.append("<CENTER><H1>");
         sb.append("国家試験判定");
         sb.append("<BR><BR><FONT COLOR='deeppink'>");
         sb.append(gakunenStr);
         sb.append("</FONT>");
         sb.append("の");
         sb.append("<FONT COLOR='deeppink'>");
         sb.append(namaeStr);
         sb.append("</FONT>");
         sb.append("さん");
         sb.append("<BR><BR>");
         sb.append("あなたの得点は");
         sb.append("<BR><BR>");
         sb.append("午前");
         sb.append("<FONT COLOR='deeppink'>");
         sb.append(gozenStr);
         sb.append("</FONT>");
         sb.append("点　午後");
         sb.append("<FONT COLOR='deeppink'>");
         sb.append(gogoStr);
         sb.append("</FONT>");
         sb.append("点　合計");
         sb.append("<FONT COLOR='deeppink'>");
         sb.append(goukei);
         sb.append("</FONT>");
         sb.append("点");
         sb.append("<BR><BR>");
         sb.append("判定結果は");
         sb.append("<FONT COLOR='deeppink'>");
         sb.append(hantei);
         sb.append("</FONT>");
         sb.append("です。");
         sb.append("<BR><BR>");
         sb.append("<A HREF=../kokushi1.html>戻る</A>");
         sb.append("</H1></CENTER></BODY></HTML>");
         out.println( sb.toString() );
  }
}
