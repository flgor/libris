<form class="col-6 offset-3" action="#" style="padding-top: 1em">
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
        <label for="inputPublishedDate">Published Date</label>
        <input type="text" class="form-control" id="inputPublishedDate"
               placeholder="YYYY-MM-DD" name="publishedDate">
    </div>
    <div class="form-group">
        <label for="inputIsbn13">ISBN 13</label>
        <input type="text" class="form-control" id="inputIsbn13"
               placeholder="ISBN 13" name="isbn13">
    </div>
    <button type="submit" class="btn btn-primary" id="addBookButton">Add Book.</button>
</form>

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
</script>