<%--
  Created by IntelliJ IDEA.
  User: florent
  Date: 15/10/15
  Time: 08:46
--%>
<%@ page import="fishandfriends.Catch; fishandfriends.FishingMan; fishandfriends.Fish; fishandfriends.FishingArea" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>${fishingManInstance.firstname} ${fishingManInstance.lastname}</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-offset-2 col-md-4">
                <h1>Partager votre prise ${fishingManInstance.firstname}
                    lalala ${fishingManInstance.lastname}</h1>
            </div>
            <div class="col-md-offset-2 col-md-8">
                <div class="homebox jumbotron">
                    <g:form url="[action: 'shareCatchLocation', controller: 'fishingMan', resource: fishingManInstance]" class="form-horizontal">
                        <h2><i class="fa fa-location-arrow"></i> Lieu</h2>
                        <div class="form-group">
                            <label  for="fishingAreaNameShared" class="control-label">Lieux</label>
                            <g:select class="form-control"
                                      name="fishingAreaNameShared"
                                      from="${FishingArea.list()}"
                                      value="${FishingArea?.name}"
                                      noSelection="${['null':'Lieu de pêche']}"
                                      optionKey="id"
                                      optionValue="name"
                            />
                        </div>
                        <h2><i class="flaticon-fish"></i> Poisson</h2>
                        <div class="form-group">
                            <label for="fishNameShared" class="control-label">Nom</label>
                            <g:select class="form-control"
                                      name="fishNameShared"
                                      from="${Fish.list()}"
                                      value="${Fish?.name}"
                                      noSelection="${['null':'Choisir un poisson']}"
                                      optionKey="id"
                                      optionValue="name"
                            />
                            <label for="fishWeightShared" class="col-md-1 control-label">Poids (kg)</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"
                                       id="fishWeightShared"
                                       name="fishWeightShared"
                                       placeholder="0.0">
                            </div>
                            <label for="fishSizeShared" class="col-md-2 control-label">Taille (cm)</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"
                                       id="fishSizeShared"
                                       name="fishSizeShared" placeholder="0.0">
                            </div>
                        </div>
                        <div class="col-md-offset-10">
                            <button type="submit" class="btn btn-success"><i class="fa fa-share"></i> Partager</button>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>