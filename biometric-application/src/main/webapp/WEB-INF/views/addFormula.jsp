<%@include file="includeLibs.jsp"%>
<script type="text/javascript">
	function appendToFormula(value) {
		var element = document.getElementById("formulaText");
		var existingValue = element.value;
		existingValue = existingValue + "" + value;
		element.value = existingValue;
	}
	function clearFormula() {
		var element = document.getElementById("formulaText");
		element.value = "";
	}
	function validate() {
		document.getElementById("calculator").action = "validateFormula";
		document.getElementById("calculator").submit();
	}
</script>

<div>
	<c:choose>
		<c:when test="${edit}">
			<c:set var="action"
				value="componentFormula-edit-${percentages.percentageId}" />
		</c:when>
		<c:otherwise>
			<c:set var="action" value="saveComponentFormula" />
		</c:otherwise>
	</c:choose>
</div>

<form:form method="POST" modelAttribute="percentages" name="calculator"
	action="${action }" class="form-horizontal">
	<div class="panel panel-default">
		<div class="uploadcontainer">
			<section>
				Select Component<label class="select"><form:select
						path="component" items="${salaryComponents}" multiple="false"
						itemValue="componentId" itemLabel="componentName"
						class="form-control input-sm" id="component" /></label>
				<div class="has-error">
					<form:errors path="component" class="help-inline" />
				</div>
			</section>
			<div class="box">
				<div class="keys">
					<div class="display">
						<label class="text"><form:input path="formula"
								id="formulaText" name="display" /></label>
					</div>
					<div class="has-error">
						<form:errors path="formula" class="help-inline" />
					</div>
					<table style="width: 25%">
						<tr>
							<td><input type="button" class="button gray" name="one"
								value="1" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="two"
								value="2" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="three"
								value="3" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="four"
								value="4" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button black" name="plus"
								value="+" onclick="appendToFormula(this.value)"></td>
						</tr>
						<tr>
							<td><input type="button" class="button gray" name="five"
								value="5" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="six"
								value="6" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="seven"
								value="7" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="eight"
								value="8" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button black" name="minus"
								value="-" onclick="appendToFormula(this.value)"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" class="button gray" name="nine"
								value="9" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="zero"
								value="0" onclick="appendToFormula(this.value)"></td>

							<td></td>
							<td><input type="button" class="button black" name="times"
								value="x" onclick="appendToFormula(this.value)"></td>
						</tr>
						<tr>
							<td><input type="button" class="button gray" name="."
								value="." onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name="("
								value="(" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" class="button gray" name=")"
								value=")" onclick="appendToFormula(this.value)"></td>
							<td><input type="button" id="clear" name="clear" value="c"
								onclick="clearFormula()"></td>
							<td><input type="button" class="button black" name="div"
								value="/" onclick="appendToFormula(this.value)"></td>
							<td></td>
						</tr>

					</table>
					<table>
						<c:forEach items="${salaryComponentsAll}" var="component1">
							<tr>
								<td><input type="button"
									value="${component1.componentName}"
									onclick="appendToFormula(this.value)" /></td>
							</tr>
						</c:forEach>
					</table>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-actions floatRight">
			<c:choose>
				<c:when test="${edit}">
					<input type="submit" value="Validate and Update"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-salaryDivision' />">Cancel</a>
				</c:when>
				<c:otherwise>
					<input type="submit" value="Validate and Save"
						class="btn btn-primary btn-sm" /> or <a
						href="<c:url value='view-salaryDivision' />">Cancel</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</form:form>
