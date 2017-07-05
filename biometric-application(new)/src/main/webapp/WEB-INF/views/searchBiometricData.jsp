
<form:form method="POST" modelAttribute="biometricData" enctype="multipart/form-data" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="year">Year</label>
			<div class="col-md-7">
				<form:input type="text" path="year" id="year"
					class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="year" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="month">Month</label>
			<div class="col-md-7">
				<form:input type="text" path="month" id="month"
					class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="month" class="help-inline" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-actions floatRight">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Update" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-wages' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Add" class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-wages' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</form:form>
