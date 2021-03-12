// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var orderList = [];

var ordercount = [0,0,0,0,0,0,0,0,0,0,0,0];
var labelListBar = [];
var labelListLine = [];
var productList = [];
var discountList = [];
ratings = ["1 Star", "2 Star", "3 Star", "4 Star", "5 Star"];
let months = ["Jan", "Feb", "Mar", "Apr", "Maj", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

$.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/Data/Reviews/Data",
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (data) {
        var userRevenue = [];
        var userRevenueLabel = [];
        let rating_1 = 0;
        let rating_2 = 0;
        let rating_3 = 0;
        let rating_4= 0;
        let rating_5= 0;
        for (i in data) {
            let last2 = data[i].rating;
            console.log(last2);
            switch (last2) {
                case 1:
                    rating_1 ++;
                    break;
                case 2:
                    rating_2 ++;
                    break;
                case 3:
                    rating_3 ++;
                    break;
                case 4:
                    rating_4 ++;
                    break;
                case 5:
                    rating_5 ++;
                    break;
                default:
                    console.log("Data not available");
                    break;
            }
        }
        productList = [rating_1, rating_2, rating_3, rating_4, rating_5];
        console.log(productList);
        BuildPieChartProducts(productList, ratings, "Products")
    },
    error: function (e) {

        var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
            + e.responseText + "&lt;/pre&gt;";
        //$('#feedback').html(json);

        console.log("ERROR : ", e);
        //$("#btn-search").prop("disabled", false);

    }
});

$.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/Data/Orders/Revenue",
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (data) {
        var userRevenue = [];
        var userRevenueLabel = [];
        for (i in data) {
            console.log(data[i].userId + data[i].grandTotal);
            userRevenue.push(data[i].grandTotal);
            userRevenueLabel.push("User:" +data[i].userId);
        }

        // orderList.push(parseInt(jan), parseInt(feb), mar, apr, maj, jun, jul, aug, sep, oct, nov, dec);
        BuildChartUserRevenue(userRevenue, userRevenueLabel, "SEK");
        // BuildChart(ordercount, months, "Count");
    },
    error: function (e) {

        var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
            + e.responseText + "&lt;/pre&gt;";
        //$('#feedback').html(json);

        console.log("ERROR : ", e);
        //$("#btn-search").prop("disabled", false);

    }
});

$.ajax({
    type: "GET",
    contentType: "application/json",
    url: "/Data/Orders",
    dataType: 'json',
    cache: false,
    timeout: 600000,
    success: function (data) {
        let jan = 0;
        let feb = 0;
        let mar = 0;
        let apr= 0;
        let maj= 0;
        let jun= 0;
        let jul= 0;
        let aug= 0;
        let sep= 0;
        let oct= 0;
        let nov= 0;
        let dec= 0;
        for (i in data) {
            let last2 = data[i].createdAt.substring(5, 7);

            switch (last2) {
                case "01":
                    jan += parseInt(data[i].grandTotal);
                    console.log("Jan " + jan);
                    ordercount[0] ++;
                    break;
                case "02":
                    feb += parseInt(data[i].grandTotal);
                    ordercount[1] ++;
                    break;
                case "03":
                    mar = mar + parseInt(data[i].grandTotal);
                    console.log("Mars " + mar);
                    ordercount[2] ++;
                    break;
                case "04":
                    apr = apr + parseInt(data[i].grandTotal);
                    console.log("apr " + apr);
                    ordercount[3] ++;
                    break;
                case "05":
                    maj = maj + parseInt(data[i].grandTotal);
                    console.log(last2 + " Maj " + maj);
                    ordercount[4] ++;
                    break;
                case "06":
                    jun = jun + parseInt(data[i].grandTotal);
                    console.log(last2 + " Jun " + jun);
                    ordercount[5] ++;
                    break;
                case "07":
                    jul = parseInt(data[i].grandTotal);
                    console.log(last2 + " jul " + jul);
                    ordercount[6] ++;
                    break;
                case "08":
                    aug += parseInt(data[i].grandTotal);
                    ordercount[7] ++;
                    break;
                case "09":
                    sep += parseInt(data[i].grandTotal);
                    ordercount[8] ++;
                    break;
                case "10":
                    oct += parseInt(data[i].grandTotal);
                    ordercount[9] ++;
                    break;
                case "11":
                    nov += parseInt(data[i].grandTotal);
                    ordercount[10] ++;
                    break;
                case "12":
                    dec += parseInt(data[i].grandTotal);
                    ordercount[11] ++;
                    break;
            }
        }
        orderList.push(parseInt(jan), parseInt(feb), mar, apr, maj, jun, jul, aug, sep, oct, nov, dec);
        BuildLineChart(orderList, months, "SEK");
        BuildChart(ordercount, months, "Count");
    },
    error: function (e) {

        var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
            + e.responseText + "&lt;/pre&gt;";
        //$('#feedback').html(json);

        console.log("ERROR : ", e);
        //$("#btn-search").prop("disabled", false);

    }
});

function BuildLineChart(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) * 1.1;
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
function BuildChart(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) * 1.1;
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


function BuildChartUserRevenue(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) * 1.1;
    var ctx = document.getElementById("revenueByUser").getContext('2d');
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
function BuildLineChartProducts(orderList, labelList, chartTitle) {
    max = Math.max(...orderList) * 1.1;
    var ctx = document.getElementById("productByMonth");
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
function BuildPieChartProducts(orderList, labelList, chartTitle){
    var ctx = document.getElementById("myPieChart");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labelList,
            datasets: [{
                data: orderList,
                backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', ''],
            }],
        },
    });
    return myPieChart;
}


