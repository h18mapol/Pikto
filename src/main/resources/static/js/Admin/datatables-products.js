// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#dataTable').DataTable({
        drawCallback: function() {
            $("#dataTable tbody tr").click(function () {
                var tableData = $(this).children("td").map(function() {
                    return $(this).text();
                }).get();
                $("#productId").val(tableData[1]);
                $("#userId").val(tableData[2]);
                $("#title").val(tableData[3]);
                $("#summary").val(tableData[4]);
                $("#type").val(tableData[5]);
                $("#price").val(tableData[6]);
                $("#discount").val(tableData[7]);
                $("#date").val(tableData[8]);
                $("#content").val(tableData[9]);
                $("#productUrl").val(tableData[10]);
                $('#productModal').modal('show');
                var a = document.getElementById('deletebtn'); //or grab it by tagname etc
                a.href = "/Admin/deleteProduct/" + (tableData[1]);
            });
        }
    });
});
