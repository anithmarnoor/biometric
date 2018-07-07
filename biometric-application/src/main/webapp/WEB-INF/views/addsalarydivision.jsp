<div>
	<c:choose>
		<c:when test="${edit}">
			<c:set var="action" value="salaryDivision-edit-${salaryDivision.componentId}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="salaryDivision-save-" />
		</c:otherwise>
	</c:choose>
</div>
<form:form method="POST" modelAttribute="salaryDivision"
	action="${action}" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="componentName">Component
				Name</label>
			<div class="col-md-7">
				<form:input type="text" path="componentName" id="componentName"
					class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="componentName" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="isDeductible">Should
				be deducted from salary?</label>
			<div class="col-md-7">
				<form:radiobutton path="isDeductible" value="Yes" />
				Yes
				<form:radiobutton path="isDeductible" value="No" />
				No
				<div class="has-error">
					<form:errors path="isDeductible" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-actions floatRight">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-salaryDivision' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-salaryDivision' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</form:form>