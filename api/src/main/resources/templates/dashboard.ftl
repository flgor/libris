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
                <th scope="col" class="col-md-4">Authors</th>
                <th scope="col" class="col-md-1">Published Date</th>
                <th scope="col" class="col-md-1">Actions</th>
            </tr>
            </thead>
            <tbody>
                <#list books as book>
                <tr class="row">
                    <td class="col-md-2">${book.uniqueId}</td>
                    <td class="col-md-4">${book.title}</td>
                    <td class="col-md-4">${(book.authors)!"Not defined."}</td>
                    <td class="col-md-1">${(book.publishedDate)!"Not defined."}</td>
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
                <label for="inputAuthors">Authors</label>
                <input type="text" class="form-control" id="inputAuthors"
                       placeholder="Authors" name="authors">
            </div>
            <div class="form-group">
                <label for="inputDescription">Description</label>
                <input type="text" class="form-control" id="inputDescription"
                       placeholder="Description" name="description">
            </div>
            <div class="form-group">
                <label for="inputPublisher">Publisher</label>
                <input type="text" class="form-control" id="inputPublisher"
                       placeholder="Publisher" name="publisher">
            </div>
            <div class="form-group">
                <label for="inputPublishedDate">PublishedDate</label>
                <input type="text" class="form-control" id="inputPublishedDate"
                       placeholder="PublishedDate" name="publishedDate">
            </div>
            <div class="form-group">
                <label for="inputIsbn13">ISBN 13</label>
                <input type="text" class="form-control" id="inputIsbn13"
                       placeholder="ISBN 13" name="isbn13">
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
        var inputAuthorsValue = $('#inputAuthors').val();
        var inputDescriptionValue = $('#inputDescription').val();
        var inputPublisherValue = $('#inputPublisher').val();
        var inputPublishedDateValue = $('#inputPublishedDate').val();
        var inputIsbn13Value = $('#inputIsbn13').val();

        var createBookRequest = {};
        createBookRequest.title = inputTitleValue;
        createBookRequest.authors = inputAuthorsValue;
        createBookRequest.description = inputDescriptionValue;
        createBookRequest.publisher = inputPublisherValue;
        createBookRequest.publishedDate = inputPublishedDateValue;
        createBookRequest.isbn13 = inputIsbn13Value;

        console.log('Request: ', createBookRequest);
        return createBookRequest
    };

    $('#addBookButton').click(createBook);
</script

</body>
</html>
