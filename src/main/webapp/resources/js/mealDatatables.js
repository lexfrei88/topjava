/**
 * Created by Alex on 5/22/2017.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateWithFilter();
            successNoty('Deleted');
        }
    });
}

function updateWithFilter() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            datatableApi.clear();
            $.each(data, function (key, item) {
                datatableApi.row.add(item);
            });
            datatableApi.draw();
        }
    });
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateWithFilter();
            successNoty('Saved');
        }
    });
}

function resetFilter() {
    $('#filter')[0].reset();
    updateWithFilter();
}