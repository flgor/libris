<form class="col-6 offset-3" action="#" style="padding-top: 1em">
    <div class="form-group">
        <label for="searchInputTitle">Title</label>
        <input type="text" class="form-control" id="searchInputTitle"
               placeholder="Title" name="title">
    </div>
    <div class="form-group">
        <label for="searchInputAuthors">Authors</label>
        <input type="text" class="form-control" id="searchInputAuthors"
               placeholder="Authors" name="authors">
    </div>
    <div class="form-group">
        <label for="searchInputIsbn">ISBN</label>
        <input type="text" class="form-control" id="searchInputIsbn"
               placeholder="ISBN" name="isbn">
    </div>
    <button type="submit" class="btn btn-primary" id="searchBookButton">Search</button>
</form>

<div id="searchResult" class="col-12">
</div>

<script type="text/javascript">
    var searchBooks = function (event) {
        event.preventDefault();
        $('#searchBookButton').attr('disabled', 'disabled');

        var googleSearchResultTemplate = $('#googleSearchResultTemplate').html();
        Mustache.parse(googleSearchResultTemplate);

        $.ajax({
            url: "/api/v1/book/google/search/public",
            type: "get",
            data: getSearchRequestData(),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                console.log("success", response);
                $('#searchBookButton').removeAttr('disabled');

                var renderedResult = "No books were found.";
                if (response.totalItems > 0) {
                    renderedResult = Mustache.render(googleSearchResultTemplate, response);
                }

                $('#searchResult').html(renderedResult);
                $('.bookadd').on("click", bookAdd);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error", xhr);
                $('#searchBookButton').removeAttr('disabled');

                $('#searchResult').html("There is a problem with the search. Please contact us if the problem persists.");
            }
        });
    };

    var bookAdd = function (event) {
        event.preventDefault();
        $(this).attr('disabled', 'disabled');
        var googleIdValue = $(this).attr("googleId");
        $.ajax({
            url: "/api/v1/book/google/" + googleIdValue,
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                console.log("success", response);
                location.href = '/dashboard';
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error", xhr);
                $(this).removeAttr('disabled');
            }
        });
    };

    var getSearchRequestData = function () {
        var searchInputTitleValue = $('#searchInputTitle').val();
        var searchInputAuthorsValue = $('#searchInputAuthors').val();
        var searchInputIsbnValue = $('#searchInputIsbn').val();

        return {
            intitle: searchInputTitleValue,
            inauthor: searchInputAuthorsValue,
            isbn: searchInputIsbnValue
        }
    };

    $('#searchBookButton').click(searchBooks);
</script>

<script id="googleSearchResultTemplate" type="x-tmpl-mustache">
        <h2>Search Results.</h2>

        <table class="table table-hover">
            <tbody>
                {{#items}}
                    <tr class="row">
                        <td class="col-1"><img src="{{thumbnail}}" class="search-img"></td>
                        <td class="col-5">{{title}}</td>
                        <td class="col-5">{{authors}}</td>
                        <td class="col-1"><button class="btn btn-primary bookadd" googleId={{googleId}}>Add.</button></td>
                    </tr>
                {{/items}}
            </tbody>
        </table>


</script>