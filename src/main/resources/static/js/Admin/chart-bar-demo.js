// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var orderList = [];
var labelList = [];
// Bar Chart Example
function fire_ajax_submit() {



    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/Data/Orders",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            for (i in data) {
                orderList.push(parseInt(data[i].grandTotal));
            }

            handleData();

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            //$('#feedback').html(json);

            console.log("ERROR : ", e);
            //$("#btn-search").prop("disabled", false);

        }
    });
}
function handleData() {
    console.log(orderList);
}

fire_ajax_submit();
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["January"],
    datasets: [{
      label: "Revenue",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: orderList
    }]
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'session'
        },
        gridLines: {
          display: false
        },
        ticks: {
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 50,
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
