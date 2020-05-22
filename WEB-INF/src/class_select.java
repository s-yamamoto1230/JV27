import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class class_select extends HttpServlet{
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
          String select_id = req.getParameter("SELECT_ID");
          String class_noStr = req.getParameter("CLASS_NO");
          String gakunen_noStr = req.getParameter("GAKUNEN_NO");
          String teacher_no = req.getParameter("TEACHER_NO");
          String keyword = req.getParameter("keyword");

          String t_simei_1 = "";
          String t_simei_2 = "";
          switch(teacher_no){
               case "1":
                    t_simei_1 ="古田";
                    t_simei_2 ="貴久";
               break;
               case "2":
                    t_simei_1 ="中根";
                    t_simei_2 ="慎也";
               break;
               case "3":
                    t_simei_1 ="水戸";
                    t_simei_2 ="寛樹";
               break;
               case "4":
                    t_simei_1 ="片岡";
                    t_simei_2 ="利幸";
               break;
          }

          StringBuffer sb = new StringBuffer();
          sb.append("<html>");
          sb.append("<head><title>クラス名簿検索</title></head>");
          sb.append("<body bgcolor='#fff'>");
          sb.append("    class_select.java");
          sb.append("    <center>");
          sb.append("         <caption><font size='+3' color='#0000ff'><B>＜＜クラス名簿検索＞＞</B></font></caption>");
          sb.append("         <br><br><br>");
          sb.append("         <font size='+2'>");
          sb.append("              検索結果　");
                                   switch(select_id){
                                        case "1":
          sb.append(                         "クラス");
                                        break;
                                        case "2":
          sb.append(                         "学年");
                                        break;
                                        case "3":
          sb.append(                         "担任");
                                        break;
                                        case "4":
          sb.append(                         "キーワード");
                                        break;
                                   }
          sb.append("         </font>");
          sb.append("         <font size='+2' color='deeppink'>");
                                   switch(select_id){
                                        case "1":
          sb.append(                         class_noStr);
                                        break;
                                        case "2":
                                             if (!(gakunen_noStr.equals("9"))) {
          sb.append(                              gakunen_noStr + "年");
                                             }else{
          sb.append(                              "その他");
                                             }
                                        break;
                                        case "3":
                                             if(teacher_no.equals("1")){
          sb.append(                              "古田貴久 先生");
                                             }else if(teacher_no.equals("2")){
          sb.append(                              "中根慎也 先生");
                                             }else if(teacher_no.equals("3")){
          sb.append(                              "水戸寛樹 先生");
                                             }else if(teacher_no.equals("4")){
          sb.append(                              "片岡利幸 先生");
                                             }
                                        break;
                                        case "4":
          sb.append(                         keyword);
                                        break;
                                   }
          sb.append("         </font>");
          sb.append("         <br><br>");
          sb.append("         <table border='1' bordercolor='darkblue'>");
          sb.append("              <tr>");
                                        if(!(select_id.equals("1"))){
          sb.append("                        <td bgcolor='darkblue'>");
          sb.append("                             <font color='white'>クラス</font>");
          sb.append("                        </td>");
                                        }
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>出席</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>学籍</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>氏名（姓）</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>氏名（名）</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>カナ（セイ）</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>カナ（メイ）</font>");
          sb.append("                   </td>");
          sb.append("                   <td bgcolor='darkblue'>");
          sb.append("                        <font color='white'>生年月日</font>");
          sb.append("                   </td>");
          sb.append("              </tr>");

          try{
               //MySQLDriverロード
               Class.forName(DRIVER);
               //MySQL接続
               con = DriverManager.getConnection(URL,USER,PASS);
               //SQL格納
               stmt = con.createStatement();
               //SQL実行
               StringBuffer query = new StringBuffer();

               switch(select_id){
                    case "1":
                         query.append("select * from class_table where class_no ='");
                         query.append(class_noStr);
                         query.append("' order by syusseki_no");
                    break;
                    case "2":
                         query.append("select * from class_table where class_no like '___");
                         query.append(gakunen_noStr);
                         query.append("%' order by class_no,syusseki_no");
                    break;
                    case "3":
                         query.append("select class_table.class_no,class_table.syusseki_no,class_table.gakuseki_no,class_table.simei_1,class_table.simei_2,class_table.kana_1,class_table.kana_2,class_table.umare from class_table join tannin_table on class_table.class_no = tannin_table.class_no join teacher_table on tannin_table.teacher_no = teacher_table.teacher_no where teacher_table.simei_1 ='");
                         query.append(t_simei_1);
                         query.append("' and teacher_table.simei_2 ='");
                         query.append(t_simei_2);
                         query.append("' order by class_table.class_no,class_table.syusseki_no");
                    break;
                    case "4":
                         query.append("select * from class_table where simei_1 like '%");
                         query.append(keyword);
                         query.append("%' or simei_2 like '%");
                         query.append(keyword);
                         query.append("%' or kana_1 like '%");
                         query.append(keyword);
                         query.append("%' or kana_2 like '%");
                         query.append(keyword);
                         query.append("%' order by class_no,syusseki_no");

                    break;

               }
               ResultSet rs = stmt.executeQuery(query.toString());

               //検索結果
               while(rs.next()){
                    class_no = rs.getString("class_no");
                    syusseki_no = rs.getString("syusseki_no");
                    gakuseki_no = rs.getString("gakuseki_no");
                    simei_1 = rs.getString("simei_1");
                    simei_2 = rs.getString("simei_2");
                    kana_1 = rs.getString("kana_1");
                    kana_2 = rs.getString("kana_2");
                    umare = rs.getString("umare");

          sb.append("              <tr>");

                                        if(!(select_id.equals("1"))){
          sb.append("                        <td>");
          sb.append(                              class_no);
          sb.append("                        </td>");
                                        }

          sb.append("                   <td>");
          sb.append(                         syusseki_no);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         gakuseki_no);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         simei_1);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         simei_2);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         kana_1);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         kana_2);
          sb.append("                   </td>");
          sb.append("                   <td>");
          sb.append(                         umare);
          sb.append("                   </td>");
          sb.append("              </tr>");

               }

          sb.append("         </table><br>");
          sb.append("    </center><br><br>");
          sb.append("    <hr><a href='/JV27/class_select.html'>検索に戻る</a>");
          sb.append("    <hr><a href='/JV27/class_index.html'>ホームに戻る</a>");
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
