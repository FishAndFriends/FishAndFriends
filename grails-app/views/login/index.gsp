<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-offset-2 col-md-4">
            <div class="homebox jumbotron">
                <h2>Connexion</h2>
                <g:form url="[action: 'connexion', controller: 'login']">
                    <div class="form-group">
                        <input type="email" class="form-control" name="connectMail" value="${params.connectMail}" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="connectPwd" value="${params.connectPwd}" placeholder="Mot de passe">
                    </div>
                    <button type="submit" id="sublogin" class="btn btn-primary"><i class="fa fa-user"></i> Se connecter</button>
                </g:form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="homebox jumbotron">
                <h2>Inscription</h2>
                <g:form url="[action: 'inscription', controller: 'login']">
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="signupFirstname" value="${params.signupFirstname}" placeholder="Prénom">
                        </div>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="signupLastname" value="${params.signupLastname}" placeholder="Nom">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="signupMail" value="${params.signupMail}" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="signupPwd" value="${params.signupPwd}" placeholder="Mot de passe">
                    </div>
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="radioGender" id="male" value="H"/>Homme
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radioGender" id="female" value="F"/>Femme
                        </label>
                    </div>
                    <recaptcha:ifEnabled>
                        <recaptcha:recaptcha/>
                    </recaptcha:ifEnabled>
                    <br/>
                    <button type="submit" id="subsignin" class="btn btn-success"><i class="fa fa-user-plus"></i> S'inscrire</button>
                </g:form>
            </div>
        </div>
    </div>
    <g:hasErrors bean="${fishingManInstance}">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <g:eachError bean="${fishingManInstance}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </div>
            </div>
        </div>
    </g:hasErrors>
    <g:if test="${errors}">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <g:each in="${errors}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </g:if>
</div>
</body>
</html>