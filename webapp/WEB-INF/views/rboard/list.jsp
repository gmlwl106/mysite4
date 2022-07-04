<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/includes/boardAside.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>groupNo</th>
									<th>orderNo</th>
									<th>depth</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<%-- <c:forEach items="${rbList }" var="rbVo">
									<tr>
										<td>${rbVo.no }</td>
										<td class="text-left"><a href="./read/${rbVo.no }">${rbVo.title }</a></td>
										<td>${rbVo.name }</td>
										<td>${rbVo.hit }</td>
										<td>${rbVo.regDate }</td>
										<td>${rbVo.groupNo }</td>
										<td>${rbVo.orderNo }</td>
										<td>${rbVo.depth }</td>
										<td><a href="">[삭제]</a></td>
									</tr>
								</c:forEach> --%>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${not empty authUser }">
							<a id="btn_write" href="./writeForm">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
<!-- 준비가 끝났을때 -->
$(document).ready(function(){
	console.log("jquery로 요청 data만 받는 요청");
	
	/* 리스트 요청하고 그리기 */
	fetchList();
});

/* 리스트 요청 */
function fetchList() {
	$.ajax({
		//보낼때
		url : "${pageContext.request.contextPath }/rboard/list",
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},
		
		//받을때
		dataType : "json",
		success : function(rbList){
			//성공시 처리해야될 코드 작성
			console.log(rbList);
			
			//화면 data + html 그린다
			for(var i=0; i<rbList.length; i++) {
				render(rbList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
};

/* 리스트 1개씩 그리기 */
function render(rbVo) {
	console.log("render()");
	var str = "";
	str += '<tr>';
	str += '	<td>'+rbVo.no+'</td>';
	str += '	<td class="text-left"><a href="./read/'+rbVo.no+'">';
	if(rbVo.depth > 0) {
		for(var i=0; i <= rbVo.depth; i++) {
			str += '&ensp;&ensp;';
		}
		str += '▶';
	}
	str += rbVo.title+'</a></td>';
	str += '	<td>'+rbVo.name+'</td>';
	str += '	<td>'+rbVo.hit+'</td>';
	str += '	<td>'+rbVo.regDate+'</td>';
	str += '	<td>'+rbVo.groupNo+'</td>';
	str += '	<td>'+rbVo.orderNo+'</td>';
	str += '	<td>'+rbVo.depth+'</td>';
	if("${authUser.no}" == rbVo.userNo) {
		str += '	<td><a href="">[삭제]</a></td>';
	}
	str += '</tr>';
	console.log(str);
	$("#tbody").append(str);

};


</script>

</html>
