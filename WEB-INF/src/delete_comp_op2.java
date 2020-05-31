import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class delete_comp_op2 extends HttpServlet{
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

          PrintWriter out;
          req.setCharacterEncoding("UTF-8");
          res.setContentType("text/html;charset=UTF8");
          out = res.getWriter();
          
          //WebブラウザのFormからパラメータを取得
          String class_no = req.getParameter("class_no");
          String delete_noStr[] = req.getParameterValues("delete");
          System.out.println(class_no);
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
               stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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

               //(名前順に並び変えて再登録)
               query = new StringBuffer();
               query.append("SELECT * FROM class_table WHERE class_no ='");
               query.append(class_no);
               query.append("' ORDER BY kana_1,kana_2,gakuseki_no");
                    ResultSet rs = stmt.executeQuery(query.toString());

                    int auto_no =0;

                    while(rs.next()){

                    auto_no =auto_no+1;
                    String syusseki = String.valueOf(auto_no);
                                                 
                    if(auto_no < 10){
                         rs.updateString("syusseki_no", "0" + syusseki);
                    }else{
                         rs.updateString("syusseki_no", syusseki);
                    }
                    rs.updateRow();
                    auto_no = Integer.parseInt(syusseki);
                    }


          sb.append("              <h1>名簿削除完了しました。</h1>");
          sb.append(          "</center>");
          sb.append(          "<br><br>");
          sb.append("         <hr><a href='../class_delete_op2.html'>削除に戻る</a>");
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
