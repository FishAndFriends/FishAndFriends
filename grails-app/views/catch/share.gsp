<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'catch.label', default: 'Catch')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-2 col-md-4">
            <h1>Partagez votre prise</h1>
        </div>

        <div class="col-md-offset-2 col-md-8">
            <div class="homebox jumbotron">
                <g:form url="[action: 'save', controller: 'catch']">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fishingAreaNameShared" class="control-label">
                                    Lieu</label>
                                <g:select class="form-control"
                                          id="fishingArea"
                                          name="fishingArea.id"
                                          value="${catchInstance?.fishingArea?.id ?: fishingArea?.id}"
                                          from="${fishingAreaList}"
                                          noSelection="${['null': 'Lieu de pêche']}"
                                          optionKey="id"
                                          optionValue="name"/>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="fishNameShared" class="control-label">
                                    Poisson</label>
                                <g:select class="form-control"
                                          id="fish"
                                          name="fish.id"
                                          value="${catchInstance?.fish?.id}"
                                          from="${fishList}"
                                          noSelection="${['null': 'Choisir un poisson']}"
                                          optionKey="id"
                                          optionValue="name"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 col-sm-6">
                            <div class="form-group">
                                <label for="weight" class="control-label">Poids (kg)</label>
                                <g:field type="text"
                                         id="weight"
                                         name="weight"
                                         class="form-control"
                                         value="${fieldValue(bean: catchInstance, field: 'weight')}"
                                         required=""
                                         placeholder="0.0"/>

                            </div>
                        </div>

                        <div class="col-md-offset-4 col-md-4 col-sm-6">
                            <div class="form-group">
                                <label for="size" class="control-label">Taille (cm)</label>
                                <g:field type="text"
                                         id="size"
                                         name="size"
                                         class="form-control"
                                         value="${fieldValue(bean: catchInstance, field: 'size')}"
                                         required=""
                                         placeholder="0.0"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-offset-10 col-md-2">
                            <button type="submit" class="btn btn-success btn-block"><i class="fa fa-share"></i> Partager
                            </button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <g:hasErrors bean="${catchInstance}">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <g:eachError bean="${catchInstance}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </div>
            </div>
        </div>
    </g:hasErrors>
</div>
</body>
</html>
