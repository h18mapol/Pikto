// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#dataTable').DataTable({
        drawCallback: function() {
            $("#dataTable tbody tr").click(function () {
                var tableData = $(this).children("td").map(function() {
                    return $(this).text();
                }).get();
                $("#reviewId").val(tableData[0]);
                $("#productId").val(tableData[1]);
                $("#title").val(tableData[2]);
                $("#rating").val(tableData[3]);
                $("#createdAt").val(tableData[4]);
                $("#content").val(tableData[5]);
                $("#userId").val(tableData[6]);
                $('#reviewModal').modal('show');
                var a = document.getElementById('deletebtn'); //or grab it by tagname etc
                a.href = "/Admin/deleteReview/" + (tableData[0]);
            });
        }
    });
});