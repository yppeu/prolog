<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>게시판</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

 	<%@ include file="../layout/header2.jsp"%><br>
 	<h1>HTML 게시판</h1>
					<hr>
 			<c:choose>
 				<c:when test="${principal.user.username != null }"> <!-- 로그인한 회원이 비회원이 아닐 때만 글쓰기 권한을 주기위해 -->
 					<div align="right"><a href="write?user_id=${principal.user.username}"><button type="button" class="btn btn-info" id="writeBtn"><img src="../images/pencil.png" width="30"></button></a></div>
				</c:when>
			</c:choose>		
					</table>
					<table align="center">

			<tbody style="width: 50%">
			<div id="grid">
			<c:choose>
			<c:when test="${principal.user.username == null}"><!-- 로그인한 회원이 비회원이라면 댓글작성 등 기능을 이용 못하도록  -->
			
			<c:forEach var="imsi" items="${menu}"> 
		        				
					<div class='image1'><a href = 'loginboardView?contents=${imsi.contents}&image=${imsi.image}&title=${imsi.title}&lang=${imsi.lang}&num=${imsi.num}&date1=${imsi.date1}&writer=${imsi.user_id}'>
						 <img id='hov1' src='/upload/${imsi.image}' width="200" height="200"></a><h6 id='date1'>작성일: ${imsi.date1}</h6></div>
				</c:forEach>
			</c:when>
			<c:otherwise>
			
				<c:forEach var="imsi" items="${menu}"> 
		        				
					<div class='image1'><a href = 'loginboardView?contents=${imsi.contents}&image=${imsi.image}&title=${imsi.title}&lang=${imsi.lang}&num=${imsi.num}&date1=${imsi.date1}&user_id=${principal.user.username}&writer=${imsi.user_id}'>
						 <img src='/upload/${imsi.image}' width="200" height="200"></a><h6 id='date1'>작성일: ${imsi.date1}</h6></div>
				</c:forEach>
			</c:otherwise>
			</c:choose>		
			 </tbody>
			 </div>
			 		<button type="button" class="btn btn-info" id="btnUpdate" onclick="history.go(-1)">뒤로가기</button>	
			 </table>
					
			</div>
			</div>
			</div>
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>
