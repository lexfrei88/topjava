var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
        success: updateTableByData
    });
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        ajax: {
            "url": ajaxUrl,
            "dataSrc": ''
        },
        paging: false,
        info: true,
        createdRow: function ( row, data, index ) {
            var clazz = data.exceed ? 'exceeded' : 'normal';
            $('td', row).addClass(clazz);
        },
        columns: [
            {
                "data": "dateTime",
                render: function(data, type, row) {
                    return data.replace("T", " ").substring(0, 16);
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        order: [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});

$(function () {
    jQuery.datetimepicker.setLocale("${pageContext.request.locale.language}");

    $('#startDate, #endDate').datetimepicker({
        timepicker:false,
        format:'Y-m-d'
    });
    $('#startTime, #endTime').datetimepicker({
        datepicker:false,
        format:'H:i'
    });
});
