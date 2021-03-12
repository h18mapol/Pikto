// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var orderList = [0,0,0,0,0,0,0,0,0,0,0,0];
var labelListBar = [];
var labelListLine = [];
let months = ["Jan","Feb","Mar","Apr","Maj", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
let jan =

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
            labelListBar.push(data[i].createdAt);
            let last2 = data[i].createdAt.substring(5,7);
            switch (last2) {
                case "01":
                    let jan = orderList[0];
                    jan += parseInt(data[i].grandTotal);
                    orderList[0] = jan;
                    break;
                case "02":
                    let feb = orderList[1];
                    feb += parseInt(data[i].grandTotal);
                    orderList[1] = feb;
                    break;
                case "03":
                    let mar = orderList[2];
                    mar = mar + parseInt(data[i].grandTotal);
                    console.log(last2 + mar);
                    orderList[2] = mar;
                    break;
                case "04":
                    let apr = orderList[3];
                    apr = apr + parseInt(data[i].grandTotal);
                    orderList[3] = apr;
                case "05":
                    let maj = orderList[4];
                    maj += parseInt(data[i].grandTotal);
                    orderList[4] = maj;
                case "06":
                    let jun = orderList[5];
                    jun += parseInt(data[i].grandTotal);
                case "07":
                    let jul = orderList[6];
                    jul += parseInt(data[i].grandTotal);
                case "08":
                    let aug = orderList[7];
                    aug += parseInt(data[i].grandTotal);
                case "09":
                    sep = orderList[8];
                    sep += parseInt(data[i].grandTotal);
                case "10":
                    oct = orderList[9];
                    oct += parseInt(data[i].grandTotal);
                case "11":
                    nov = orderList[10];
                    nov += parseInt(data[i].grandTotal);
                case "12":
                    dec = orderList[11];
                    dec += parseInt(data[i].grandTotal);
            }
            console.log(parseInt(data[i].grandTotal));
            labelListLine.push(last2);
        }
        BuildLineChart(orderList, months, "Order");
        BuildChart(orderList, labelListBar, "Order size");
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
