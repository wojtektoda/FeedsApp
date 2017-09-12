<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layouts/taglibs.jsp"%>



<div class="container">
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary btn-lg"
		data-toggle="modal" data-target="#myModal">New feed</button>
		</br></br>
	
	<form:form modelAttribute="feedDTO"
		cssClass="form-horizontal feedForm">
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">New feed</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Name:</label>
							<div class="col-sm-10">
								<form:input path="name" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Url:</label>
							<div class="col-sm-10">
								<form:input path="url" cssClass="form-control" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<input type="submit" class="btn btn-primary" value="Save" />
					</div>
				</div>
			</div>
		</div>
	</form:form>
	



	<script type="text/javascript">
		$(document).ready(function() {
			$('.nav-tabs a:first').tab('show'); //boostrapjs - shows frist tab at the page load
			
			// Staying on the same tab after refreshing the page
		    $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
		        localStorage.setItem('activeTab', $(e.target).attr('href'));
		    });
		    var activeTab = localStorage.getItem('activeTab');
		    if(activeTab){
		        $('.nav-tabs a[href="' + activeTab + '"]').tab('show');
		    }
			
			// trigger Remove function that delegates action to modal window
			$('.triggerRemove').click(function(e){
				e.preventDefault();
				$('#modalRemove .removeBtn').attr('href', $(this).attr('href')); //get attribute href from this element and set it to modal winows
				$('#modalRemove').modal(); // shows modalRemove windowd
			});
			
			// edit by AJAX
			$('.editButton').on('click',function() {
								// Get the record's ID via attribute
								var id = $(this).attr('data-id');
								console.log("The id of the current tab is:"
										+ id);

								// GET current feed Object from the server
								$.ajax({
									url : '/feed/edit/' + id,
									method : 'GET',
									datatype : 'json',
									success : function(response) {
										// Populate the form fields with the data returned from server
										$('#userForm').find('[name="id"]').val(
												response.id).end().find(
												'[name="name"]').val(
												response.name).end().find(
												'[name="url"]').val(
												response.url).end();
									},
									error : function() {
										alert('Error while request..');
									}

								});

							});

					// trigger Edit - function that delegates action to modal window
					$('.triggerEdit').click(
							function(e) {
								var b = e.currentTarget; // only for testing to source of the Event object
								var currentTab = $(this).parent().attr('id'); // retreiving current element Id in tab-pane view
								var numb = currentTab.match(/\d/g); // retrive tab id
								var numb1 = numb.join("");
								e.preventDefault();
								//var currentTab = $('.nav-tabs').tabs('option', 'active');

								// 				$.each(currentTab, function(key, element) {
								//     			console.log('key: ' + key + '\n' + 'value: ' + element);
								// 				});
								//triggered when modal is about to be shown
								$('#myModalEdit').on(
										'show.bs.modal',
										function(e) {

											//get data-id attribute of the clicked element
											//var bookId = $(e.relatedTarget).data('book-id');

											//populate the textbox
											$(e.currentTarget).find(
													'input[name="id"]').val(
													numb1);
											//$(e.currentTarget).find('input[name="name"]').val(numb1);
											//$(e.currentTarget).find('input[name="url"]').val(numb1);
										})
								console.log(numb1);
								console.log(currentTab);
								console.log(b);
								$('input[id=name]').attr('value',
										$(this).attr('href'));
							});

					// feed validation rules
					$(".feedForm").validate(
							{
								rules : {
									name : {
										required : true,
										minlength : 1,
										pattern: true
									},
									url : {
										required : true,
										url : true
									}

								},
								highlight : function(element) {
									$(element).closest('.form-group')
											.removeClass('has-success')
											.addClass('has-error');
								},
								unhighlight : function(element) {
									$(element).closest('.form-group')
											.removeClass('has-error').addClass(
													'has-success');
								}

							});
					jQuery.validator.addMethod("pattern", function(value, element){
					    if (/^[0-9]{9}[vVxX]$/.test(value)) {
					        return false;  // FAIL validation when REGEX matches
					    } else {
					        return true;   // PASS validation otherwise
					    };
					}, "wrong nic number");
				});
	</script>

	<div>
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" id="tabs">
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
				<div class="tab-pane" id="feed_<c:out value="${feed.id}"/>">
				
					<!--  Title section -->
					<h1>
						<strong><c:out value="${feed.name}" /></strong>
					</h1>
					<h4>
						<a href="${feed.url}" ><c:out value="${feed.url}" /></a>
					</h4>
					

					<!--  Button Remove Order -->
					<a href="<spring:url value="/feed/remove/${feed.id}"/>"
						class="btn btn-danger triggerRemove">Remove</a>
									
					<!-- Button Edit Order-->
					<a href="" class="btn btn-info editButton" data-id="${feed.id}" 
					data-toggle="modal" data-target="#myModalEdit">Edit</a>

					<c:if test="${empty feed.items}">
						<div class="alert alert-info">
							<strong>Info!</strong> No records found !
						</div>
					</c:if>
					<c:if test="${not empty feed.items}">
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
										<td><c:out value="${item.publishedDate}" /></td>

										<td><strong> <a
												href="<c:out value="${item.link}" />">${item.title}</a>
										</strong> ${item.description}</td>
										<td><a
											href="<spring:url value="/item/remove/${item.id}"/>"
											class="btn btn-danger triggerRemove">Remove</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
		</c:forEach> 
		</div>
	</div>

</div>


<!-- ModalEdit -->
<form:form id="userForm" modelAttribute="feedDTO" action="/feed/edit"
						cssClass="form-horizontal feedEditForm" method="post">

						<!-- ModalEdit -->
						<div class="modal fade" id="myModalEdit" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">Edit feed</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
 											<form:hidden path="id" value="" /> <!-- data used to identify ID of currently manipulated object (current scope) -->
											<label for="name" class="col-sm-2 control-label">Name:</label>
											<div class="col-sm-10">
												<form:input path="name" value=""
													cssClass="form-control" autofocus="autofocus" />
											</div>
										</div>
										<div class="form-group">
											<label for="name" class="col-sm-2 control-label">Url:</label>
											<div class="col-sm-10">
												<form:input path="url" cssClass="form-control"
													value="" />
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<input type="submit" class="btn btn-primary" value="Save" />
									</div>
								</div>
							</div>
						</div>
</form:form>

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
				<h4 class="modal-title" id="myModalLabel">Remove user feed</h4>
			</div>
			<div class="modal-body">Do you really want to remove user feed
				?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>

