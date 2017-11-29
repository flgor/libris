<!doctype html>
<html lang="en">
<head>
<#include "components/analitics.ftl">
<#include "components/meta.ftl">
<#include "components/bootstrapcss.ftl">

    <!-- Custom styles for this template -->
    <link href="/css/cover.css" rel="stylesheet">
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <header class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand">Libris</h3>
                    <nav class="nav nav-masthead">
                        <a class="nav-link active" href="#">Home</a>
                        <a class="nav-link" href="#">Contact</a>
                    </nav>
                </div>
            </header>

            <main role="main" class="inner cover">
                <h1 class="cover-heading">What is Libris?</h1>
                <p class="lead">Libris, an application to help you manage your books.</p>
                <p class="lead">
                    <a href="/login" class="btn btn-lg btn-secondary">Login</a>
                </p>
            </main>

            <footer class="mastfoot">
                <div class="inner">
                    <p>Powered by <a
                            href="https://twitter.com/flgor">@flgor</a>.</p>
                </div>
            </footer>

        </div>

    </div>

</div>

<#include "components/bootstrapjs.ftl">
<script src="/js/nananana.js"/>

</body>
</html>
