<!DOCTYPE html>
<head>
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Fish & Friends"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">-->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.css')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'flaticon.css')}">

    %{--TODO: Penser à retirer ce debug à la fin--}%
    <style type="text/css">
    /*.debug {
        diplay: none;
    }*/
    </style>
    <r:require modules="bootstrap"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources/>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Fish & Friends</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li ${controllerName == null ? 'class="active"' : ''}><a href="/"><i class="fa fa-home"></i> Accueil</a></li>
                <g:if test="${session.fishingMan != null}">
                    <li><a href="${createLink(controller:'fishingMan', action:'show', id:"${session.fishingMan.id}") }"><i class="fa ${session.fishingMan.gender.equals("H") ? 'fa-male':'fa-female'}"></i> ${session.fishingMan.firstname} ${session.fishingMan.lastname}</a></li>
                    <li><a href="#" id="signout"><i class="fa fa-sign-out"></i> Déconnexion</a></li>
                </g:if>
            </ul>
            <g:if test="${session.fishingMan != null}">
                <g:form class="navbar-form navbar-right" url="[action: 'index', controller: 'searchResult']">
                    <select id="want" name="want" class="form-control">
                        <option value="fishingArea">Lieu</option>
                        <option value="fishingMan">Pêcheur</option>
                        <option value="fish">Poisson</option>
                    </select>
                    <div class="input-group">
                        <input type="text" id="query" name="query" class="form-control" placeholder="Rechercher...">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                        </span>
                    </div><!-- /input-group -->
                </g:form>
            </g:if>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<g:layoutBody/>
<g:if test="${session.fishingMan != null}">
    <div class="modal fade" tabindex="-1" role="dialog" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="mySmallModalLabel">Déconnexion</h4>
                </div>
                <div class="modal-body">
                    Êtes-vous sûr de vouloir vous déconnecter ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                    <a href="${createLink(controller:'login', action:'deconnexion')}" class="btn btn-danger" role="button">Se déconnecter</a>
                </div>
            </div>
        </div>
    </div>
</g:if>
<r:layoutResources/>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-select.min.css')}">
<g:javascript src="bootstrap-select.min.js" />
<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>
