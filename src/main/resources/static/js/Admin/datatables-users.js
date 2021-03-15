// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#dataTable').DataTable({
        drawCallback: function() {
            $("#dataTable tbody tr").click(function () {
                var tableData = $(this).children("td").map(function() {
                    return $(this).text();
                }).get();
                window.location.href = '/Admin/User/' + tableData[0];
            });
        }
    });
});