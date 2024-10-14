<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="sp" %>

<style>
	blink{
		color:red;
		-webkit-animation:blink 2s step-end infinite;
		animation:blink 1s step-end infinite;
	}
	
	@-webkit-keyframes blink{
	   67%{opacity:0}
	}
	
	@keyframes blink{
	    67%{opacity:0}
	}
</style>

<br>
<p style="text-align:center">
    <a href="?lang=en_US">English</a>&nbsp;&nbsp;
    <a href="?lang=fr_FR">French</a>&nbsp;&nbsp;
    <a href="?lang=hi_IN">Hindi</a>&nbsp;&nbsp;
    <a href="?lang=zh_CN">Chineese</a>&nbsp;&nbsp;

</p>

<h3 style="text-align:center"><a href="report?type=pdf">Download As Pdf</a></h3>
<h3 style="text-align:center"><a href="report?type=excel">Download As Excel</a></h3>
 <h2 style="color:blue;text-align:center"><sp:message code="title"></sp:message></h2>
<c:choose>
   <c:when test="${!empty page}">
         <table border="1" bgcolor="cyan" align="center">
            <tr  bgcolor="pink" align="center">
                 <th><sp:message code="id"></sp:message></th>
                  <th><sp:message code="name"></sp:message></th>
                   <th><sp:message code="sal"></sp:message></th>
                   <th><sp:message code="gender"></sp:message></th>
                    <th><sp:message code="country"></sp:message></th>
                     <th><sp:message code="state"></sp:message></th>
                      <th><sp:message code="dob"></sp:message></th>
                      <th><sp:message code="doj"></sp:message></th>
                    <th><sp:message code="languages"></sp:message></th>
                    <th><sp:message code="hobbies"></sp:message></th>
                    <th>Download Resume</th>
                     <th>Download Photo</th>
                   <th>ACTIONS</th>
            </tr>
            <c:forEach var="emp" items="${page.getContent()}">
                 <tr>
                    <td align="center">${emp.empId}</td>
                     <td align="center">${emp.empName}</td>
                      <td align="center">${emp.sal}</td>
                       <td align="center">${emp.gender}</td>
                       <td align="center">${emp.country}</td>
                       <td align="center">${emp.state}</td>
                        <td align="center">${emp.dob}</td>
                         <td align="center">${emp.doj}</td>
                       <td align="center"><c:forEach var="i" items="${emp.languages}"> ${i},</c:forEach></td>
                       <td align="center"><c:forEach var="i" items="${emp.hobbies}"> ${i},</c:forEach></td>
                       <td align="center"><a href="download?empId=${emp.empId}&type=resume"><img src="images/down.jfif" width="60px" height="60px"></a></td>
                       <td align="center"><a href="download?empId=${emp.empId}&type=photo"><img src="images/down.jfif" width="60px" height="60px"></a></td>
                      <td>
                         <a href="edit?empId=${emp.empId}"><img src="images/edit.jfif" width="80px" height="50px"></a>
                         <a href="delete?empId=${emp.empId}" onclick="confirm('Do u want to delete')"><img src="images/delete.jpg" width="80px" height="50px"></a>
                      </td>
                 </tr>
            </c:forEach>
         </table>
         <br><br>
         <p style="text-align:center">
             <c:if test="${!page.isFirst() }">
                 <a href="./?page=0">[first]</a>&nbsp;&nbsp;
             </c:if>
             <c:if test="${!page.isLast() }">
                 <a href="./?page=${page.getNumber()+1 }">[next]</a>&nbsp;&nbsp;
             </c:if>
             
             <c:forEach var="i" begin="1" end="${page.getTotalPages()}" step="1">
                  [<a href="./?page=${i-1}">${i}</a>]&nbsp;&nbsp;
              </c:forEach>
              
              
              <c:if test="${!page.isLast() }">
                 <a href="./?page=${page.getTotalPages()-1 }">[last]</a>&nbsp;&nbsp;
             </c:if>
             <c:if test="${!page.isFirst() }">
                 <a href="./?page=${page.getNumber()-1 }">[prvious]</a>&nbsp;&nbsp;
             </c:if>
         </p>
         
         
   </c:when>
   <c:otherwise>
       <h1 style="color:red;text-align:center">Records Not Found</h1>
   </c:otherwise>
</c:choose>
<blink><h2 style="color:red;text-align:center">${msg}</h2></blink>
<h1 style="text-align:center"><a href="add"><img src="images/add.jfif" width="100px" height="100px"></h1>
    