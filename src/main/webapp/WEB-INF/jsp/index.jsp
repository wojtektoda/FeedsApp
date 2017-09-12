<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
		
		
		$(document).ready(function() {
			$("#itemDetails").hide();
		
			
			$('.table-row').on('dblclick', function(){
				$('#homeTable').toggle();
				$("#itemDetails").show();
				$('#itemDetails').attr('data-id',$(this).data('id') );
				});
			})


</script>
<h1>Latest feeds</h1>

<table id="homeTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Date</th>
									<th>Title</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${items}" var="item" varStatus="status">
									<tr class="table-row" data-id="${item.id}">
										<td><c:out value="${item.publishedDate}" />
										</br>
										<c:out value="${item.feed.name}"/></td>

										<td><strong> <a
												href="<c:out value="${item.link}" />">${item.title}</a>
										</strong> ${item.description}</td>
									</tr>
								</c:forEach>
							</tbody>
</table>
						
						<button id="myButton">Hello</button>
						
						<div id="itemDetails">
							<h1><c:out value="${item.title}"/></h1>
							<h4><c:out value="${item.description}"/></h4>
						</div>

