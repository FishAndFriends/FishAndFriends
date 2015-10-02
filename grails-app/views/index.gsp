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
            <div class="homebox jumbotron">
                <h2>Connexion</h2>
                <form>
                    <div class="form-group">
                        <input type="email" class="form-control" id="connectMail" value="${params.connectMail}" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="connectPwd" value="${params.connectPwd}" placeholder="Mot de passe">
                    </div>
                    <button type="submit" class="btn btn-primary">Se connecter</button>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="homebox jumbotron">
                <h2>Inscription</h2>
                <form>
                    <div class="form-group row">
                        <div class="col-sm-6">
                            <input type="email" class="form-control" id="signupFirstname" value="${params.signupFirstname}" placeholder="Prénom">
                        </div>
                        <div class="col-sm-6">
                            <input type="email" class="form-control" id="signupLastname" value="${params.signupLastname}" placeholder="Nom">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="signupMail" value="${params.signupMail}" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="signupPwd" value="${params.signupPwd}" placeholder="Mot de passe">
                    </div>
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="radioGender" id="male" value="H"/>Homme
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="radioGender" id="female" value="F"/>Femme
                        </label>
                    </div>
                    <button type="submit" class="btn btn-success">S'inscrire</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>