<!doctype html>
<html lang="en">
<head>
<#include "components/analitics.ftl">
<#include "components/meta.ftl">
<#include "components/bootstrapcss.ftl">
    <title>Libris, welcome.</title>
    <link href="/css/bookadd.css" rel="stylesheet">
</head>

<body>
<#include "components/navigationBar.ftl">
<#include "components/bootstrapjs.ftl">

<main role="main" class="container">

    <div class="starter-template">
        <h1>Add books to your books collection.</h1>
        <p>You can modify, search and manage your books.</p>
    </div>

    <div class="row">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs col-4" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#home" role="tab">Search</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#profile" role="tab">Manual</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content col-12">
            <div class="tab-pane active" id="home" role="tabpanel">
                <#include "components/googleSearchAddBook.ftl">
            </div>
            <div class="tab-pane" id="profile" role="tabpanel">
                <#include "components/manualAddBook.ftl">
            </div>
        </div>
    </div>
</main>

</body>
</html>
