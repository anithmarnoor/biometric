<div>
	<c:choose>
		<c:when test="${edit}">
			<c:set var="action"
				value="salDivPercent-edit-${percentages.percentageId}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="salDivPercent-save" />
		</c:otherwise>
	</c:choose>
</div>
<form:form method="POST" modelAttribute="percentages"
	action="${action}" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="percentage">Percentage</label>
			<div class="col-md-7">
				<form:input type="text" path="percentage" id="percentage"
					class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="percentage" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<c:choose>
				<c:when test="${edit}">
					<label class="col-md-2 control-lable" for="designation">Designation</label>
					<div class="col-md-7">
						<form:select path="designation" items="${designations}"
							multiple="false" itemValue="designationId"
							itemLabel="designationName" class="form-control input-sm"
							id="designation" />
						<div class="has-error">
							<form:errors path="designation" class="help-inline" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<label class="col-md-2 control-lable" for="designation">Designation</label>
					<div class="col-md-7">
						<form:select path="designation" items="${designations}"
							multiple="false" itemValue="designationId"
							itemLabel="designationName" class="form-control input-sm"
							id="designation" />
						<div class="has-error">
							<form:errors path="designation" class="help-inline" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<c:choose>
				<c:when test="${edit}">
					<label class="col-md-2 control-lable" for="division">Salary
						Component</label>
					<div class="col-md-7">
						<form:select path="division" items="${salaryComponents}"
							multiple="false" itemValue="componentId"
							itemLabel="componentName" class="form-control input-sm"
							id="division" />
						<div class="has-error">
							<form:errors path="division" class="help-inline" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<label class="col-md-2 control-lable" for="division">Salary
						Component</label>
					<div class="col-md-7">
						<form:select path="division" items="${salaryComponents}"
							multiple="false" itemValue="componentId"
							itemLabel="componentName" class="form-control input-sm"
							id="division" />
						<div class="has-error">
							<form:errors path="division" class="help-inline" />
						</div>
					</div>
				</c:otherwise>
			</c:choose>
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