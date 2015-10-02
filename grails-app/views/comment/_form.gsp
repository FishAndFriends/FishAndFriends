<%@ page import="fishandfriends.Comment" %>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'text', 'error')} required">
    <g:textArea style="width: 100%" placeholder="Enter un commentaire" name="text" cols="40" rows="5" maxlength="5000" required="" value="${commentInstance?.text}"/>
</div>

