<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<p style="text-align:center">
    <a href="?lang=en_US">English</a>&nbsp;&nbsp;
    <a href="?lang=fr_FR">French</a>&nbsp;&nbsp;
    <a href="?lang=hi_IN">Hindi</a>&nbsp;&nbsp;
    <a href="?lang=zh_CN">Chineese</a>&nbsp;&nbsp;

</p>

<h1 style="color:blue;text-align:center">Register Employee </h1>

<f:form modelAttribute="emp">
    <table border="0" bgcolor="pink" align="center">
          <tr>
             <td>Employee Id:</td>
             <td><f:input path="empId" readonly="true"/></td>
         </tr>
         <tr>
             <td>Employee Name:</td>
             <td><f:input path="empName"/></td>
         </tr>
         
         
         <tr>
             <td>Employee Salary:</td>
             <td><f:input path="sal"/></td>
         </tr>
          <tr>
             <td>Select Gender:</td>
            <td>
                <f:radiobutton path="gender" value="male" label="Male"/>&nbsp;
                 <f:radiobutton path="gender" value="female" label="Female"/>
            </td>
         </tr>
         
          <tr>
            <td>Select Country</td>
            <td>
               <f:select path="country">
                   <f:options items="${countriesInfo}"/>
               </f:select>
            </td>
         </tr>
         
          <tr>
            <td>Select Languages</td>
            <td>
               <f:select path="languages" multiple="true">
                   <f:options items="${languagesInfo}"/>
               </f:select>
            </td>
         </tr>
         
          <tr>
            <td>Select Hobbies</td>
            <td>
               <f:checkboxes items="${hobbiesInfo}" path="hobbies"/>
            </td>
         </tr>
         
         <tr>
              <td align="right"><input type="image" src="images/submit.jfif" width="50px" height="50px"></td>
              <td align="left"><button type="reset"><img src="images/reset.jfif" width="50px" height="50px"></button></td>
         </tr>
         
         
    </table>
</f:form>