import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class class_update2 extends HttpServlet{
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
         
         req.setCharacterEncoding("UTF-8");
         res.setContentType("text/html;charset=UTF8");

         String gakuseki_nostr = req.getParameter("update_data");
         System.out.println(gakuseki_nostr);

        try{
            //MySQLDriverロード
            Class.forName(DRIVER);
            //MySQL接続
            con = DriverManager.getConnection(URL,USER,PASS);
            //SQL格納
            stmt = con.createStatement();
            //SQL実行
            StringBuffer query = new StringBuffer();

            query.append("SELECT * FROM class_table WHERE gakuseki_no ='");
            query.append(gakuseki_nostr);
            query.append("'");
            ResultSet rs = stmt.executeQuery(query.toString());

            //検索結果取得
            String class_no,syusseki_no,gakuseki_no,simei_1,simei_2,kana_1,kana_2,umare;
            while(rs.next()){
                class_no =rs.getString("class_no");
                syusseki_no =rs.getString("syusseki_no");
                gakuseki_no =rs.getString("gakuseki_no");
                simei_1 =rs.getString("simei_1");
                simei_2 =rs.getString("simei_2");
                kana_1 =rs.getString("kana_1");
                kana_2 =rs.getString("kana_2");
                umare =rs.getString("umare");

                //入力データ変換
                req.setAttribute("class_no",class_no);
                req.setAttribute("syusseki_no",syusseki_no);
                req.setAttribute("gakuseki_no",gakuseki_no);
                req.setAttribute("simei_1",simei_1);
                req.setAttribute("simei_2",simei_2);
                req.setAttribute("kana_1",kana_1);
                req.setAttribute("kana_2",kana_2);
                req.setAttribute("umare",umare);
            }

            ServletContext sc = getServletContext();
            sc.getRequestDispatcher("/class_update.jsp").forward(req,res);

            //切断
            stmt.close();
            con.close();
        }
        //例外処理
        catch(SQLException ex){
            PrintWriter out;
            res.setContentType("text/html;charset=UTF-8");
            out = res.getWriter();
            out.println("--- SQL Excepion ---" + "<br>");
            out.println("Message : " + "<br>");
            while(ex != null){
                 out.println(ex.getMessage() + "<br>");
                 ex = ex.getNextException();
            }
       }
       catch(Exception ex){
            PrintWriter out;
            res.setContentType("text/html;charset=UTF-8");
            out = res.getWriter();
            ex.printStackTrace(out);
        }
    }

}
