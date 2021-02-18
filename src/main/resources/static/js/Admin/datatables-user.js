$(document).ready(function() {
    $('#dataTableOrders').DataTable({
        drawCallback: function() {
            $("#dataTableOrders tbody tr").click(function () {
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
                a.href = "/Admin/User/deleteOrder/" + (tableData[0]);
            });
        }
    });
    $('#dataTableProducts').DataTable({
        drawCallback: function() {
            $("#dataTableProducts tbody tr").click(function () {
                var tableData = $(this).children("td").map(function() {
                    return $(this).text();
                }).get();
                $("#p_productId").val(tableData[1]);
                $("#p_userId").val(tableData[2]);
                $("#p_title").val(tableData[3]);
                $("#p_summary").val(tableData[4]);
                $("#p_type").val(tableData[5]);
                $("#p_price").val(tableData[6]);
                $("#p_discount").val(tableData[7]);
                $("#p_date").val(tableData[8]);
                $("#p_content").val(tableData[9]);
                $("#p_productUrl").val(tableData[10]);
                $('#p_ProductModal').modal('show');
                var a = document.getElementById('p_deleteBtn'); //or grab it by tagname etc
                a.href = "/Admin/User/deleteProduct/" + (tableData[1]);
            });
        }
    });
    $('#dataTableReview').DataTable({
        drawCallback: function() {
            $("#dataTableReview tbody tr").click(function () {
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
                a.href = "/Admin/User/deleteOrder/" + (tableData[0]);
            });
        }
    });
});