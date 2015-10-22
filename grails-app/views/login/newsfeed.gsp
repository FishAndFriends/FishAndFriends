<%@ page import="fishandfriends.Catch; fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fish & Friends</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <h1>Fil d'actualités</h1>
            <g:form url="[action: 'shareCatch', controller: 'fishingMan', resource: fishingManInstance]">
                <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
            </g:form>
        </div>
        <div class="col-md-offset-2 col-md-8">
            <g:each var="singleCatch" in="${fishandfriends.Catch.list()}">
                <div class="jumbotron">
                    <g:render template="showNewsFeedsCatch" model="[singleCatch: singleCatch]"></g:render>
                </div>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>