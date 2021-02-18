// Call the dataTables jQuery plugin
$(document).ready(function() {
    $('#dataTable').DataTable({
        drawCallback: function() {
            $("#dataTable tbody tr").click(function () {
                var tableData = $(this).children("td").map(function() {
                    return $(this).text();
                }).get();
                $("#orderId").val(tableData[0]);
                $("#userId").val(tableData[1]);
                $("#sessionId").val(tableData[2]);
                $("#status").val(tableData[3]);
                $("#subTotal").val(tableData[4]);
                $("#itemDiscount").val(tableData[5]);
                $("#tax").val(tableData[6]);
                $("#shipping").val(tableData[7]);
                $("#total").val(tableData[8]);
                $("#promo").val(tableData[9]);
                $("#discount").val(tableData[10]);
                $("#grandTotal").val(tableData[11]);
                $("#firstName").val(tableData[12]);
                $("#lastName").val(tableData[13]);
                $("#mobile").val(tableData[14]);
                $("#email").val(tableData[15]);
                $("#address").val(tableData[16]);
                $("#city").val(tableData[17]);
                $("#createdAt").val(tableData[18]);
                $("#content").val(tableData[19]);
                $('#orderModal').modal('show');
                var a = document.getElementById('deletebtn'); //or grab it by tagname etc
                a.href = "/Admin/deleteOrder/" + (tableData[0]);
            });
        }
    });
});