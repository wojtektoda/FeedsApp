<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
addEventListener("load",function(){
	$('#1').hide();
	$('.show').click(function(event){

	    $('#1').slideToggle('slow');
	});
	
}); 

</script>
<h2>List of feeds</h2>
<!-- Search form -->
<form class="navbar-form navbar-left" role="search">
	<div class="form-group">
		<input type="text" class="form-control" placeholder="Search">
	</div>
	<button type="submit" class="btn btn-default">Submit</button>
</form>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Title</th>
			<th>Link</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${feeds}" var="feed" varStatus="status">
			<tr>
				<td>${status.index}</td>
				<td><a href="feeds/${feed.id}" class="show">${feed.name}</a></td>
				<td><a href="feeds/${feed.id}" class="show">${feed.url}</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="feedDetails">
<c:out value="${feedDetail.user}" />
</div>

