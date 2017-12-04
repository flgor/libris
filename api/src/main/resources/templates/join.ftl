<!doctype html>
<html lang="en">
<head>
<#include "components/analitics.ftl">
<#include "components/meta.ftl">
<#include "components/bootstrapcss.ftl">
    <title>Libris, sign up now.</title>
    <link href="/css/signinup.css" rel="stylesheet">
</head>

<body>

<main role="main" class="container">
    <div class="row">
        <div class="signInUpDescription col-12">
            Join Libris now and easy manage your books.
        </div>
    </div>

    <div class="row">
        <form class="col-md-6 offset-md-3" action="/join" method="post">
            <div class="form-group">
                <label for="fullName">Full name</label>
                <input type="text" class="form-control" id="fullName"
                       placeholder="Full name" name="username">
            </div>
            <div class="form-group">
                <label for="inputEmail">Email address</label>
                <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                       placeholder="Enter email" name="username">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.
                </small>
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                       name="password">
            </div>
            <button type="submit" class="btn btn-primary" disabled="disabled">Sign Up</button>
        </form>
    </div>

</main>
<#include "components/bootstrapjs.ftl">

</body>
</html>
