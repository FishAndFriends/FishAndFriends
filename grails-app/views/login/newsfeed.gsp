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
            <div class="row">
                <div class="col-md-12">
                    <h1>Fil d'actualités</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Vous avez eu une prise ? <g:link action="share" controller="catch" class="btn btn-success">
                            <i class="fa fa-share"></i> Partager
                        </g:link></h4>
                        </div>
                    </div>
                </div>
            </div>

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

</body>

</html>