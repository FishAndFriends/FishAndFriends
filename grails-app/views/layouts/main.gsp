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

    <r:require modules="bootstrap"/>
    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources/>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-select.min.css')}">
    <g:javascript src="bootstrap-select.min.js" />
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
                <g:if test="${session.fishingMan != null}">
                    <li><a href="${createLink(controller:'fishingMan', action:'show', id:"${session.fishingMan.id}") }"><i class="fa ${session.fishingMan.gender.equals("H") ? 'fa-male':'fa-female'}"></i> ${session.fishingMan.firstname} ${session.fishingMan.lastname}</a></li>
                    <li><g:link action="addNewArea" controller="fishingArea">
                        <i class="fa fa-plus"></i> Ajouter un lieu</g:link></li>
                    <li><a href="#" id="signout"><i class="fa fa-sign-out"></i> Déconnexion</a></li>
                    %{--<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-location-arrow"></i> Lieux <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><g:link action="addNewArea" controller="fishingArea">
                                <i class="fa fa-plus"></i> Ajouter un lieu</g:link></li>
                        </ul>
                    </li>--}%
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
                            <button class="btn btn-primary" id="searchbtn" type="submit"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
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
                    <a id="logoutbtn" href="${createLink(controller:'login', action:'deconnexion')}" class="btn btn-danger" role="button">Se déconnecter</a>
                </div>
            </div>
        </div>
    </div>
    <div id="commentModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Liste des commentaires</h4>
                </div>

                <div class="modal-body custom_modal-body">
                    <div id="modal-comment-body">
                        <!-- Contains all comments provided by AJAX -->
                    </div>
                    <g:render template="../comment/createComment"
                              model="[commentable: fishingAreaInstance, fishingMan: session.fishingMan]"/>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</g:if>
<r:layoutResources/>
<script>
    $('.selectpicker').selectpicker();
    function showModal(event, ident) {
        event.preventDefault();

        $('#commentModal').modal('show');

        // Change the value of the commentable.id
        $("[name = 'commentable.id']").val(ident);

        // Retrieve all comments for the commentable
        $.ajax({
            type: 'POST',
            url: '${createLink(action: "showAllComment", controller: "comment")}',
            data: {'commentable': ident},
            success: function (data) {
                $("#modal-comment-body").html(data);
            }
        });
    }

    $(".showComment").on("click", function (event) {
        showModal(event, $(this).data('ident'));
    });

    function applyFormCommentBehavior() {
        $(".formComment").on('submit', function (ev) {
            ev.preventDefault();

            $.ajax({
                type: 'POST',
                url: '${createLink(action: "createComment", controller: "comment")}',
                data: $(this).serialize(),
                success: function (data) {
                    location.reload();
                }
            });
            return false;
        });
    };

    $(function() {
        applyFormCommentBehavior();
    });
</script>
</body>
</html>
