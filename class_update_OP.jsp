<%@ page contentType="text/html;charset=UTF-8"%>
<% response.setContentType("text/html;charset=UTF-8");%>

<%
    String class_no_before = request.getAttribute("class_no").toString();
%>
<html>
    <head>
        <title>class_update_OP.jsp</title>
    </head>
    <body bgcolor="#ffffff">
        <p>class_update_OP.jsp</p>
        <center>
            <caption>
                <font size="+3" color="#0000ff">
                    <b>＜＜名簿変更＞＞</b>
                </font>
            </caption>
            <br>
            <form name="frm" action="./class_update3_OP" method="post">
                <input type="hidden" name="gakuseki_no" value="<%= request.getAttribute("gakuseki_no").toString() %>">
                <input type="hidden" name="class_no_before" value="<%= request.getAttribute("class_no").toString() %>">
                <input type="hidden" name="syusseki_no" value="<%= request.getAttribute("syusseki_no").toString() %>">
                <font size="+1" color="#0000ff">
                    <p>名簿情報を変更して下さい</p>
                <font>
                <hr size="5" width="80%">
                <table border=0>
                    <tr>
                        <td>学籍番号</td>
                        <td><%= request.getAttribute("gakuseki_no").toString() %></td>
                    </tr>
                    <tr>
                        <td>区分</td>
                        <td>
                            <select name="class_no_after" size="1">
                                <option value="AT11A192">AT11A192
                                <option value="AT11B203">AT11B203
                                <option value="CG11A172">CG11A172
                                <option value="IT11A172">IT11A172
                                <option value="AT12A165">AT12A165
                                <option value="AT12B165">AT12B165
                                <option value="IH12A101">IH12A101
                                <option value="IW12A185">IW12A185
                                <option value="AT13A181">AT13A181
                                <option value="AP13A223">AP13A223
                                <option value="IH13A181">IH13A181
                                <option value="IW13A187">IW13A187
                                <option value="CD13A166">CD13A166
                                <option value="AP14A226">AP14A226
                                <option value="AT14A226">AT14A226
                                <option value="IH14A223">IH14A223
                                <option value="CT14A187">CT14A187
                                <option value="IW14A187">IW14A187
                                <option value="xx14Axxx">xx14Axxx
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>出席番号</td>
                        <td>
                            <p>自動で振り分けます</p>
                        </td>
                    </tr>
                    <tr>
                        <td>氏名（姓）</td>
                        <td>
                            <input type="text" name="simei_1" size=20 value="<%= request.getAttribute("simei_1").toString() %>">
                        </td>
                    </tr>
                    <tr>
                        <td>氏名（名）</td>
                        <td>
                            <input type="text" name="simei_2" size=20 value="<%= request.getAttribute("simei_2").toString() %>">
                        </td>
                    </tr>
                    <tr>
                        <td>カナ（姓）</td>
                        <td>
                            <input type="text" name="kana_1" size=20 value="<%= request.getAttribute("kana_1").toString() %>">
                        </td>
                    </tr>
                    <tr>
                        <td>カナ（名）</td>
                        <td>
                            <input type="text" name="kana_2" size=20 value="<%= request.getAttribute("kana_2").toString() %>">
                        </td>
                    </tr>
                    <tr>
                        <td>生年月日</td>
                        <td>
                            <input type="text" name="umare" size=20 value="<%= request.getAttribute("umare").toString() %>">
                        </td>
                    </tr>
                </table>
                <hr size="5" width="80%">
                <br>
                <input type="submit" value="名簿更新">
                <input type="reset" value="入力クリア">
                <br><br>
            </form>
            <hr>
            <a href="../class_update_OP.html">名簿選択へ戻る</a>
            <a href="../class_index.html">メニューへ戻る</a>
            <br>
        </center>
        <script>
            switch ("<%= request.getAttribute("class_no").toString() %>"){
                case "AT11A192":  class_no = 0;
                    break;
                case "AT11B203":  class_no = 1;
                    break;
                case "CG11A172":  class_no = 2;
                    break;
                case "IT11A172":  class_no = 3;
                    break;
                case "AT12A165":  class_no = 4;
                    break;
                case "AT12B165":  class_no = 5;
                    break;
                case "IH12A101":  class_no = 6;
                    break;
                case "IW12A185":  lass_no = 7;
                    break;
                case "AT13A181":  class_no = 8;
                    break;
                case "AP13A223":  class_no = 9;
                    break;
                case "IH13A181":  class_no = 10;
                    break;
                case "IW13A187":  class_no = 11;
                    break;
                case "CD13A166":  class_no = 12;
                    break;
                case "AP14A226":  class_no = 13;
                    break;
                case "AT14A226":  class_no = 14;
                    break;
                case "IH14A223": class_no = 15;
                    break;
                case "CT14A187":  class_no = 16;
                    break;
                case "IW14A187":  class_no = 17;
                    break;
                case "xx14Axxx":  class_no = 18;
                    break;
            }
             document.frm.class_no_after.selectedIndex = class_no;
             console.log(class_no);
        </script>
    </body>
</html>
