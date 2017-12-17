<!doctype html>
<html lang="en">
<head>
<#include "components/analitics.ftl">
<#include "components/meta.ftl">
<#include "components/bootstrapcss.ftl">
    <title>Libris, welcome.</title>
    <link href="/css/dashboard.css" rel="stylesheet">
</head>

<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="#">Libris</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">${user.fullName}</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="/logout">Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<main role="main" class="container">

    <div class="starter-template">
        <h1>Welcome to libris.</h1>
        <p class="lead">Manage your library.</p>
    </div>

    <div class="row">
        <table class="table table-hover">
            <thead>
            <tr class="row">
                <th scope="col" class="col-md-2">#</th>
                <th scope="col" class="col-md-4">Title</th>
                <th scope="col" class="col-md-4">Author</th>
                <th scope="col" class="col-md-1">Year</th>
                <th scope="col" class="col-md-1">Actions</th>
            </tr>
            </thead>
            <tbody>
                <#list books as book>
                <tr class="row">
                    <td class="col-md-2">${book.uniqueId}</td>
                    <td class="col-md-4">${book.title}</td>
                    <td class="col-md-4">${book.author}</td>
                    <td class="col-md-1">${book.year?c}</td>
                    <td class="col-md-1">(soon)</td>
                </tr>
                </#list >
            </tbody>
        </table>
    </div>

    <div class="row">
        <form class="col-md-6 books-table" action="#">
            <div class="form-group">
                <label for="inputTitle">Title</label>
                <input type="text" class="form-control" id="inputTitle"
                       placeholder="Title" name="title">
            </div>
            <div class="form-group">
                <label for="inputAuthor">Author</label>
                <input type="text" class="form-control" id="inputAuthor"
                       placeholder="Author" name="author">
            </div>
            <div class="form-group">
                <label for="inputYear">Year</label>
                <input type="text" class="form-control" id="inputYear" placeholder="Year"
                       name="year">
            </div>
            <button type="submit" class="btn btn-primary" id="addBookButton">Add Book.</button>
        </form>
    </div>

</main><#include "components/bootstrapjs.ftl">

<script type="text/javascript">
    var createBook = function (event) {
        event.preventDefault();
        $('#addBookButton').attr('disabled', 'disabled');

        $.ajax({
            url: "/api/v1/book",
            type: "POST",
            data: JSON.stringify(getRequest()),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: function (response) {
                console.log("success", response);
                location.href = '/dashboard';
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error", xhr);
                $('#addBookButton').removeAttr('disabled');
            }
        });
    };

    var getRequest = function () {
        var inputTitleValue = $('#inputTitle').val();
        var inputAuthorValue = $('#inputAuthor').val();
        var inputYearValue = $('#inputYear').val();

        var createBookRequest = {};
        createBookRequest.title = inputTitleValue;
        createBookRequest.author = inputAuthorValue;
        createBookRequest.year = inputYearValue;

        console.log('Request: ', createBookRequest);
        return createBookRequest
    };

    $('#addBookButton').click(createBook);
</script

</body>
</html>
