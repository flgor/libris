<!doctype html>
<html lang="en">
<head>
<#include "components/analitics.ftl">
<#include "components/meta.ftl">
<#include "components/bootstrapcss.ftl">

    <style>
        body {
            padding-top: 5rem;
        }
    </style>
</head>

<body>

<#--https://getbootstrap.com/docs/4.0/examples/starter-template/-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="/">Libris</a>
</nav>

<main role="main" class="container">

    <div class="row">
        <form class="col-md-6 offset-md-3">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                       placeholder="Enter email">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.
                </small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="checkbox" class="form-check-input">
                    Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-primary">Sign In</button>
        </form>
    </div>

</main><!-- /.container -->


<#include "components/bootstrapjs.ftl">

</body>
</html>
