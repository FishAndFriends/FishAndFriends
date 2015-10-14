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
                <label class="col-md-2 control-label">Partager une prise</label>
                <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
            </div>
            <div class="homebox jumbotron">
                <ul>
                    <g:each var="fishingMan" in="${}">
                        <li>${fishingArea.name}, ${fishingArea.location}</li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>