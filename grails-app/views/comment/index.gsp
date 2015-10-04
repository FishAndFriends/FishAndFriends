<%@ page import="fishandfriends.Comment" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Détail des commentaires</title>
</head>

<body>

<h1>Liste des commentaires</h1>
<g:include controller="comment" action="showAllComment" params="[commentable: commentable]"/>
<g:render template="createComment" model="[commentable: commentable, fishingMan: fishingMan]"/>
</body>
</html>
