<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layouts/taglibs.jsp"%>



<div class="container">
	<h2><c:out value="${users.name}"/></h2>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.nav-tabs a:first').tab('show'); //boostrapjs - shows frist tab at the page load
			$('.triggerRemove').click(function(e){
				e.preventDefault();
				$('#modalRemove .removeBtn').attr('href', $(this).attr('href')); //get attribute href from this element and set it to modal winows
				$('#modalRemove').modal(); // shows modalRemove window
			})
		});
	</script>

	<div>
		
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<c:forEach items="${users.feeds}" var="feed">
				<li><a href="#feed_${feed.id}" data-toggle="tab">${feed.name}</a></li>
			</c:forEach>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
		<c:if test="${empty users.feeds}">
						<div class="alert alert-info">
							<strong>Info!</strong> No records found !
						</div>
					</c:if>
			<c:forEach items="${users.feeds}" var="feed">
			
				<div class="tab-pane" id="feed_${feed.id}">
					<h1>
						<c:out value="${feed.name}" />
					</h1>
					<p>
					<a href="<spring:url value="/feed/remove/${feed.id}"/>" class="btn btn-danger triggerRemove">Remove</a>
					${feed.url}</p>
<%-- 					<c:if test="${empty feed.items}"> --%>
						<div class="alert alert-info">
							<strong>Info!</strong> No records found !
						</div>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${not empty feed.items}"> --%>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Title</th>
									<th>Link</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${feed.items}" var="item" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td><a href="<spring:url value="items/${item.id}" />">${item.title}</a></td>
										<td><a href="<spring:url value="items/${item.id}" />">${item.link}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
<%-- 					</c:if> --%>
				</div>
			</c:forEach>
		</div>
	</div>

</div>

<!-- ModalRemove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove user feed</h4>
      </div>
      <div class="modal-body">
        Do you really want to remove user feed ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>
