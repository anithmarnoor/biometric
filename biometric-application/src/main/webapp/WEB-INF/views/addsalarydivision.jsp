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

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ltaPercentage">Leave
						Travel Allowance</label>
					<div class="col-md-7">
						<form:input path="ltaPercentage" id="ltaPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ltaPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="mrPercentage">Medical
						Reimbursement</label>
					<div class="col-md-7">
						<form:input path="mrPercentage" id="mrPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="mrPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="esiPercentage">ESI</label>
					<div class="col-md-7">
						<form:input path="esiPercentage" id="esiPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="esiPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="saPercentage">Special
						Allowance</label>
					<div class="col-md-7">
						<form:input path="saPercentage" id="saPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="saPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="incomeTaxPercentage">Income
						Tax</label>
					<div class="col-md-7">
						<form:input path="incomeTaxPercentage" id="incomeTaxPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="incomeTaxPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="pfPercentage">Provident
						Fund</label>
					<div class="col-md-7">
						<form:input path="pfPercentage" id="pfPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="pfPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ptPercentage">Profession
						Tax</label>
					<div class="col-md-7">
						<form:input path="ptPercentage" id="ptPercentage"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ptPercentage" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
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