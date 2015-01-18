<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<script src="<c:url value="/resources/jquery-2.1.1.min.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:insertAttribute name="title" ignore="true" />  
</title>  
</head>  
<body>  
    <table border="1" cellspacing="2" align="center">  
        <tr>  
            <td height="30" colspan="2"><tiles:insertAttribute name="header" />  
            </td>  
        </tr>  
        <tr>  
            
            <td width="400"><tiles:insertAttribute name="body" />  
            </td>  
        </tr>  
        <tr>  
            <td height="30" colspan="2"><tiles:insertAttribute name="footer" />  
            </td>  
        </tr>  
    </table>  
</body>  
</html>  