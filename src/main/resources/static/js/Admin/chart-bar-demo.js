// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var orderList = [];
var labelListBar = [];
var labelListLine = [];
let months = ["01","02","03","04","05"];
// Bar Chart Example
//$("#btn-search").prop("disabled", true);

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
            labelListBar.push(data[i].createdAt);
            let last2 = data[i].createdAt.substring(8,10);
            labelListLine.push(last2);
        }
        console.log(orderList + labelListBar + labelListLine);
        BuildChart(orderList, labelListBar, "Order size");
        BuildLineChart(orderList, labelListLine, "Order");
    },
    error: function (e) {

        var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
            + e.responseText + "&lt;/pre&gt;";
        //$('#feedback').html(json);

        console.log("ERROR : ", e);
        //$("#btn-search").prop("disabled", false);

    }
});

function BuildChart(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) + 5;
    console.log(max);
    var ctx = document.getElementById("myBarChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labelList, // Our labels
            datasets: [{
                label: chartTitle, // Name the series
                data: orderList, // Our values
                borderWidth: 1, // Specify bar border width
                backgroundColor: "rgba(2,117,216,0.2)",
                borderColor: "rgba(2,117,216,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(2,117,216,1)",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,117,216,1)"
            }]
        },
        options: {
            responsive: true, // Instruct chart js to respond nicely.
            maintainAspectRatio: true, // Add to prevent default behavior of full-width/height
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: max,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
    return myChart;
}

function BuildLineChart(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) + 5;
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labelList,
            datasets: [{
                label: chartTitle,
                lineTension: 0.3,
                backgroundColor: "rgba(2,117,216,0.2)",
                borderColor: "rgba(2,117,216,1)",
                pointRadius: 5,
                pointBackgroundColor: "rgba(2,117,216,1)",
                pointBorderColor: "rgba(255,255,255,0.8)",
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "rgba(2,117,216,1)",
                pointHitRadius: 50,
                pointBorderWidth: 2,
                data: orderList,
            }],
        },
        options: {
            scales: {
                xAxes: [{
                    time: {
                        unit: 'date'
                    },
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        maxTicksLimit: 7
                    }
                }],
                yAxes: [{
                    ticks: {
                        min: 0,
                        max: max,
                        maxTicksLimit: 5
                    },
                    gridLines: {
                        color: "rgba(0, 0, 0, .125)",
                    }
                }],
            },
            legend: {
                display: false
            }
        }
    });
    return myLineChart;
}

// var ctx = document.getElementById("myBarChart");
// var myLineChart = new Chart(ctx, {
//   type: 'bar',
//   data: {
//     labels: ["1","2","3"],
//     datasets: [{
//       label: "Revenue",
//       backgroundColor: "rgba(2,117,216,1)",
//       borderColor: "rgba(2,117,216,1)",
//       data: [orderList[0],10,20],
//     }]
//   },
//   options: {
//     scales: {
//       xAxes: [{
//         time: {
//           unit: 'session'
//         },
//         gridLines: {
//           display: false
//         },
//           ticks: {
//               maxTicksLimit: 5
//           }
//       }],
//         yAxes: [{
//             ticks: {
//                 min: 0,
//                 max: 100,
//                 maxTicksLimit: 6
//             },
//             gridLines: {
//                 color: "rgba(0, 0, 0, .125)",
//             }
//         }],
//     },
//       legend: {
//           display: false
//       }
//   }
// });
