function ajaxFunction(uri, method, data) {

    return $.ajax({

        type: method,
        url: uri,
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: data ? JSON.stringify(data) : null

    }).fail(function (jqXHR, textStatus, errorThrown) {


        swal(jqXHR.responseText, "Error", "error");


    });
}