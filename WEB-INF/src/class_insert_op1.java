import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class class_insert_op1 extends HttpServlet{
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
          class_no = req.getParameter("CLASS_NO");
          gakuseki_no = req.getParameter("GAKUSEKI_NO");
          simei_1 = req.getParameter("SIMEI_1");
          simei_2 = req.getParameter("SIMEI_2");
          kana_1 = req.getParameter("KANA_1");
          kana_2 = req.getParameter("KANA_2");
          umare = req.getParameter("UMARE");

          try{
               //MySQLDriverロード
               Class.forName(DRIVER);
               //MySQL接続
               con = DriverManager.getConnection(URL,USER,PASS);
               //SQL格納
               stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
               //SQL実行
               StringBuffer query = new StringBuffer();
               query.append("select * from class_table where gakuseki_no ='");
               query.append(gakuseki_no);
               query.append("'");

               ResultSet rs = stmt.executeQuery(query.toString());

               //検索結果表示
               StringBuffer sb = new StringBuffer();
               sb.append("<html>");
               sb.append("     <head>");
               sb.append("          <title> 名簿登録 </title>");
               sb.append("     </head>");
               sb.append("     <body bgcolor='#ffffff'>");
               sb.append("          <p> insert.java </p>");
               sb.append("          <center>");
               sb.append("               <caption>");
               sb.append("                    <font size='+3' color='#0000ff'>");
               sb.append("                         <b>　<<名簿登録>> </b>");
               sb.append("                    </font>");
               sb.append("               </caption>");
               sb.append("               <br><br><br>");

               //追加失敗
                                        if(rs.next()){
               sb.append("                    学籍番号");
               sb.append("                    <font size='+3' color='deeppink'>");
               sb.append(                           gakuseki_no);
               sb.append("                    </font>");
               sb.append("                    は既に登録済です。");

               //追加成功(仮登録)
                                        }else{
                                             query = new StringBuffer();
               query.append(                 "insert into class_table(class_no,syusseki_no,gakuseki_no,simei_1,simei_2,kana_1,kana_2,umare)");
               query.append(                 "values('");
               query.append(                 class_no);
               query.append(                 "','");
               query.append(                 "0");
               query.append(                 "','");
               query.append(                 gakuseki_no);
               query.append(                 "','");
               query.append(                 simei_1);
               query.append(                 "','");
               query.append(                 simei_2);
               query.append(                 "','");
               query.append(                 kana_1);
               query.append(                 "','");
               query.append(                 kana_2);
               query.append(                 "','");
               query.append(                 umare);
               query.append(                 "')");
                                             stmt.executeUpdate(query.toString());
               //(名前順に並び変えて再登録)
                                             query = new StringBuffer();
               query.append(                 "select * from class_table where class_no ='");
               query.append(                 class_no);
               query.append(                 "' order by kana_1,kana_2,gakuseki_no");
                                             rs = stmt.executeQuery(query.toString());

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

               sb.append("                   <h1> 名簿登録が完了しました。 </h1>");
                                        }

               sb.append("               ");
               sb.append("          </center>");
               sb.append("          <br><br>");
               sb.append("         <hr><a href='/JV27/class_insert_op1.html'>追加に戻る</a>");
               sb.append("         <hr><a href='/JV27/class_index.html'>ホームに戻る</a>");
               sb.append("     </body>");
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
