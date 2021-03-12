// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var labelListLine2 = ["1 Star", "2 Star", "3 Star", "4 Star", "5 Star"];

var dataReveiw = $('#dataTableReview').find("tbody tr");
let rating_1 = 0;
let rating_2 = 0;
let rating_3 = 0;
let rating_4 = 0;
let rating_5 = 0;
dataReveiw.each(function (index, value) {
    data = $(this).children("td");
    let ratings = (data[3].innerHTML);
    console.log(ratings);
    switch (ratings) {
        case "1":
            rating_1++;
            break;
        case "2":
            rating_2++;
            break;
        case "3":
            rating_3++;
            break;
        case "4":
            rating_4++;
            break;
        case "5":
            rating_5++;
            break;
        default:
            console.log("Data not available");
            break;
    }
});

orderList2 = [rating_1, rating_2, rating_3, rating_4, rating_5];
console.log(orderList2);
BuildChart(orderList2, labelListLine2, "Ratings");


function BuildChart(orderList, labelList, chartTitle) {
    //max = Math.max(...orderList) + 5;
    //console.log(max);
    // Pie Chart Example
    var ctx = document.getElementById("myPieChartReview");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labelList,
            datasets: [{
                data: orderList,
                backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', '#A2A3A8'],
            }],
        },
    });
    return myPieChart;
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
