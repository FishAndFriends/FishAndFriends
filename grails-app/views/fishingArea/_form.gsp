<%@ page import="fishandfriends.FishingArea" %>



<div class="fieldcontain ${hasErrors(bean: fishingAreaInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="fishingArea.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${fishingAreaInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingAreaInstance, field: 'location', 'error')} required">
	<label for="location">
		<g:message code="fishingArea.location.label" default="Location" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="location" required="" value="${fishingAreaInstance?.location}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingAreaInstance, field: 'catches', 'error')} ">
	<label for="catches">
		<g:message code="fishingArea.catches.label" default="Catches" />
		
	</label>
	

<li class="add">
<g:link controller="catch" action="create" params="['fishingArea.id': fishingAreaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'catch.label', default: 'Catch')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: fishingAreaInstance, field: 'fishes', 'error')} ">
	<label for="fishes">
		<g:message code="fishingArea.fishes.label" default="Fishes" />
		
	</label>
	<g:select name="fishes" from="${fishandfriends.Fish.list()}" multiple="multiple" optionKey="id" size="5" value="${fishingAreaInstance?.fishes*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: fishingAreaInstance, field: 'fishingMen', 'error')} ">
	<label for="fishingMen">
		<g:message code="fishingArea.fishingMen.label" default="Fishing Men" />
		
	</label>
	<g:select name="fishingMen" from="${fishandfriends.FishingMan.list()}" multiple="multiple" optionKey="id" size="5" value="${fishingAreaInstance?.fishingMen*.id}" class="many-to-many"/>

</div>

