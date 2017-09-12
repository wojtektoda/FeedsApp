<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../layouts/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<form:form modelAttribute="userDTO" cssClass="form-horizontal registration-form">

		<c:if test="${param.success eq true }">
			<div class="alert alert-success">Registration succsessfull!</div>
		</c:if>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" /> <!-- #name  -->
				<form:errors path="name"/>
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email:</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password:</label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password"/>
				<!-- path corresponds to attributes name of object transfering data from the form -->
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password again:</label>
			<div class="col-sm-10">
				<input type="password" name="password_again" id="password_again" class="form-control"/>
				<!-- path corresponds to attributes name of object transfering data from the form -->
			</div>
		</div>
			<button type="submit" class="btn btn-large btn-primary">Submit</button>

	</form:form>
	<script>
	$(document).ready(function(){
		$(".registration-form").validate(
				{
					rules:{
						name: {
							required:true,
							minlength:3,
							remote: {
								url:"<spring:url value='register/available'/>",
								type: "get",
								data: {
									username: function(){
										return $("#name").val();
									}
								}
							}
						},
									
						
						email: {required:true,email:true},
						password: {required:true,minlength:5},
						password_again: {required:true,minlength:5, equalTo:"#password"}
					},
					highlight: function(element){
						$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
					},
					unhighlight: function(element){
						$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
					},
					messages: {
						name: {
							remote: "Such user already exists!"
						}
					}
				
				})
	});
	</script>
</div>