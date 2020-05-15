<%@ page contentType="text/html; charset=UTF-8" %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<HTML>
    <HEAD><TITLE>param2.jsp</TITLE>
    </HEAD>
    <BODY>
        param2.jsp テスト
        <CENTER><H1>サーブレットさんが<BR>
        HTMLさんからのデータを届けてくれました
        <BR><BR>
        あなたは
        <BR><FONT COLOR='deeppink'>
        <%= request.getAttribute( "address1" ).toString() %>
        </FONT>にお住まいの
        <FONT COLOR='deeppink'>
        <% String aaa = request.getAttribute( "namae" ).toString(); %>
        <%= aaa %>
        </FONT>さんですね<BR>
        ほいでもって<BR>
        <FONT COLOR='deeppink'>
        <%= request.getAttribute( "toshi" ).toString() %>
        </FONT>才の
        <FONT COLOR='deeppink'>
        <% if ( request.getAttribute( "seibetu" ).toString().equals( "1" ) ) { %>
              男性
        <% } else { %>
              女性
        <% } %>
        </FONT>なんですね</H1></CENTER>
        <HR>
        <A HREF="/JV27/param2.html">戻る</A> 
    </BODY>
</HTML>