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
                    <g:form url="[action: 'share', controller: 'catch']">
                        <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
                    </g:form>
                    <br/>
                    <br/>
                    <g:form url="[action: 'addNewArea', controller: 'fishingArea']">
                        <button type="submit" class="btn btn-success"></button>
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
            <div class="row">
                <div class="col-md-12">
                    <g:each var="singleCatch" in="${catches}">
                        <g:render template="showNewsFeedsCatch"
                                  model="[singleCatch: singleCatch.aCatch, nbComments: singleCatch.nbComments]"></g:render>
                    </g:each>
                </div>
            </div>
        </div>
    </div>

</div>
</div>

</body>

</html>