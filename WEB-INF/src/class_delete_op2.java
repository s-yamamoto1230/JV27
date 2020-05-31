import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class class_delete_op2 extends HttpServlet{
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
          String class_noStr = req.getParameter("CLASS_NO");

          StringBuffer sb = new StringBuffer();
          sb.append("<html>");
          sb.append("<head><title>削除対象選択</title></head>");
          sb.append("<body bgcolor='#fff'>");
          sb.append("    class_delet.java");
          sb.append("    <center>");
          sb.append("         <caption><font size='+3' color='#0000ff'><B>＜＜削除選択＞＞</B></font></caption>");
          sb.append("         <br><br><br>");
          sb.append("         <font size='+2' color='deeppink'>");
          sb.append(               class_noStr);
          sb.append("         </font>");
          sb.append("         <br><br>");
          sb.append("         <form method='post' action='./delete_comp_op2'>");

          sb.append("              <input type='hidden' name='class_no' value= '");
          sb.append(               class_noStr);
          sb.append(               "'>");

          sb.append("              <table border='1' bordercolor='darkblue'>");
          sb.append("                   <tr>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>削除</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>出席</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>学籍</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>氏名（姓）</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>氏名（名）</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>カナ（セイ）</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>カナ（メイ）</font>");
          sb.append("                        </td>");
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>生年月日</font>");
          sb.append("                        </td>");
          sb.append("                   </tr>");

          try{
               //MySQLDriverロード
               Class.forName(DRIVER);
               //MySQL接続
               con = DriverManager.getConnection(URL,USER,PASS);
               //SQL格納
               stmt = con.createStatement();
               //SQL実行
               StringBuffer query = new StringBuffer();

               query.append("select * from class_table where class_no ='");
               query.append(class_noStr);
               query.append("' order by syusseki_no");
                    
               ResultSet rs = stmt.executeQuery(query.toString());

               //検索結果
               while(rs.next()){
                    syusseki_no = rs.getString("syusseki_no");
                    gakuseki_no = rs.getString("gakuseki_no");
                    simei_1 = rs.getString("simei_1");
                    simei_2 = rs.getString("simei_2");
                    kana_1 = rs.getString("kana_1");
                    kana_2 = rs.getString("kana_2");
                    umare = rs.getString("umare");

          sb.append("                   <tr>");
          sb.append("                        <td align='cente' bgcolor='blue'>");
          sb.append("                             <input type='checkbox' name='delete' value='");
          sb.append(                              gakuseki_no);
          sb.append("                             '>");
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                               syusseki_no);
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                              gakuseki_no);
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                              simei_1);
          sb.append("                         </td>");
          sb.append("                        <td>");
          sb.append(                              simei_2);
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                              kana_1);
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                              kana_2);
          sb.append("                        </td>");
          sb.append("                        <td>");
          sb.append(                              umare);
          sb.append("                        </td>");
          sb.append("                   </tr>");

               }

          sb.append("              </table><br>");
          sb.append("         <input type='submit' value='削除'>");
          sb.append("         </form>");
          sb.append("    </center><br><br>");
          sb.append("    <hr><a href='../class_delete_op2.html'>選択に戻る</a>");
          sb.append("    <hr><a href='../class_index.html'>ホームに戻る</a>");
          sb.append("</body></html>");
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
