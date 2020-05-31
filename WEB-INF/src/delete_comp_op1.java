import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class delete_comp_op1 extends HttpServlet{
     public void doPost(HttpServletRequest req,HttpServletResponse res)
     throws ServletException,IOException{
          //DB:URL
          final String URL ="jdbc:mysql://localhost/nhs90345db?useUnicode=true&characterEncodi ng=UTF-8";
          //DB:ユーザ
          final String USER ="root";
          //DB:パスワード
          final String PASS ="";
          //DB:ドライバ
          final String DRIVER ="org.gjt.mm.mysql.Driver";

          Connection con = null;
          Statement stmt = null;

          String class_no,syusseki_no,gakuseki_no,simei_1,simei_2,kana_1,kana_2,umare;
          PrintWriter out;
          req.setCharacterEncoding("UTF-8");
          res.setContentType("text/html;charset=UTF8");
          out = res.getWriter();

          //WebブラウザのFormからパラメータを取得
          String delete_noStr[] = req.getParameterValues("delete");
          System.out.println(delete_noStr.length);

          StringBuffer sb = new StringBuffer();
          sb.append("<html>");
          sb.append(     "<head>");
          sb.append(          "<title>名簿削除</title>");
          sb.append(     "</head>");
          sb.append(     "<body bgcolor='#ffffff'>");
          sb.append(          "delete_comp.java");
          sb.append(          "<center>");
          sb.append(               "<caption>");
          sb.append(                    "<font size='+3' color='#0000ff'>");
          sb.append(                    "<b>＜＜名簿削除＞＞</b>");
          sb.append(                    "</font>");
          sb.append(               "</caption>");
          sb.append(               "<br><br><br>");

          try{
               //MySQLDriverロード
               Class.forName(DRIVER);
               //MySQL接続
               con = DriverManager.getConnection(URL,USER,PASS);
               //SQL格納
               stmt = con.createStatement();
               //SQL実行
               StringBuffer query = new StringBuffer();

               int i = 0;
               query.append("DELETE FROM class_table WHERE gakuseki_no ='");
                    while(i < delete_noStr.length){
                         query.append(delete_noStr[i]);
                         if(i == delete_noStr.length-1){
                              query.append("'");
                         }else{
                              query.append("' or gakuseki_no = '");
                         }
                         i++;
                    }
               stmt.executeUpdate(query.toString());


          sb.append("              <h1>名簿削除完了しました。</h1>");
          sb.append(          "</center>");
          sb.append(          "<br><br>");
          sb.append("         <hr><a href='../class_delete_op1.html'>削除に戻る</a>");
          sb.append("         <hr><a href='../class_index.html'>ホームに戻る</a>");
          sb.append(     "</body>");
          sb.append("</html>");
          out.println(sb.toString());

               //切断
               stmt.close();
               con.close();
          }
          //例外処理
          catch(SQLException ex){
               out.println("--- SQL Excepion ---" + "<br>");
               out.println("Message : " + "<br>");
               while(ex != null){
                    out.println(ex.getMessage() + "<br>");
                    ex = ex.getNextException();
               }
          }
          catch(Exception ex){
               ex.printStackTrace(out);
          }
     }
}
