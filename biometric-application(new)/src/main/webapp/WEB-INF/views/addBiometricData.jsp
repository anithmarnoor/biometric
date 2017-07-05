
<form:form method="POST" modelAttribute="biometricData"
	enctype="multipart/form-data" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="year">Year</label>
			<div class="col-md-7">
				<form:input path="year" id="year" placeholder="YYYY" />
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
				<form:input path="month" id="month" placeholder="MM"/>
				<div class="has-error">
					<form:errors path="month" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="file">Upload a
				document</label>
			<div class="col-md-7">
				<form:input type="file" path="file" id="file"
					class="form-control input-sm" placeholder=".xls or .dat or .xslx or .csv"/>
				<div class="has-error">
					<form:errors path="file" class="help-inline" />
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="color:red">
			${error}
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





















<%-- 
<form:form method="POST" modelAttribute="biometricData"
	enctype="multipart/form-data" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="year">Year</label>
			<div class="col-md-7">
				<form:input path="year" id="year" placeholder="YYYY" />
				<div class="has-error">
					<form:errors path="year" class="help-inline" />
<form:form method="POST" modelAttribute="biometricData"
	enctype="multipart/form-data" class="form-horizontal">
	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="startDate">Start Date</label>
			<div class="col-md-7">
				<form:input path="startDate" id="startDate" />
				<div class="has-error">
					<form:errors path="startDate" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="endDate">End Date</label>
			<div class="col-md-7">
				<form:input path="endDate" id="endDate" />
				<div class="has-error">
					<form:errors path="endDate" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="file">Upload a
				document</label>
			<div class="col-md-7">
				<form:input type="file" path="file" id="file"
					class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="file" class="help-inline" />
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
					
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="month">Month</label>
			<div class="col-md-7">
				<form:input path="month" id="month" placeholder="MM"/>
				<div class="has-error">
					<form:errors path="month" class="help-inline" />
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-12">
			<label class="col-md-3 control-lable" for="file">Upload a
				document</label>
			<div class="col-md-7">
				<form:input type="file" path="file" id="file"
					class="form-control input-sm" placeholder=".xls or .dat or .xslx or .csv"/>
				<div class="has-error">
					<form:errors path="file" class="help-inline" />
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="color:red">
			${error}
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
 --%>