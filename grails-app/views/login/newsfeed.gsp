<%@ page import="fishandfriends.Catch; fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fish & Friends</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid homebox">
    <div class="row">
        <div class="col-md-2 ">

            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Options</h4>
                </div>
                <br/>
                <br/>

                <div class="panel-body">
                        <g:form url="[action: 'shareCatch', controller: 'fishingMan', resource: fishingManInstance]">
                            <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
                        </g:form>
                    <br/>
                    <br/>
                    <g:form url="[action: 'addNewArea', controller: 'fishingArea', resource: fishingManInstance]">
                        <button type="submit" class="btn btn-success"><i
                                class="fa fa-plus"></i> Ajouter un lieu</button>
                    </g:form>
                    <br/>
                    <br/>

                </div>
            </div>

            %{--<g:if test="${fishingManInstance.id}.equals(${session.fishingMan.id})">--}%

        </div>

        <div class="col-md-4">
            <h1>Fil d'actualités</h1>
        </div>

        <div class="col-md-8">
            <g:each var="singleCatch" in="${fishandfriends.Catch.list()}">
                <div class="jumbotron">
                    <g:render template="showNewsFeedsCatch" model="[singleCatch: singleCatch]"></g:render>
                </div>
            </g:each>
        </div>
    </div>

</div>
</div>

</body>

</html>