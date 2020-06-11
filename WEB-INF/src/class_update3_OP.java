import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class class_update3_OP extends HttpServlet{
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
          Statement stmt_update = null;
          Statement stmt_search = null;

          String class_no_after,class_no_before,syusseki_no,simei_1,simei_2,kana_1,kana_2,umare,gakuseki_no;
          PrintWriter out;
          req.setCharacterEncoding("UTF-8");
          res.setContentType("text/html;charset=UTF8");
          out = res.getWriter();

          //WebブラウザのFormからパラメータを取得
          class_no_after = req.getParameter("class_no_after");
          class_no_before = req.getParameter("class_no_before");
          syusseki_no = req.getParameter("syusseki_no");
          simei_1 = req.getParameter("simei_1");
          simei_2 = req.getParameter("simei_2");
          kana_1 = req.getParameter("kana_1");
          kana_2 = req.getParameter("kana_2");
          umare = req.getParameter("umare");
          gakuseki_no = req.getParameter("gakuseki_no");

          try{
               //MySQLDriverロード
               Class.forName(DRIVER);
               //MySQL接続
               con = DriverManager.getConnection(URL,USER,PASS);
               //SQL格納
               stmt_update = con.createStatement();
               stmt_search = con.createStatement();
               //SQL更新実行
               StringBuffer query = new StringBuffer();
               query.append("UPDATE class_table SET class_no ='");
               query.append(class_no_after);
               query.append("',syusseki_no = '");
               query.append(syusseki_no);
               query.append("',simei_1 = '");
               query.append(simei_1);
               query.append("',simei_2 = '");
               query.append(simei_2);
               query.append("',kana_1 = '");
               query.append(kana_1);
               query.append("',kana_2 = '");
               query.append(kana_2);
               query.append("',umare = '");
               query.append(umare);
               query.append("' WHERE gakuseki_no = '");
               query.append(gakuseki_no);
               query.append("'");
               System.out.println(query.toString());
               stmt_update.executeUpdate(query.toString());

               //更新後クラス(出席番号振り分け)
               query = new StringBuffer();
               query.append("SELECT * FROM class_table WHERE class_no ='");
               query.append(class_no_after);
               query.append("' ORDER BY kana_1,kana_2,gakuseki_no");
               ResultSet rs = stmt_search.executeQuery(query.toString());
               System.out.println(query.toString());

               int auto_no =0;

               while(rs.next()){

                    gakuseki_no = rs.getString("gakuseki_no");

                    auto_no =auto_no+1;
                    String syusseki = String.valueOf(auto_no);
                    query = new StringBuffer();

                    if(auto_no < 10){
                         query.append("UPDATE class_table SET syusseki_no ='");
                         query.append("0"+auto_no);
                         query.append("' WHERE gakuseki_no = '");
                         query.append(gakuseki_no);
                         query.append("'");
                    }else{
                         query.append("UPDATE class_table SET syusseki_no ='");
                         query.append(auto_no);
                         query.append("' WHERE gakuseki_no = '");
                         query.append(gakuseki_no);
                         query.append("'");
                    }
                    stmt_update.executeUpdate(query.toString());
                    System.out.println(query.toString());
                    auto_no = Integer.parseInt(syusseki);
               }

               //更新前クラス(出席番号振り分け)
               query = new StringBuffer();
               query.append("SELECT * FROM class_table WHERE class_no ='");
               query.append(class_no_before);
               query.append("' ORDER BY kana_1,kana_2,gakuseki_no");
               rs = stmt_search.executeQuery(query.toString());
               System.out.println(query.toString());

               auto_no =0;

               while(rs.next()){

                    gakuseki_no = rs.getString("gakuseki_no");

                    auto_no =auto_no+1;
                    String syusseki = String.valueOf(auto_no);
                    query = new StringBuffer();

                    if(auto_no < 10){
                         query.append("UPDATE class_table SET syusseki_no ='");
                         query.append("0"+auto_no);
                         query.append("' WHERE gakuseki_no = '");
                         query.append(gakuseki_no);
                         query.append("'");
                    }else{
                         query.append("UPDATE class_table SET syusseki_no ='");
                         query.append(auto_no);
                         query.append("' WHERE gakuseki_no = '");
                         query.append(gakuseki_no);
                         query.append("'");
                    }
                    stmt_update.executeUpdate(query.toString());
                    System.out.println(query.toString());
                    auto_no = Integer.parseInt(syusseki);
               }

               //検索結果表示
               StringBuffer sb = new StringBuffer();
               sb.append("<html>");
               sb.append("     <head>");
               sb.append("          <title> 名簿更新 </title>");
               sb.append("     </head>");
               sb.append("     <body bgcolor='#ffffff'>");
               sb.append("          <p> update.java </p>");
               sb.append("          <center>");
               sb.append("               <caption>");
               sb.append("                    <font size='+3' color='#0000ff'>");
               sb.append("                         <b>　<<名簿更新>> </b>");
               sb.append("                    </font>");
               sb.append("               </caption>");
               sb.append("               <br><br><br>");

               //追加成功
               sb.append("                   <h1> 名簿更新が完了しました。 </h1>");

               sb.append("               ");
               sb.append("          </center>");
               sb.append("          <br><br>");
               sb.append("         <hr><a href='../class_update_OP.html'>更新に戻る</a>");
               sb.append("         <hr><a href='../class_index.html'>ホームに戻る</a>");
               sb.append("     </body>");
               sb.append("</html>");

               out.println(sb.toString());
               System.out.println("---------------------------------------------------------------------------------");

               //切断
               stmt_update.close();
               stmt_search.close();
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
