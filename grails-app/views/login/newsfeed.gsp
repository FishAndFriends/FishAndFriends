<!DOCTYPE html>
<html lang="en">
<head>
    <title>Fish & Friends</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-2 col-md-4">
            <h1>What's news?</h1>
        </div>
        <div class="col-md-offset-2 col-md-8">
            <div class="homebox jumbotron">
                <div class="col-md-12">
                    <label class="col-md-2 control-label">Partager une prise</label>
                    <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
                </div>
            </div>
            <div class="homebox jumbotron">
                <g:each var="singleCatch" in="${catches}">
                    <div class="row">
                        <div class="col-md-12">
                            <g:render template="showNewsFeedsCatch" model="[singleCatch: singleCatch]"></g:render>
                        </div>
                    </div>
                </g:each>
            </div>
        </div>
    </div>
</div>
</body>
</html>