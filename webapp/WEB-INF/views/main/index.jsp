<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">
	
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		
		<div id="container" class="clearfix">
			<!-- aside 없음 -->
			<div id="full-content">
			
				<!-- content-head 없음 -->
				<div id="index"> 
				
					<img id="profile-img" src="${pageContext.request.contextPath }/assets/image/profile.jpg">
					
					<div id="greetings">
						<!-- [ 영국 런던을 중심으로 구글지도 만들기 ] -->
						<div id="googleMap" style="width: 100%;height: 700px;"></div>
						
						<script>
						   function myMap(){
							   	//위치 좌표
								var myLocation = new google.maps.LatLng(37.481304, 126.952476);
							   	
							   	//맵의 중앙 지정
							   	var map = new google.maps.Map(document.getElementById("googleMap"), {
							   		zoom: 10,
							   		center: myLocation
							   	});
							   	
							   	//마커
							   	var marker = new google.maps.Marker({
							   		position: myLocation,
							   		map: map
							   	});
							   	
							   	//마커 정보창
							   	var infowindow = new google.maps.InfoWindow({
							   		content: "서울대입구역"
							   	});
							   	
							   	//마커 클릭하면 확대
							   	marker.addListener("click",function() {
							   		map.setZoom(18);
							   		map.setCenter(marker.getPosition());
							   		infowindow.open(marker.get("map"), marker);
							   	});
							   	
							   	//맵 클릭 이벤트
							   	google.maps.event.addListener(map, "click", function(event) {
							   		placeMarker(event.latLng)
							   	});
							   	
							   	//클릭했을때 마커가 찍히게
							   	function placeMarker(location) {
							   		new google.maps.Marker({
							   			position: location,
							   			map: map
							   		});
							   		map.setCenter(location)
							   	}
							   	
							   
								
						   }
						   
						</script> 
						<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB6MxegCCXt0IWF5C4RAfFRHXcVN0T_RhE&callback=myMap"></script>
					</div>
					<!-- //greetings -->
					
					<div class="clear"></div>
					
				</div>
				<!-- //index -->
				
			</div>
			<!-- //full-content -->
			

		</div>
		<!-- //container -->
		
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>