<%@ page import="fishandfriends.Catch" %>



<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="catch.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${catchInstance?.date}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'weight', 'error')} required">
	<label for="weight">
		<g:message code="catch.weight.label" default="Weight" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="weight" value="${fieldValue(bean: catchInstance, field: 'weight')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="catch.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" value="${fieldValue(bean: catchInstance, field: 'size')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'fishingMan', 'error')} required">
	<label for="fishingMan">
		<g:message code="catch.fishingMan.label" default="Fishing Man" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fishingMan" name="fishingMan.id" from="${fishandfriends.FishingMan.list()}" optionKey="id" required="" value="${catchInstance?.fishingMan?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'fishingArea', 'error')} required">
	<label for="fishingArea">
		<g:message code="catch.fishingArea.label" default="Fishing Area" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fishingArea" name="fishingArea.id" from="${fishandfriends.FishingArea.list()}" optionKey="id" required="" value="${catchInstance?.fishingArea?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: catchInstance, field: 'fish', 'error')} required">
	<label for="fish">
		<g:message code="catch.fish.label" default="Fish" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fish" name="fish.id" from="${fishandfriends.Fish.list()}" optionKey="id" required="" value="${catchInstance?.fish?.id}" class="many-to-one"/>

</div>

