<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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
        <form class="col-md-6 offset-md-3" action="#">
            <div class="form-group">
                <label for="inputFullName">Full name</label>
                <input type="text" class="form-control" id="inputFullName"
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
            <button type="submit" class="btn btn-primary" id="signUpButton">Sign Up</button>
        </form>
    </div>

</main>
<#include "components/bootstrapjs.ftl">

<script type="text/javascript">
    var createAccount = function () {
        $.ajax({
            url: "/api/v1/user",
            type: "POST",
            data: JSON.stringify(getRequest()),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        })
                .done(function () {
                    console.log("success");
                    location.href = '/dashboard';
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    console.log("Error:", jqXHR);
                });
    };

    var getRequest = function () {
        var inputEmailValue = $('#inputEmail').val();
        var inputFullNameValue = $('#inputFullName').val();
        var inputPasswordValue = $('#inputPassword').val();

        var createUserRequest = {};
        createUserRequest.email = inputEmailValue;
        createUserRequest.fullName = inputFullNameValue;
        createUserRequest.password = inputPasswordValue;

        console.log('Request: ', createUserRequest);
        return createUserRequest;
    };

    $('#signUpButton').click(createAccount);
</script


</body>
</html>
