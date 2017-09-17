<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layouts/taglibs.jsp"%>

<div class="container">
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>User name</th>
				<th>User email</th>
				<th>User password</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${users}" var="user" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><a href="<spring:url value="users/${user.id}" />"><c:out
								value="${user.name}" /></a></td>
					<td><a href="<spring:url value="users/${user.id}" />">${user.email}</a></td>
					<td><a href="<spring:url value="users/${user.id}" />">${user.password}</a></td>
					<td><a href="<spring:url value="/users/remove/${user.id}"/>"
						class="btn btn-danger triggerRemove">Remove</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		$(document).ready(
				function() {

					$('.triggerRemove').click(
							function(e) {
								e.preventDefault();
								$('#modalRemove .removeBtn').attr('href',
										$(this).attr('href')); //get attribute href from this element and set it to modal winows
								$('#modalRemove').modal(); // shows modalRemove window
							})
				});
	</script>
	<!-- ModalRemove -->
	<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Remove user</h4>
				</div>
				<div class="modal-body">Do you really want to remove ?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="" class="btn btn-danger removeBtn">Remove</a>
				</div>
			</div>
		</div>
	</div>
</div>
