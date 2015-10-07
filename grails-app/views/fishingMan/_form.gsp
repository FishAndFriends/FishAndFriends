<%@ page import="fishandfriends.FishingMan" %>



<div class="fieldcontain ${hasErrors(bean: fishingManInstance, field: 'firstname', 'error')} required">
	<label for="firstname">
		<g:message code="fishingMan.firstname.label" default="Firstname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstname" required="" value="${fishingManInstance?.firstname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingManInstance, field: 'lastname', 'error')} required">
	<label for="lastname">
		<g:message code="fishingMan.lastname.label" default="Lastname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastname" required="" value="${fishingManInstance?.lastname}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingManInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="fishingMan.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${fishingManInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingManInstance, field: 'hashedPassword', 'error')} required">
	<label for="password">
		<g:message code="fishingMan.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${fishingManInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingManInstance, field: 'gender', 'error')} required">
	<label for="gender">
		<g:message code="fishingMan.gender.label" default="Gender" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="gender" from="${fishingManInstance.constraints.gender.inList}" required="" value="${fishingManInstance?.gender}" valueMessagePrefix="fishingMan.gender"/>

</div>

